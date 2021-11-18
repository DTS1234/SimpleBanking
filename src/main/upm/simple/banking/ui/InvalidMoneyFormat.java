package main.upm.simple.banking.ui;

/**
 * @author akazmierczak
 * @create 18.11.2021
 */
public class InvalidMoneyFormat extends RuntimeException {
    public InvalidMoneyFormat() {
        System.out.println("Money value should be a positive double value !");
    }
}
