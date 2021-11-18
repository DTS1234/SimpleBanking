package main.upm.simple.banking.logic.account;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class WithdrawCommand {

    private String accountNumber;
    private double value;

    public WithdrawCommand(String accountNumber, double value) {
        this.accountNumber = accountNumber;
        this.value = value;
    }

    /**
     * This command allows the withdrawal of a specified amount from a particular account.
     * The account balance is updated accordingly. The user is notified of the successful
     * withdrawal. Furthermore, the previous and the new balance are displayed.
     */
    void execute(String accountNumber, double value) {

    }

}
