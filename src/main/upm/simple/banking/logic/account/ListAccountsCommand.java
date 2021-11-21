package main.upm.simple.banking.logic.account;

import main.upm.simple.banking.persistance.AccountRepository;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class ListAccountsCommand {

    /**
     * This command displays a list of accounts, including the respective current account
     * balance.
     */
    public void execute() {
        AccountRepository.getInstance().findAll().forEach(
                account -> {
                    String format = String.format("account number: %s | balance: %s | transactions : %s", account.getAccountNumber(), account.getBalance(), account.getTransactionIds().isEmpty() ? "empty" : account.transactionsString());
                    System.out.println(format);
                }
        );
    }

}
