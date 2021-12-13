package main.upm.simple.bank.logic.account;

import main.upm.simple.bank.TestUtil;
import main.upm.simple.bank.model.Account;
import main.upm.simple.bank.persistance.AccountNotFoundException;
import main.upm.simple.bank.persistance.AccountRepository;
import main.upm.simple.bank.ui.UIInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
class ViewAccountCommandTest {

    private AccountRepository accountRepository;
    private UIInterface subject;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        TestUtil.deleteFiles();
        subject = new UIInterface();
        accountRepository = AccountRepository.getInstance();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void t23() throws Exception {
        // given
        accountRepository.save(new Account("000000", 1, Collections.emptyList()));
        // when
        subject.runTheCommand("view_account 000000");
        // then
        String whatWasPrinted = outputStreamCaptor.toString();
        String whatShouldBePrinted = "Account : \n\taccount number : 000000\n\t balance : 1,00" ;
        Assertions.assertEquals(TestUtil.omitLineSeparator(whatWasPrinted), TestUtil.omitLineSeparator(whatShouldBePrinted));
    }

    @Test
    void t24() {
        // when
        Assertions.assertThrows(AccountNotFoundException.class, () -> subject.runTheCommand("view_account 000000"));
        // then
        String whatWasPrinted = outputStreamCaptor.toString();
        String whatShouldBePrinted = "There is no account with that number : 000000";
        Assertions.assertEquals(TestUtil.omitLineSeparator(whatWasPrinted), TestUtil.omitLineSeparator(whatShouldBePrinted));
    }
}
