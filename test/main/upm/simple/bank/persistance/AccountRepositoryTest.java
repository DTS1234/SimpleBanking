package main.upm.simple.bank.persistance;

import jdk.jfr.Description;
import main.upm.simple.bank.TestUtil;
import main.upm.simple.bank.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author akazmierczak
 * @create 21.11.2021
 */
class AccountRepositoryTest {

    @BeforeEach
    void setUp() {
        TestUtil.deleteFiles();
    }

    @Test
    @Description("Should throw error when file is open while saving.")
    void t37() throws Exception {

        final RandomAccessFile raFile = new RandomAccessFile(new File("accounts.txt"), "rw");
        raFile.getChannel().lock();

        final AccountRepository instance = AccountRepository.getInstance();
        assertThrows(FileIsOpenException.class, () -> instance.save(new Account("000000", 10, Collections.emptyList())));
        raFile.close();
    }

    @Test
    void t38() {
        AccountRepository.getInstance().removeLine("line", "testFile.txt");
    }

}
