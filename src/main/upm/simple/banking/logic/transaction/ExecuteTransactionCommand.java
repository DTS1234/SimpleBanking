package main.upm.simple.banking.logic.transaction;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class ExecuteTransactionCommand {

    private String senderAccount;
    private String receiverAccount;
    private double value;

    public ExecuteTransactionCommand(String senderAccount, String receiverAccount, double value) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.value = value;
    }

    /**
     * This command adds the specified amount to the receiving account and subtracts this
     * amount from the sending account. The user is notified of a successful transaction
     * and for both accounts the previous and the new account are displayed.
     */
    public void execute() {

    }

}
