package main.upm.simple.bank.ui;

import jdk.jfr.Description;
import main.upm.simple.bank.TestUtil;
import main.upm.simple.bank.logic.program.ExitProgram;
import main.upm.simple.bank.persistance.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
    void t35() throws Exception {
        when(read.readAnInput()).thenReturn("add_account", "exit");
        assertThrows(ExitProgram.class, () -> subject.run());
    }

    @Test
    @Description("Should throw InvalidAccountNumber call for another input an exit.")
    void t36() throws Exception {
        when(read.readAnInput())
                .thenReturn("execute_transaction 2131242 123123 10.0", "exit");
        assertThrows(ExitProgram.class, () -> subject.run());
    }

    @Test
    @Description("Should call both commands without and with arguments and quit")
    void t45() throws Exception {
        when(read.readAnInput())
                .thenReturn("add_account", "add_money 000000 1", "exit");
        assertThrows(ExitProgram.class, () -> subject.run());
    }

}
