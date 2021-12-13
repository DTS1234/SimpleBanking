package main.upm.simple.bank.logic.account;

import main.upm.simple.bank.model.Account;
import main.upm.simple.bank.persistance.AccountRepository;

import java.io.IOException;
import java.util.List;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class ListAccountsCommand {

    /**
     * This command displays a list of accounts, including the respective current account
     * balance.
     */
    public void execute() throws IOException {
        final List<Account> all = AccountRepository.getInstance().findAll();

        if (all.isEmpty()) {
            System.out.println("There are no existing accounts");
        } else {
            System.out.println("Account numbers: ");
            all.forEach(
                    account -> {
                        String format = String.format("account number: %s | balance: %s", account.getAccountNumber(), account.getBalance());
                        System.out.println(format);
                    }
            );
        }
    }
}
