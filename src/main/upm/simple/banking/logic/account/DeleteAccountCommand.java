package main.upm.simple.banking.logic.account;

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

    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
