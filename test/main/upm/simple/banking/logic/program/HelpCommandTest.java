package main.upm.simple.banking.logic.program;

import jdk.jfr.Description;
import main.upm.simple.banking.TestUtil;
import main.upm.simple.banking.ui.UIInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
class HelpCommandTest {

    private UIInterface subject;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        subject = new UIInterface();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @Description("Should print a message with description of every possible command.")
    void t25() {
        subject.runTheCommand("help");
        final String whatWasPrinted = outputStreamCaptor.toString();
        final String whatShouldBePrinted = "#-----------------------------------------------------------------------------------#\n" +
                " add_account\n" +
                "This command adds a new account to the system.\n" +
                "#-----------------------------------------------------------------------------------#\n" +
                "delete_account <account number>\n" +
                "This command deletes the account which has the specified account number and\n" +
                "notifies the user with a message\n" +
                "#-----------------------------------------------------------------------------------#\n" +
                "add_money <account number> <amount>\n" +
                "This command adds the specified amount to the specified account and notifies the\n" +
                "user afterwards that the addition has been successful.\n" +
                "#-----------------------------------------------------------------------------------#\n" +
                "withdraw_money <account number> <amount>\n" +
                "This command allows the withdrawal of a specified amount from a particular account.\n" +
                "#-----------------------------------------------------------------------------------#\n" +
                "execute_transaction <sending account> <receiving account> <amount>\n" +
                "This command adds the specified amount to the receiving account and subtracts this\n" +
                "amount from the sending account.\n" +
                "#-----------------------------------------------------------------------------------#\n" +
                "execute_transaction <sending account> <receiving account> <amount>\n" +
                "This command adds the specified amount to the receiving account and subtracts this\n" +
                "amount from the sending account.\n" +
                "#-----------------------------------------------------------------------------------#\n" +
                "view_transactions\n" +
                "This command results in the program displaying all transactions that have been\n" +
                "performed by the system\n" +
                "#-----------------------------------------------------------------------------------#\n" +
                "view_transactions <account number>\n" +
                "This command shows all transactions a particular account was involved in (money\n" +
                "received or sent)\n" +
                "#-----------------------------------------------------------------------------------#\n" +
                "view_transactions <account number> <account number>\n" +
                "With this command all transactions are listed in which both accounts are involved.\n" +
                "#-----------------------------------------------------------------------------------#\n" +
                "list_accountnumbers\n" +
                "This command displays a list of all accounts sorted descending by account number\n" +
                "#-----------------------------------------------------------------------------------#\n" +
                "list_accounts\n" +
                "This command displays a list of accounts, including the respective current account\n" +
                "balance\n" +
                "#-----------------------------------------------------------------------------------#\n" +
                "exit\n" +
                "This command shuts down the system\n" +
                "#-----------------------------------------------------------------------------------#\n" +
                "help\n" +
                "This command displays all possible commands with their needed inputs and a short\n" +
                "description to the user\n" +
                "#-----------------------------------------------------------------------------------#";
        assertEquals(TestUtil.omitLineSeparator(whatShouldBePrinted),
                TestUtil.omitLineSeparator(whatWasPrinted));
    }
}
