package main.upm.simple.bank.logic.transaction;

import main.upm.simple.bank.model.Transaction;
import main.upm.simple.bank.persistance.TransactionRepository;

import java.io.IOException;
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
    public void execute() throws IOException {

        List<Transaction> transactions = transactionRepository.findAll();

        if (transactions.isEmpty()) {
            System.out.println("Transactions: ");
            System.out.print("EMPTY");
        } else {
            printTransactionsTable(transactions);
        }

    }

    private void printTransactionsTable(List<Transaction> all) {

        System.out.println("Transactions: ");
        System.out.format("%-15s%-15s%-15s%n", "Sender", "Receiver", "Amount");

        all.forEach(transaction -> {
            System.out.format("%-15s%-15s%-15s%n", transaction.getSenderAccountNumber(), transaction.getReceiverAccountNumber(), String.valueOf(transaction.getAmount()));
        });
    }

    /**
     * Returns a table with sending account, receiving account and
     * amount as columns for account with a given accountNumber.
     */
    public void execute(String accountNumber) throws IOException {

        List<Transaction> transactions = transactionRepository.findAll()
                .stream()
                .filter(transaction -> isAccountInvolvedInTransaction(accountNumber, transaction))
                .collect(Collectors.toList());

        checkEmptinessOrPrint(transactions);

    }

    private void checkEmptinessOrPrint(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions connected to that account.");
        } else {
            printTransactionsTable(transactions);
        }
    }

    private boolean isAccountInvolvedInTransaction(String accountNumber, Transaction transaction) {
        return transaction.getSenderAccountNumber().equals(accountNumber) || transaction.getReceiverAccountNumber().equals(accountNumber);
    }

    /**
     * Returns a table with sending account, receiving account and
     * amount as columns for accounts given. All transactions that both accounts were involved in.
     */
    public void execute(String sendersAccount, String receiversAccount) throws IOException {

        List<Transaction> transactions = transactionRepository.findAll()
                .stream()
                .filter(transaction -> isAccountInvolvedInTransaction(sendersAccount, transaction) &&
                        isAccountInvolvedInTransaction(receiversAccount, transaction))
                .collect(Collectors.toList());

        checkEmptinessOrPrint(transactions);

    }

}
