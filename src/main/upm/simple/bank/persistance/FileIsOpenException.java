package main.upm.simple.bank.persistance;

public class FileIsOpenException extends RuntimeException {
    public FileIsOpenException(String fileName) {
        System.out.println("Please close the " + fileName + " file while using the program.");
    }
}
