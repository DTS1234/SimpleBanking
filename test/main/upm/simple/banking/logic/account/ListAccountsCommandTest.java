package main.upm.simple.banking.logic.account;

import jdk.jfr.Description;
import main.upm.simple.banking.TestUtil;
import main.upm.simple.banking.ui.UIInterface;
import main.upm.simple.banking.ui.UIInterfaceReadImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
class ListAccountsCommandTest {

    private UIInterface subject;
    private AddAccountCommand addAccountCommand;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeAll
    static void beforeAll() {
        TestUtil.deleteFiles();
    }

    @BeforeEach
    void setUp() {
        TestUtil.deleteFiles();
        subject = new UIInterface(new UIInterfaceReadImpl());
        addAccountCommand = new AddAccountCommand();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @Description("Should print the numbers and balances when those exist.")
    void t20() throws Exception {
        // given
        addAccountCommand.execute();
        addAccountCommand.execute();
        addAccountCommand.execute();
        // when
        subject.runTheCommand("list_accounts");
        // then
        final String whatWasPrinted = outputStreamCaptor.toString();
        final String whatShouldBePrinted =
                "account number: 000000 | balance: 0.0\n" +
                        "account number: 000001 | balance: 0.0\n" +
                        "account number: 000002 | balance: 0.0";

        Assertions.assertTrue(TestUtil.omitLineSeparator(whatWasPrinted)
                .endsWith(TestUtil.omitLineSeparator(whatShouldBePrinted)));
    }

    @Test
    void t21() throws Exception {
        // when
        subject.runTheCommand("list_accounts");
        // then
        final String whatWasPrinted = outputStreamCaptor.toString();
        final String whatShouldBePrinted = "There are no existing accounts";
        Assertions.assertTrue(TestUtil.omitLineSeparator(whatWasPrinted).endsWith(
                TestUtil.omitLineSeparator(whatShouldBePrinted)));
    }
}
