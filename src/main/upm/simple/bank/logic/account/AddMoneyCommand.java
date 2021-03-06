package main.upm.simple.bank.logic.account;

import main.upm.simple.bank.model.Account;
import main.upm.simple.bank.persistance.AccountRepository;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class AddMoneyCommand {

    private final AccountRepository accountRepository = AccountRepository.getInstance();
    private final String accountNumber;
    private final double value;

    public AddMoneyCommand(String accountNumber, double value) {
        this.accountNumber = accountNumber;
        this.value = value;
    }

    /**
     * This command adds the specified amount to the specified account and notifies the
     * user afterwards that the addition has been successful. Furthermore, the previous and
     * the new balance are displayed.
     */
    public void execute() throws Exception {
        Account selectedAccount = accountRepository.findById(accountNumber);
        double currentBalance = selectedAccount.getBalance();
        double newBalance = currentBalance + value;
        selectedAccount.setBalance(newBalance);

        accountRepository.save(selectedAccount);

        String message = String.format("The addition to the account was successful, \n" +
                "\tPrevious balance : %.2f\n" +
                "\tNew balance : %.2f", currentBalance, newBalance);

        System.out.println(message);
    }

}
