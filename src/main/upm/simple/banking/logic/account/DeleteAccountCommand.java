package main.upm.simple.banking.logic.account;

import main.upm.simple.banking.persistance.AccountRepository;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class DeleteAccountCommand {

    private String accountNumber;

    public DeleteAccountCommand(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * This command deletes the account which has the specified account number and
     * notifies the user with a message, that the deletion process has been completed.
     */
    public void execute() {
        AccountRepository.getInstance().deleteById(accountNumber);
        System.out.println("Account with the number " + accountNumber + " has been deleted successfully.");
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
