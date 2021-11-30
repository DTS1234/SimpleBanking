package main.upm.simple.banking.logic.account;

import main.upm.simple.banking.TestUtil;
import main.upm.simple.banking.model.Account;
import main.upm.simple.banking.persistance.AccountNotFoundException;
import main.upm.simple.banking.persistance.AccountRepository;
import main.upm.simple.banking.ui.UIInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
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
        subject = new UIInterface();
        accountRepository = AccountRepository.getInstance();
    }

    @Test
    void t23() {
        // given
        accountRepository.save(new Account("000000", 1, Collections.emptyList()));
        // when
        subject.runTheCommand("view_account 000000");
        // then
        String whatWasPrinted = outputStreamCaptor.toString();
        String whatShouldBePrinted = "Account : \n\taccount number : 000000\n\tbalance : 1,0" ;
        Assertions.assertEquals(whatWasPrinted, whatShouldBePrinted);
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
