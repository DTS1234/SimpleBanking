package main.upm.simple.banking.ui;

import jdk.jfr.Description;
import main.upm.simple.banking.TestUtil;
import main.upm.simple.banking.logic.program.ExitProgram;
import main.upm.simple.banking.model.Account;
import main.upm.simple.banking.persistance.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author akazmierczak
 * @create 17.11.2021
 */
class UIInterfaceTest {

    private AccountRepository accountRepository;
    @Mock
    private UIInterfaceReadImpl read = new UIInterfaceReadImpl();

    private UIInterface subject;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        TestUtil.deleteFiles();
        accountRepository = AccountRepository.getInstance();
        subject = new UIInterface(read);
    }

    @Test
    @Description("Should ask for input until exit command is not invoked.")
    void t37() throws Exception {
        when(read.readAnInput()).thenReturn("add_account", "exit");

        assertThrows(ExitProgram.class, () -> subject.run());
    }

    @Test
    @Description("Should throw InvalidAccountNumber call for another input an exit.")
    void t38() throws Exception {
        when(read.readAnInput())
                .thenReturn("execute_transaction 2131242 123123 10.0", "exit");
        assertThrows(ExitProgram.class, () -> subject.run());
    }

    @Test
    @Description("Should call multiple methods and exit.")
    void t39() throws Exception {
        when(read.readAnInput())
                .thenReturn("execute_transaction 2131242 123123 10.0", "add_account",
                        "list_accounts", "list_accountnumbers", "exit");
        assertThrows(ExitProgram.class, () -> subject.run());
    }
}
