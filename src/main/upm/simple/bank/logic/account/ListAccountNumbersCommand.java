package main.upm.simple.bank.logic.account;

import main.upm.simple.bank.model.Account;
import main.upm.simple.bank.persistance.AccountRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class ListAccountNumbersCommand {

    /**
     * This command displays a list of all accounts sorted descending by account number.
     */
    public void execute() throws IOException {
        final List<Account> all = AccountRepository.getInstance().findAll();

        if (all.isEmpty()) {
            System.out.println("There are no existing accounts");
        } else {
            System.out.println("Account numbers: ");

            all.stream()
                    .sorted(Comparator.comparing(Account::getAccountNumber).reversed())
                    .map(Account::getAccountNumber)
                    .forEach(
                            accountNumber -> System.out.println("\t" + accountNumber)
                    );
        }
    }

}
