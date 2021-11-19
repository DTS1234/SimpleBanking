package main.upm.simple.banking.logic.transaction;

import main.upm.simple.banking.model.Account;
import main.upm.simple.banking.model.Transaction;
import main.upm.simple.banking.persistance.AccountRepository;
import main.upm.simple.banking.persistance.TransactionRepository;

import java.util.List;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class ExecuteTransactionCommand {

    private String senderAccountNumber;
    private String receiverAccountNumber;
    private double value;

    public ExecuteTransactionCommand(String senderAccountNumber, String receiverAccountNumber, double value) {
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.value = value;
    }

    /**
     * This command adds the specified amount to the receiving account and subtracts this
     * amount from the sending account. The user is notified of a successful transaction
     * and for both accounts the previous and the new account are displayed.
     */
    public void execute() {
        TransactionRepository transactionRepo = TransactionRepository.getInstance();
        AccountRepository accountRepo = AccountRepository.getInstance();

        Account sendersAccount = accountRepo.findById(senderAccountNumber);
        Account snapshotSender = new Account(sendersAccount.getAccountNumber(), sendersAccount.getBalance(), sendersAccount.getTransactionIds());

        Account receiversAccount = accountRepo.findById(receiverAccountNumber);
        Account snapshotReceiver = new Account(receiversAccount.getAccountNumber(), receiversAccount.getBalance(), receiversAccount.getTransactionIds());

        List<Transaction> all = transactionRepo.findAll();

        Long id;
        if (!all.isEmpty()) {
            id = all.get(all.size() - 1).getId();
        } else {
            id = 1L;
        }

        transactionRepo.save(new Transaction(++id, senderAccountNumber, receiverAccountNumber, value));
        updateAccounts(accountRepo, id, sendersAccount, receiversAccount);

        String message = String.format("Successful transaction:\n\t sender before transaction: "
                + sendersAccount.getAccountNumber() + " | " + sendersAccount.getBalance() + " | " + sendersAccount.transactionsString()
                + "\n\t sender after transaction: " + snapshotSender.getAccountNumber() + " | " + snapshotSender.getBalance() + " | " + snapshotSender.transactionsString()
                + "\n\t receiver before transaction: " + snapshotReceiver.getAccountNumber() + " | " +  snapshotReceiver.getBalance() + " | " + snapshotReceiver.transactionsString()
                + "\n\t receiver after transaction: " + receiversAccount.getAccountNumber() + " | " +  receiversAccount.getBalance() + " | " + receiversAccount.transactionsString());

        System.out.println(message);
    }

    private void updateAccounts(AccountRepository accountRepo, Long id, Account sendersAccount, Account receiversAccount) {
        // update sender's account balance
        double newSendersBalance = sendersAccount.getBalance() - value;
        if (newSendersBalance < 0) {
            throw new InvalidTransactionException("Sender does not have enough money to execute the transaction ! current balance: " + sendersAccount.getBalance() + ".");
        }
        sendersAccount.setBalance(newSendersBalance);

        addTransactionToTheAccount(id, sendersAccount);

        // update receiver's account balance
        double newReceiversBalance = receiversAccount.getBalance() + value;
        receiversAccount.setBalance(newReceiversBalance);

        addTransactionToTheAccount(id, receiversAccount);

        accountRepo.save(sendersAccount);
        accountRepo.save(receiversAccount);
    }

    private void addTransactionToTheAccount(Long id, Account sendersAccount) {
        List<Long> newTransactionsSender = sendersAccount.getTransactionIds();
        newTransactionsSender.add(id);
        sendersAccount.setTransactionIds(newTransactionsSender);
    }

}
