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
            printTransactionsTable(transactions);
        }

    }

    // TODO add table formatting
    private void printTransactionsTable(List<Transaction> all) {

        System.out.println("Sender's account | Receiver's account | Amount");

        all.forEach(transaction -> {
            System.out.println(transaction.getSenderAccountNumber() + " | " + transaction.getReceiverAccountNumber() + " | " + transaction.getAmount() );
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
    public void execute(String sendersAccount, String receiversAccount) {

        List<Transaction> transactions = transactionRepository.findAll()
                .stream()
                .filter(transaction -> isAccountInvolvedInTransaction(sendersAccount, transaction) &&
                        isAccountInvolvedInTransaction(receiversAccount, transaction))
                .collect(Collectors.toList());

        if (transactions.isEmpty()) {
            System.out.println("No transactions connected to those accounts.");
        } else {
            printTransactionsTable(transactions);
        }

    }

}
