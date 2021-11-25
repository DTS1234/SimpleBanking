package main.upm.simple.banking.logic.account;

import jdk.jfr.Description;
import main.upm.simple.banking.model.Account;
import main.upm.simple.banking.persistance.AccountRepository;
import main.upm.simple.banking.ui.UIInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
class AddMoneyCommandTest {

    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = AccountRepository.getInstance();
    }

    @Test
    @Description("Should add money to existing account taking into account the current balance.")
    void t6() {
        // given
        accountRepository.save(new Account("000000", 5, Collections.emptyList()));
        // when
        new UIInterface().runTheCommand("add_money 000000 10.05");
        // then
        final Account actual = accountRepository.findById("000000");
        assertEquals("000000", actual.getAccountNumber());
        assertEquals(15.05, actual.getBalance());
        assertEquals(Collections.emptyList(), actual.getTransactionIds());
    }
}
