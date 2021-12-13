package main.upm.simple.bank.persistance;

public class FileCreatedByUserException extends RuntimeException {
    public FileCreatedByUserException() {
        System.out.println("File created by user! Please delete it.");
    }
}
