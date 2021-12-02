package main.upm.simple.banking.logic.account;

import jdk.jfr.Description;
import main.upm.simple.banking.TestUtil;
import main.upm.simple.banking.model.Account;
import main.upm.simple.banking.persistance.AccountNotFoundException;
import main.upm.simple.banking.persistance.AccountRepository;
import main.upm.simple.banking.ui.InvalidAccountNumber;
import main.upm.simple.banking.ui.UIInterface;
import main.upm.simple.banking.ui.WrongInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
class DeleteAccountCommandTest {

    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        TestUtil.deleteFiles();
        accountRepository = AccountRepository.getInstance();
    }

    @Test
    @Description("Should invoke delete command transaction.")
    void t7() throws Exception {
        //given
        accountRepository.save(new Account("000000", 10, Collections.emptyList()));
        //when
        new UIInterface().runTheCommand("delete_account 000000");
        //then
        Assertions.assertTrue(accountRepository.findAll().isEmpty());
    }

    @Test
    @Description("Should throw an error about to many arguments")
    void t8() {
        final WrongInputException wrongInputException = assertThrows(
                WrongInputException.class,
                // when
                () -> new UIInterface().runTheCommand("delete_account 000000 14.45"));

        Assertions.assertEquals("Delete command should receive one argument in the format of account number", wrongInputException.getMessage());
    }

    @Test
    @Description("Should throw an error about invalid account format")
    void t9() {
        assertThrows(InvalidAccountNumber.class, () -> new UIInterface().runTheCommand("delete_account wrong11"));
    }

    @Test
    @Description("Should throw an error when no account found.")
    void t31() {
        Assertions.assertThrows(AccountNotFoundException.class, () -> new UIInterface().runTheCommand("delete_account 000000"));
    }

}
