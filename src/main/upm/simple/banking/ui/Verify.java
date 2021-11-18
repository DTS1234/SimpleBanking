package main.upm.simple.banking.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verify {

    public static boolean onlyDigits(String str) {
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        if (str == null) {
            return false;
        }
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static void verifyMoney(String moneyString) throws InvalidMoneyFormat {
        try {
            double v = Double.parseDouble(moneyString);
            if (v < 0) {
                throw new InvalidMoneyFormat();
            }
        } catch (NumberFormatException formatException) {
            throw new InvalidMoneyFormat();

        }
    }

    public static void verifyAccountNumber(String accountNumber) {
        if (accountNumber.length() != 6) {
            throw new InvalidAccountNumber(accountNumber);
        }
        if (!onlyDigits(accountNumber)) {
            throw new InvalidAccountNumber(accountNumber);
        }
    }
}