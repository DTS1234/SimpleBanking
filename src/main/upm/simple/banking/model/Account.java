package main.upm.simple.banking.model;

import java.util.List;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class Account {

    private String accountNumber;
    private double balance;
    private List<Long> transactionIds;

    public Account(String accountNumber, double balance, List<Long> transactionIds) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionIds = transactionIds;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<Long> getTransactionIds() {
        return transactionIds;
    }

    public String transactionsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Long id : transactionIds) {
            if (transactionIds.size() - 1 == transactionIds.indexOf(id)) {
                stringBuilder.append(id);
            } else {
                stringBuilder.append(id).append(", ");
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", transactionIds=" + transactionIds +
                '}';
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setTransactionIds(List<Long> transactionIds) {
        this.transactionIds = transactionIds;
    }
}
