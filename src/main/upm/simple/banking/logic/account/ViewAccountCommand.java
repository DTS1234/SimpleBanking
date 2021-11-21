package main.upm.simple.banking.logic.account;

import main.upm.simple.banking.model.Account;
import main.upm.simple.banking.persistance.AccountRepository;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class ViewAccountCommand {

    private final String accountNumber;

    public ViewAccountCommand(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * This command shows the information of a particular account. This information
     * comprises the account ID and the account balance of that account.
     */
    public void execute() {
        Account byId = AccountRepository.getInstance().findById(accountNumber);
        System.out.println("Account :");
        System.out.println("\t account number : " + byId.getAccountNumber());
        System.out.println("\t balance : " + byId.getBalance());
    }

}
