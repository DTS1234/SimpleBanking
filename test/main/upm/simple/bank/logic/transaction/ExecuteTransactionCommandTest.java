package main.upm.simple.bank.logic.transaction;

import jdk.jfr.Description;
import main.upm.simple.bank.model.Account;
import main.upm.simple.bank.persistance.AccountRepository;
import main.upm.simple.bank.persistance.TransactionRepository;
import main.upm.simple.bank.ui.InvalidAccountNumber;
import main.upm.simple.bank.ui.InvalidMoneyFormat;
import main.upm.simple.bank.ui.UIInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
class ExecuteTransactionCommandTest {

    private final TransactionRepository transactionRepository = TransactionRepository.getInstance();
    private final AccountRepository accountRepository = AccountRepository.getInstance();
    private UIInterface subject;

    @BeforeEach
    void setUp() {
        subject = new UIInterface();
    }

    @Test
    @Description("Should substract from the sender and add to the receiver")
    void t27() throws Exception {
        accountRepository.save(new Account("000000", 5, Collections.emptyList()));
        accountRepository.save(new Account("000001", 5, Collections.emptyList()));

        subject.runTheCommand("execute_transaction 000000 000001 5");

        final double balance1 = accountRepository.findById("000000").getBalance();
        final double balance2 = accountRepository.findById("000001").getBalance();

        assertEquals(0, balance1);
        assertEquals(10, balance2);
    }

    @Test
    @Description("Should throw an error when the value of the transactions exceeds the balance")
    void t28() throws Exception {
        accountRepository.save(new Account("000000", 2, Collections.emptyList()));
        accountRepository.save(new Account("000001", 5, Collections.emptyList()));

        assertThrows(InvalidTransactionException.class, () -> subject.runTheCommand("execute_transaction 000000 000001 10"));
    }

    @Test
    @Description("Should throw error when not all account number arguments are digits.")
    void t33() {
        assertThrows(InvalidAccountNumber.class, () -> subject.runTheCommand("execute_transaction wrong1 000000 12"));
    }

    @Test
    @Description("Should throw an error when money is not a number.")
    void t34() {
        assertThrows(InvalidMoneyFormat.class, () -> subject.runTheCommand("execute_transaction 000000 000000 someMoney"));
    }

}
