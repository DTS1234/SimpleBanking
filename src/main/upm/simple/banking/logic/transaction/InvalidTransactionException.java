package main.upm.simple.banking.logic.transaction;

/**
 * @author akazmierczak
 * @create 18.11.2021
 */
public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(String message) {
        System.out.println(message);
    }
}
