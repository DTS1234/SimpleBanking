package main.upm.simple.banking.persistance;

import main.upm.simple.banking.model.Transaction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class TransactionRepository implements Repository<Transaction> {

    public static final String TRANSACTIONS_TXT = "transactions.txt";
    private static TransactionRepository transactionRepository = null;

    private TransactionRepository() {
    }

    public static TransactionRepository getInstance() {
        return transactionRepository = new TransactionRepository();
    }

    @Override
    public Transaction save(Transaction transactionToBeSaved) throws Exception {
        if (findAll().stream().anyMatch(transaction -> transactionToBeSaved.getId().equals(transaction.getId()))) {
            String newLine = getAsALine(transactionToBeSaved);
            Optional<Transaction> toBeReplaced = findAll().stream().filter(transaction -> transactionToBeSaved.getId().equals(transaction.getId())).findFirst();
            String oldLine = getAsALine(toBeReplaced.get());
            replaceLines(newLine, oldLine, TRANSACTIONS_TXT);
        } else {
            BufferedWriter transactionWriter = null;
            try {
                transactionWriter = Files.newBufferedWriter(Path.of(TRANSACTIONS_TXT), StandardOpenOption.APPEND);
                transactionWriter.write(getAsALine(transactionToBeSaved));
                transactionWriter.write(System.lineSeparator());

            } catch (IOException ioException) {
                ioException.printStackTrace();
            } finally {
                try {
                    Objects.requireNonNull(transactionWriter).close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        return transactionToBeSaved;
    }

    @Override
    public String getAsALine(Transaction transaction) {
        return transaction.getId() + "," + transaction.getReceiverAccountNumber() + "," + transaction.getSenderAccountNumber() + "," + transaction.getAmount();
    }

    @Override
    public Transaction findById(Object id) throws IOException {
        return findAll().stream().filter(transaction -> transaction.getId().equals(id)).findFirst().orElseThrow(
                () -> new TransactionNotFoundException("There is no transaction with the following id " + id)
        );
    }

    @Override
    public List<Transaction> findAll() throws IOException {

        List<Transaction> result = new ArrayList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(TRANSACTIONS_TXT))) {

            while (true) {
                String dataLine = bufferedReader.readLine();
                if (dataLine == null) break;
                String[] split = dataLine.split(",");
                Long id = Long.valueOf(split[0]);
                String receiversAccount = split[1];
                String sendersAccount = split[2];
                double value = Double.parseDouble(split[3]);
                result.add(new Transaction(id, sendersAccount, receiversAccount, value));
            }

        } catch (IOException ioException) {

            if (ioException instanceof NoSuchFileException) {
                File file = new File(TRANSACTIONS_TXT);
                file.createNewFile();
                return findAll();
            }
            throw new FileIsOpenException(TRANSACTIONS_TXT);
        }

        return result;
    }

    @Override
    public void deleteById(Object id) throws IOException {
        List<Transaction> all = findAll();
        Transaction transactionToBeDeleted = all.stream().filter(transaction -> transaction.getId().equals(id)).findFirst().orElseThrow(
                () -> new TransactionNotFoundException("There is no transaction with the following id " + id));
        removeLine(getAsALine(transactionToBeDeleted), TRANSACTIONS_TXT);
    }
}
