package main.upm.simple.banking.persistance;

public class FileIsOpenException extends RuntimeException {
    public FileIsOpenException() {
        System.out.println("Please close the accounts.txt file while using the program.");
    }
}
