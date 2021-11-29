package main.upm.simple.banking.logic.account;

import main.upm.simple.banking.logic.transaction.InvalidTransactionException;
import main.upm.simple.banking.model.Account;
import main.upm.simple.banking.persistance.AccountRepository;
import main.upm.simple.banking.persistance.TransactionNotFoundException;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class WithdrawCommand {

    private final AccountRepository accountRepository = AccountRepository.getInstance();
    private final String accountNumber;
    private final double value;

    public WithdrawCommand(String accountNumber, double value) {
        this.accountNumber = accountNumber;
        this.value = value;
    }

    /**
     * This command allows the withdrawal of a specified amount from a particular account.
     * The account balance is updated accordingly. The user is notified of the successful
     * withdrawal. Furthermore, the previous and the new balance are displayed.
     */
    public void execute() {
        Account selectedAccount = accountRepository.findById(accountNumber);
        double currentBalance = selectedAccount.getBalance();
        double newBalance = currentBalance - value;

        if (newBalance < 0) {
            throw new InvalidTransactionException("Invalid transaction: you cannot exceed the balance");
        }

        selectedAccount.setBalance(newBalance);

        Account newAccountInfo = accountRepository.save(selectedAccount);

        String message = String.format("The withdrawal from the account was successful, \n" +
                "\tPrevious balance : %.2f\n" +
                "\tNew balance : %.2f", currentBalance, newBalance);

        System.out.println(message);
    }

}
