package main.upm.simple.banking.logic.account;

import main.upm.simple.banking.persistance.AccountRepository;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class AddMoneyCommand {

    private AccountRepository accountRepository;

    public AddMoneyCommand(AccountRepository accountRepositoryInput) {
        this.accountRepository = accountRepositoryInput;
    }
    /**
     * This command adds the specified amount to the specified account and notifies the
     * user afterwards that the addition has been successful. Furthermore, the previous and
     * the new balance are displayed.
     */


    void execute() {

    }

}
