package main.upm.simple.banking.logic.account;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class ViewAccountCommand {

    private String accountNumber;

    public ViewAccountCommand(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * This command shows the information of a particular account. This information
     * comprises the account ID and the account balance of that account..
     */
    public void execute() {

    }

}
