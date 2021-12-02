package main.upm.simple.banking.persistance;

public class FileIsOpenException extends RuntimeException {
    public FileIsOpenException(String fileName) {
        System.out.println("Please close the " + fileName + " file while using the program.");
    }
}
