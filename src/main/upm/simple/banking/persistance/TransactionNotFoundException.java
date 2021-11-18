package main.upm.simple.banking.persistance;

/**
 * @author akazmierczak
 * @create 18.11.2021
 */
public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message) {
        System.out.println(message);
    }
}
