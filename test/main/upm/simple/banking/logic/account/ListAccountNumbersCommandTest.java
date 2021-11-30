package main.upm.simple.banking.logic.account;

import jdk.jfr.Description;
import main.upm.simple.banking.TestUtil;
import main.upm.simple.banking.model.Account;
import main.upm.simple.banking.persistance.AccountRepository;
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
class ListAccountNumbersCommandTest {

    private AddAccountCommand addAccountCommand = new AddAccountCommand();
    private ListAccountNumbersCommand subject;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        TestUtil.deleteFiles();
        subject = new ListAccountNumbersCommand();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @Description("Should display a list of account numbers in descending order.")
    void t18() {
        // given
        addAccountCommand.execute();
        addAccountCommand.execute();
        addAccountCommand.execute();
        // when
        subject.execute();
        // then
        final String whatWasPrinted = outputStreamCaptor.toString();
        String whatShouldBePrinted = "Account numbers: \n" +
                "\t000000\n" +
                "\t000001\n" +
                "\t000002";
        Assertions.assertTrue(TestUtil.omitLineSeparator(whatWasPrinted).endsWith(TestUtil.omitLineSeparator(whatShouldBePrinted)));
    }

    @Test
    @Description("No accounts, should print EMPTY message.")
    void t19() {
        // when
        subject.execute();
        // then
        final String whatWasPrinted = outputStreamCaptor.toString();
        String whatShouldBePrinted = "Account numbers: \n" +
                "\tEMPTY";
        Assertions.assertEquals(TestUtil.omitLineSeparator(whatWasPrinted), (TestUtil.omitLineSeparator(whatShouldBePrinted)));
    }

}
