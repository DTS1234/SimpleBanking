package main.upm.simple.banking.logic.account;

import main.upm.simple.banking.model.Account;
import main.upm.simple.banking.persistance.AccountRepository;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class ListAccountNumbersCommand {

    /**
     * This command displays a list of all accounts sorted descending by account number.
     */
    public void execute() {

        System.out.println("Account numbers: ");

        AccountRepository.getInstance().findAll()
                .stream()
                .map(Account::getAccountNumber)
                .forEach(
                        accountNumber -> System.out.println("\t" + accountNumber)
                );
    }

}
