package main.upm.simple.bank.logic.account;

import jdk.jfr.Description;
import main.upm.simple.bank.TestUtil;
import main.upm.simple.bank.ui.UIInterface;
import main.upm.simple.bank.ui.UIInterfaceReadImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
class ListAccountNumbersCommandTest {

    private AddAccountCommand addAccountCommand = new AddAccountCommand();
    private UIInterface subject;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        TestUtil.deleteFiles();
        subject = new UIInterface(new UIInterfaceReadImpl());
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @Description("Should display a list of account numbers in descending order.")
    void t18() throws Exception {
        // given
        addAccountCommand.execute();
        addAccountCommand.execute();
        addAccountCommand.execute();
        // when
        subject.runTheCommand("list_accountnumbers");
        // then
        final String whatWasPrinted = outputStreamCaptor.toString();
        String whatShouldBePrinted = "Account numbers: \n" +
                "\t000002\n" +
                "\t000001\n" +
                "\t000000";
        Assertions.assertTrue(TestUtil.omitLineSeparator(whatWasPrinted).endsWith(TestUtil.omitLineSeparator(whatShouldBePrinted)));
    }

    @Test
    @Description("No accounts, should print EMPTY message.")
    void t19() throws Exception {
        // when
        subject.runTheCommand("list_accountnumbers");
        // then
        final String whatWasPrinted = outputStreamCaptor.toString();
        String whatShouldBePrinted = "There are no existing accounts";
        Assertions.assertEquals(TestUtil.omitLineSeparator(whatWasPrinted), (TestUtil.omitLineSeparator(whatShouldBePrinted)));
    }

}
