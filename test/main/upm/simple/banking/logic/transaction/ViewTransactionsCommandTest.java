package main.upm.simple.banking.logic.transaction;

import jdk.jfr.Description;
import main.upm.simple.banking.TestUtil;
import main.upm.simple.banking.model.Transaction;
import main.upm.simple.banking.persistance.AccountNotFoundException;
import main.upm.simple.banking.persistance.TransactionRepository;
import main.upm.simple.banking.ui.UIInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @Description("No transactions, should print message about empty transactions when view_transactions called.")
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
        // given
        transactionRepository.save(new Transaction(1L, "000000", "000001", 10));
        transactionRepository.save(new Transaction(2L, "000001", "000003", 10));
        transactionRepository.save(new Transaction(3L, "000002", "000004", 10));
        transactionRepository.save(new Transaction(4L, "000002", "000000", 10));
        // when
        subject.runTheCommand("view_transactions");
        // then
        String whatShouldBePrinted = "Transactions: \n" +
                "Sender         Receiver       Amount         \n" +
                "000000         000001         10.0           \n" +
                "000001         000003         10.0           \n" +
                "000002         000004         10.0           \n" +
                "000002         000000         10.0";
        final String whatWasPrinted = outputStreamCaptor.toString();

        Assertions.assertEquals(omitLineSeparator(whatShouldBePrinted), omitLineSeparator(whatWasPrinted));
    }

    @Test
    @Description("Should return transactions only for account passed.")
    void t12() {
        // given
        transactionRepository.save(new Transaction(1L, "000000", "000001", 10));
        transactionRepository.save(new Transaction(2L, "000001", "000003", 10));
        transactionRepository.save(new Transaction(3L, "000002", "000004", 10));
        transactionRepository.save(new Transaction(4L, "000002", "000000", 10));

        // when
        subject.runTheCommand("view_transactions 000000");

        // then
        String whatShouldBePrinted = "Transactions: \n" +
                "Sender         Receiver       Amount         \n" +
                "000000         000001         10.0           \n" +
                "000002         000000         10.0";
        final String whatWasPrinted = outputStreamCaptor.toString();

        Assertions.assertEquals(omitLineSeparator(whatShouldBePrinted), omitLineSeparator(whatWasPrinted));
    }

    @Test
    @Description("Should return transactions only for account passed.")
    void t13() {
        // given
        transactionRepository.save(new Transaction(1L, "000000", "000001", 10));
        transactionRepository.save(new Transaction(2L, "000001", "000003", 10));
        transactionRepository.save(new Transaction(3L, "000002", "000004", 10));
        transactionRepository.save(new Transaction(4L, "000002", "000000", 10));

        // when
        subject.runTheCommand("view_transactions 000001 000003");

        // then
        String whatShouldBePrinted = "Transactions: \n" +
                "Sender         Receiver       Amount         \n" +
                "000001         000003         10.0           \n";
        final String whatWasPrinted = outputStreamCaptor.toString();

        Assertions.assertEquals(omitLineSeparator(whatShouldBePrinted), omitLineSeparator(whatWasPrinted));
    }

    @Test
    @Description("Should throw an error about account that does not exist")
    void t14() {
        // given
        transactionRepository.save(new Transaction(1L, "000000", "000001", 10));
        transactionRepository.save(new Transaction(2L, "000001", "000003", 10));
        transactionRepository.save(new Transaction(3L, "000002", "000004", 10));
        transactionRepository.save(new Transaction(4L, "000002", "000000", 10));
        // when
        assertThrows(AccountNotFoundException.class, () -> subject.runTheCommand("view_transactions 000000 000099"));
    }

    private String omitLineSeparator(String yourString) {
        return yourString.replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t", "").trim();
    }

}
