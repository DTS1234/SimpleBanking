package main.upm.simple.banking.logic.account;

import jdk.jfr.Description;
import main.upm.simple.banking.logic.transaction.InvalidTransactionException;
import main.upm.simple.banking.model.Account;
import main.upm.simple.banking.persistance.AccountRepository;
import main.upm.simple.banking.ui.InvalidMoneyFormat;
import main.upm.simple.banking.ui.UIInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
class WithdrawCommandTest {

    private UIInterface subject;
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        subject = new UIInterface();
        accountRepository = AccountRepository.getInstance();
    }

    @Test
    @Description("Should withdraw money properly.")
    void t15() {
        // given
        accountRepository.save(new Account("000000", 5, Collections.emptyList()));
        // when
        subject.runTheCommand("withdraw_money 000000 4");
        // then
        final Account actual = accountRepository.findById("000000");
        assertEquals("000000", actual.getAccountNumber());
        assertEquals(1, actual.getBalance());
        assertEquals(Collections.emptyList(), actual.getTransactionIds());
    }

    @Test
    @Description("Should throw an error when negative value indicated")
    void t16() {
        // given
        accountRepository.save(new Account("000000", 5, Collections.emptyList()));
        // when
        assertThrows(InvalidMoneyFormat.class, () -> subject.runTheCommand("withdraw_money 000000 -10"));
    }

    @Test
    @Description("Should throw an error when amount specified exceeds the balance")
    void t17() {
        // given
        accountRepository.save(new Account("000000", 5, Collections.emptyList()));
        // when
        assertThrows(InvalidTransactionException.class, () -> subject.runTheCommand("withdraw_money 000000 6"));
    }

}
