package main.upm.simple.banking.logic.account;

import jdk.jfr.Description;
import main.upm.simple.banking.TestUtil;
import main.upm.simple.banking.model.Account;
import main.upm.simple.banking.persistance.AccountRepository;
import main.upm.simple.banking.ui.UIInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
class AddAccountCommandTest {

    private AccountRepository accountRepository;
    private UIInterface actual;

    @BeforeAll
    static void beforeAll() {
        TestUtil.deleteFiles();
    }

    @BeforeEach
    void setUp() {
        actual = new UIInterface();
        accountRepository = AccountRepository.getInstance();
    }

    @AfterEach
    void tearDown() {
        TestUtil.deleteFiles();
    }

    @Test
    @Description("Should create account with 000000 when no accounts file exist.")
    void t1() {
        //when
        actual.runTheCommand("add_account");
        //then
        Account actual = accountRepository.findById("000000");
        assertEquals("000000", actual.getAccountNumber());
        assertEquals(0, actual.getBalance());
        assertEquals(Collections.emptyList(), actual.getTransactionIds());
    }

    @Test
    @Description("Should create account with account number properly adjusted.")
    void t2() {
        //given
        accountRepository.save(new Account("000000", 2, Collections.emptyList()));
        accountRepository.save(new Account("000001", 2, Collections.emptyList()));
        accountRepository.save(new Account("000004", 2, Collections.emptyList()));
        accountRepository.save(new Account("000007", 2, Collections.emptyList()));
        //when
        actual.runTheCommand("add_account");
        //then
        Account byId = AccountRepository.getInstance().findById("000008");
        assertEquals("000008", byId.getAccountNumber());
    }

    @Test
    @Description("Should create account with 000000 when accounts file exist but it's empty")
    void t3() {
        // given
        accountRepository.save(new Account("000000", 1, Collections.emptyList()));
        accountRepository.deleteById("000000");
        //when
        actual.runTheCommand("add_account");
        //then
        Account actual = accountRepository.findById("000000");
        assertEquals("000000", actual.getAccountNumber());
        assertEquals(0, actual.getBalance());
        assertEquals(Collections.emptyList(), actual.getTransactionIds());
    }

    @Test
    @Description("Should create account with 100000 when the last account was 099999")
    void t4() {
        // given
        accountRepository.save(new Account("099999", 1, Collections.emptyList()));
        //when
        actual.runTheCommand("add_account");
        //then
        Account actual = accountRepository.findById("100000");
        assertEquals("100000", actual.getAccountNumber());
        assertEquals(0, actual.getBalance());
        assertEquals(Collections.emptyList(), actual.getTransactionIds());
    }

    @Test
    @Description("Should add the zeroes if string passed does not contain 6 digits")
    void t5() {
        // given
        accountRepository.save(new Account("0", 1, Collections.emptyList()));
        //when
        actual.runTheCommand("add_account");
        //then
        Account actual = accountRepository.findById("000001");
        assertEquals("000001", actual.getAccountNumber());
        assertEquals(0, actual.getBalance());
        assertEquals(Collections.emptyList(), actual.getTransactionIds());
    }

}
