package main.upm.simple.banking.persistance;

import main.upm.simple.banking.model.Account;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class AccountRepository implements Repository<Account> {

    public static final String ACCOUNTS_TXT = "accounts.txt";
    private static AccountRepository accountRepository = null;

    private AccountRepository() {
    }

    public static AccountRepository getInstance() {
        return accountRepository == null ? new AccountRepository() : accountRepository;
    }

    @Override
    public Account save(Account accountToBeSaved) {

        if (findAll().stream().anyMatch(account -> accountToBeSaved.getAccountNumber().equals(account.getAccountNumber()))) {
            String newLine = getAsALine(accountToBeSaved);
            Optional<Account> toBeReplaced = findAll().stream().filter(account -> accountToBeSaved.getAccountNumber().equals(account.getAccountNumber())).findFirst();
            String oldLine = getAsALine(toBeReplaced.get());
            replaceLines(newLine, oldLine);
        } else {
            BufferedWriter accountsWriter = null;
            try {
                accountsWriter = Files.newBufferedWriter(Path.of(ACCOUNTS_TXT), StandardOpenOption.APPEND);
                accountsWriter.write(accountToBeSaved.getAccountNumber() + "," + accountToBeSaved.getBalance() + ",");
                accountsWriter.write("empty");
                accountsWriter.write(System.lineSeparator());

            } catch (IOException ioException) {
                ioException.printStackTrace();
            } finally {
                try {
                    Objects.requireNonNull(accountsWriter).close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        return accountToBeSaved;
    }

    private String getAsALine(Account accountToBeSaved) {

        String transactions = accountToBeSaved.getTransactionIds().isEmpty() ? "empty" : accountToBeSaved.getTransactionIds()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(";"));

        return accountToBeSaved.getAccountNumber() + "," + accountToBeSaved.getBalance() + "," + transactions;
    }

    @Override
    public Account findById(Object accountNumber) {
        return findAll().stream().filter(account -> account.getAccountNumber().equals(accountNumber)).findFirst().orElseThrow(
                () -> new AccountNotFoundException("There is no account with that number : " + accountNumber)
        );
    }

    @Override
    public List<Account> findAll() {

        List<Account> result = new ArrayList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(ACCOUNTS_TXT))) {

            while (true) {
                String dataLine = bufferedReader.readLine();
                if (dataLine == null || dataLine.isEmpty()) break;

                String[] split = dataLine.split(",");

                String accountNumber = split[0];
                double balance = Double.parseDouble(split[1]);

                String idsLine = split[2];

                List<Long> transactionIds = new ArrayList<>();

                if (!idsLine.equals("empty")) {
                    String[] idsArray = idsLine.split(";");
                    Arrays.stream(idsArray).forEach(idString -> transactionIds.add(Long.parseLong(idString)));
                }
                result.add(new Account(accountNumber, balance, transactionIds));
            }

        } catch (IOException ioException) {

            if (ioException instanceof NoSuchFileException) {
                File file = new File(ACCOUNTS_TXT);
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return findAll();
            }
            ioException.printStackTrace();

        }

        return result;
    }

    @Override
    public void deleteById(Object accountNumber) {

        List<Account> all = findAll();
        Account accountToBeDeleted = all.stream().filter(account -> account.getAccountNumber().equals(accountNumber)).findFirst().orElseThrow(
                () -> new AccountNotFoundException("There is no account with that number : " + accountNumber)
        );

        removeLine(getAsALine(accountToBeDeleted));

    }

    public void replaceLines(String newLine, String toReplace) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(ACCOUNTS_TXT))) {
            StringBuffer inputBuffer = new StringBuffer();
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {

                if (toReplace.equals("")) {
                    currentLine = currentLine.trim();
                }

                if (toReplace.equals(currentLine)) {
                    currentLine = newLine;
                }

                inputBuffer.append(currentLine);
                inputBuffer.append('\n');
            }
            bufferedReader.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(ACCOUNTS_TXT);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }

    public void removeLine(String toRemove) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(ACCOUNTS_TXT))) {
            StringBuffer inputBuffer = new StringBuffer();
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {

                if (!currentLine.trim().equals(toRemove)) {
                    inputBuffer.append(currentLine);
                    inputBuffer.append('\n');
                }
            }
            bufferedReader.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(ACCOUNTS_TXT);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }

}
