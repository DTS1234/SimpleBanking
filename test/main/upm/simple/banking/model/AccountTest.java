package main.upm.simple.banking.model;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class AccountTest {

    @Test
    @Description("Should print transactions ids.")
    void t30() {

        final String actual = new Account("000000", 10, Arrays.asList(
                1L, 2L, 3L
        )).transactionsString();
        System.out.println(actual);
        Assertions.assertTrue(actual.endsWith("1, 2, 3"));
    }
}