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
}
