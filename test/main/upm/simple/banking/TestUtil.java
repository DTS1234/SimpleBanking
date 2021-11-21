package main.upm.simple.banking;

import java.io.File;

/**
 * @author akazmierczak
 * @create 21.11.2021
 */
public class TestUtil {

    public static void deleteFiles() {
        File accountsFile = new File("accounts.txt");
        File transactionFile = new File("transactions.txt");
        accountsFile.delete();
        transactionFile.delete();
    }


}
