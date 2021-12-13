package main.upm.simple.bank.model;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class Transaction {

    private final Long id;
    private final String senderAccountNumber;
    private final String receiverAccountNumber;
    private final double amount;

    public Transaction(Long id, String senderAccountNumber, String receiverAccountNumber, double amount) {
        this.id = id;
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", senderAccountNumber='" + senderAccountNumber + '\'' +
                ", receiverAccountNumber='" + receiverAccountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
