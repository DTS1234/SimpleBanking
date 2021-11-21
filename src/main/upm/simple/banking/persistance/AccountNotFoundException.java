package main.upm.simple.banking.persistance;

/**
 * @author akazmierczak
 * @create 18.11.2021
 */
public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String message) {
        System.out.println(message);
    }

}
