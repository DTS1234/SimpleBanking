package main.upm.simple.banking.logic.account;

import jdk.jfr.Description;
import main.upm.simple.banking.TestUtil;
import main.upm.simple.banking.model.Account;
import main.upm.simple.banking.persistance.AccountRepository;
import main.upm.simple.banking.persistance.TransactionRepository;
import main.upm.simple.banking.ui.UIInterface;
import main.upm.simple.banking.ui.WrongInputException;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
class AddAccountCommandTest {

    private AccountRepository accountRepository;
    private UIInterface actual;

    private TransactionRepository transactionRepository;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeAll
    static void beforeAll() {
        TestUtil.deleteFiles();
    }

    @BeforeEach
    void setUp() {
        actual = new UIInterface();
        System.setOut(new PrintStream(outputStreamCaptor));
        accountRepository = AccountRepository.getInstance();
    }

    @AfterEach
    void tearDown() {
        TestUtil.deleteFiles();
    }

    @Test
    @Description("Should create account with 000000 when no accounts file exist.")
    void t1() throws Exception {
        //when
        actual.runTheCommand("add_account");
        //then
        Account actual = accountRepository.findById("000000");
        assertEquals("000000", actual.getAccountNumber());
        assertEquals(0, actual.getBalance());
        assertEquals(Collections.emptyList(), actual.getTransactionIds());
    }

    @Test
    @Description("Should create account with account number properly adjusted.")
    void t2() throws Exception {
        //given
        accountRepository.save(new Account("000000", 2, Collections.emptyList()));
        accountRepository.save(new Account("000001", 2, Collections.emptyList()));
        accountRepository.save(new Account("000004", 2, Collections.emptyList()));
        accountRepository.save(new Account("000007", 2, Collections.emptyList()));
        //when
        actual.runTheCommand("add_account");
        //then
        Account byId = AccountRepository.getInstance().findById("000008");
        assertEquals("000008", byId.getAccountNumber());
    }

    @Test
    @Description("Should create account with 000000 when accounts file exist but it's empty")
    void t3() throws Exception {
        // given
        accountRepository.save(new Account("000000", 1, Collections.emptyList()));
        accountRepository.deleteById("000000");
        //when
        actual.runTheCommand("add_account");
        //then
        Account actual = accountRepository.findById("000000");
        assertEquals("000000", actual.getAccountNumber());
        assertEquals(0, actual.getBalance());
        assertEquals(Collections.emptyList(), actual.getTransactionIds());
    }

    @Test
    @Description("Should throw an error when wrong input for add_account")
    void t4() throws Exception {
        // when
        actual.runTheCommand("add_acount");
        // then
        String whatShouldBePrinted = "Input entered is not recognized as a command, enter 'help' to display possible options.\n";
        final String whatWasPrinted = outputStreamCaptor.toString().replaceAll("\r", "");
        assertEquals(whatWasPrinted, whatShouldBePrinted);
    }

    @Test
    @Description("Should increase the account number respectively")
    void t29() throws Exception {

        // given
        accountRepository.save(new Account("99999", 10, Collections.emptyList()));

        // when
        new AddAccountCommand().execute();

        // then
        final Account byId = accountRepository.findById("100000");
        assertEquals(0, byId.getBalance());

    }

}
