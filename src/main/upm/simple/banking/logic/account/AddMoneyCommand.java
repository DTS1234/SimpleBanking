package main.upm.simple.banking.logic.account;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class AddMoneyCommand {

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
    public void execute() {

    }

}
