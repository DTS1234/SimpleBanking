package main.upm.simple.banking.persistance;

import main.upm.simple.banking.model.Account;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class AccountRepository  implements Repository<Account>{

    @Override
    public Account save(Account accountToBeSaved) {

        String pathname = "accounts.txt";
        File file = new File(pathname);

        try {
            BufferedWriter accountFile = Files.newBufferedWriter(Path.of(pathname));



        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return null;
    }

    @Override
    public Account findById(Account o) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public void delete(Account o) {

    }
}
