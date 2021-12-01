package main.upm.simple.banking.persistance;


import main.upm.simple.banking.TestUtil;
import main.upm.simple.banking.model.Account;
import main.upm.simple.banking.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author akazmierczak
 * @create 21.11.2021
 */
class TransactionRepositoryTest {

    @BeforeEach
    void setUp() {
        TestUtil.deleteFiles();
    }

    @Test
    void t43() throws IOException {
        assertThrows(TransactionNotFoundException.class, () -> TransactionRepository.getInstance().findById(1));
    }

    @Test
    void t44() throws Exception {
        final TransactionRepository instance = TransactionRepository.getInstance();
        instance.save(new Transaction(1L, "000000", "000001", 10));
        final Transaction byId = instance.findById(1L);
        assertEquals(byId.getId(), 1L);
    }

    @Test
    void t45() throws Exception {
        final TransactionRepository instance = TransactionRepository.getInstance();
        instance.save(new Transaction(1L, "000000", "000001", 10));

        instance.deleteById(1L);

        assertTrue(instance.findAll().isEmpty());
    }

    @Test
    void t46() throws Exception {
        final TransactionRepository instance = TransactionRepository.getInstance();
        assertThrows(TransactionNotFoundException.class, () -> instance.deleteById(1L));
    }

    @Test
    void t47() throws IOException {
        final RandomAccessFile raFile = new RandomAccessFile(new File("transactions.txt"), "rw");
        raFile.getChannel().lock();

        final TransactionRepository instance = TransactionRepository.getInstance();
        assertThrows(FileIsOpenException.class, () -> instance.save(new Transaction(1L, "000000", "000001", 10)));
        raFile.close();
    }
}
