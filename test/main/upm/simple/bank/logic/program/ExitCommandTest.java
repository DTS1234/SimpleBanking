package main.upm.simple.bank.logic.program;

import main.upm.simple.bank.ui.UIInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
class ExitCommandTest {

    @Test
    void t26() throws Exception {
        assertThrows(ExitProgram.class, () -> new UIInterface().runTheCommand("exit"));
    }
}
