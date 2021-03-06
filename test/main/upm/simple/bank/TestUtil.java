package main.upm.simple.bank;

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


    public static String omitLineSeparator(String yourString) {
        return yourString.replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t", "").trim();
    }
}
