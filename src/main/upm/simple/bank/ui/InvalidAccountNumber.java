package main.upm.simple.bank.ui;

/**
 * @author akazmierczak
 * @create 18.11.2021
 */
public class InvalidAccountNumber extends RuntimeException {
    public InvalidAccountNumber(String accountNumber) {
        System.out.println("Following accountNumber is in wrong format : " + accountNumber + "\n");
        System.out.println("Required format is a 6 digit number.");
    }
}
