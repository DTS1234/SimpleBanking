package main.upm.simple.banking.ui;

import jdk.jfr.Description;
import main.upm.simple.banking.TestUtil;
import main.upm.simple.banking.model.Account;
import main.upm.simple.banking.persistance.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Collections;

/**
 * @author akazmierczak
 * @create 17.11.2021
 */
class UIInterfaceTest {

    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        TestUtil.deleteFiles();
        accountRepository = AccountRepository.getInstance();
    }



}
