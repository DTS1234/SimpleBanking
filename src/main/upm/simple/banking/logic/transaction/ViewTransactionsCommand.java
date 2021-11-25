package main.upm.simple.banking.logic.transaction;

import main.upm.simple.banking.model.Transaction;
import main.upm.simple.banking.persistance.TransactionRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class ViewTransactionsCommand {

    private final TransactionRepository transactionRepository = TransactionRepository.getInstance();

    /**
     * Returns a table with sending account, receiving account
     * and amount as columns.
     */
    public void execute() {

        List<Transaction> transactions = transactionRepository.findAll();

        System.out.println("Transactions: ");

        if (transactions.isEmpty()) {
            System.out.print("EMPTY");
        } else {
            printTransactions(transactions);
        }

    }

    private void printTransactions(List<Transaction> all) {
        all.forEach(transaction -> {

            String message = String.format("\t sender's account : %s, receiver's account : %s, amount: %.2f",
                    transaction.getSenderAccountNumber(),
                    transaction.getReceiverAccountNumber(),
                    transaction.getAmount());
            System.out.println(message);

        });
    }

    /**
     * Returns a table with sending account, receiving account and
     * amount as columns for account with a given accountNumber.
     */
    public void execute(String accountNumber) {


        List<Transaction> transactions = transactionRepository.findAll()
                .stream()
                .filter(transaction -> isAccountInvolvedInTransaction(accountNumber, transaction))
                .collect(Collectors.toList());

        if (transactions.isEmpty()) {
            System.out.println("No transactions connected to that account.");
        } else {
            printTransactions(transactions);
        }

    }

    private boolean isAccountInvolvedInTransaction(String accountNumber, Transaction transaction) {
        return transaction.getSenderAccountNumber().equals(accountNumber) || transaction.getReceiverAccountNumber().equals(accountNumber);
    }

    /**
     * Returns a table with sending account, receiving account and
     * amount as columns for accounts given. All transactions that both accounts were involved in.
     */
    public void execute(String sendersAccount, String receiversAccount) {

        List<Transaction> transactions = transactionRepository.findAll()
                .stream()
                .filter(transaction -> isAccountInvolvedInTransaction(sendersAccount, transaction) &&
                        isAccountInvolvedInTransaction(receiversAccount, transaction))
                .collect(Collectors.toList());

        if (transactions.isEmpty()) {
            System.out.println("No transactions connected to those accounts.");
        } else {
            printTransactions(transactions);
        }

    }

}
