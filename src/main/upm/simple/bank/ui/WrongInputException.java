package main.upm.simple.bank.ui;

/**
 * @author akazmierczak
 * @create 18.11.2021
 */
public class WrongInputException extends RuntimeException {
    public WrongInputException(String s) {
        super(s);
        System.out.println(s);
    }
}
