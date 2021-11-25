package main.upm.simple.banking.logic.transaction;

import jdk.jfr.Description;
import main.upm.simple.banking.TestUtil;
import main.upm.simple.banking.persistance.TransactionRepository;
import main.upm.simple.banking.ui.UIInterface;
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
class ViewTransactionsCommandTest {

    private UIInterface subject;
    private TransactionRepository transactionRepository;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        TestUtil.deleteFiles();
        transactionRepository = TransactionRepository.getInstance();
        System.setOut(new PrintStream(outputStreamCaptor));
        subject = new UIInterface();
    }

    @BeforeAll
    static void beforeAll() {
        TestUtil.deleteFiles();
    }

    @Test
    @Description("Should print message about empty transactions.")
    void t10() {
        // when
        subject.runTheCommand("view_transactions");

        // then
        final String whatWasPrinted = outputStreamCaptor.toString();
        String whatShouldBePrinted = "Transactions: \n" +
                "EMPTY";
        Assertions.assertEquals(omitLineSeparator(whatShouldBePrinted), omitLineSeparator(whatWasPrinted));
    }

    @Test
    @Description("Should display all transactions.")
    void t11() {

        subject.runTheCommand("view_transactions");
    }

    private String omitLineSeparator(String yourString) {
        return yourString.replaceAll("\n", "").replaceAll("\r", "");
    }

}
