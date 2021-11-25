package main.upm.simple.banking.logic.account;


import main.upm.simple.banking.model.Account;
import main.upm.simple.banking.persistance.AccountRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class AddAccountCommand {

    private final AccountRepository accountRepository = AccountRepository.getInstance();

    /**
     * This command adds a new account to the system. The account numbers start with
     * 000000 and are continuously incrementing (-> next would be 000001). The user is
     * notified that the new account has been created and the account number and balance
     * are shown.
     */
    public void execute() {

        List<Account> currentAccounts = accountRepository.findAll();

        Account account;
        if (currentAccounts.isEmpty()) {
            account = new Account("000000", 0, new ArrayList<>());
            accountRepository.save(account);
        } else {
            account = saveAccountWithNewNumber(currentAccounts);
        }

        String message = String.format("New account created successfully, \n" +
                "\taccount number : %s\n" +
                "\tbalance : 0", account.getAccountNumber());

        System.out.println(message);

    }

    private Account saveAccountWithNewNumber(List<Account> currentAccounts) {
        int size = currentAccounts.size();
        Account lastAccount = currentAccounts.get(size - 1);
        String lastAccountNumber = lastAccount.getAccountNumber();

        int numberToIncrease = getNumberToIncrease(lastAccountNumber);

        // increment by one for the new account
        numberToIncrease += 1;
        String stringWithoutZeroes = String.valueOf(numberToIncrease);

        StringBuilder finalAccountNumberToBeAdded = getFinalAccountNumberToBeAdded(stringWithoutZeroes);

        Account account = new Account(finalAccountNumberToBeAdded.toString(), 0, new ArrayList<>());
        return accountRepository.save(account);
    }

    /**
     * Retrieves the final account number by adding the zeroes to plain integer representing the counter of the new account.
     */
    private StringBuilder getFinalAccountNumberToBeAdded(String stringWithoutZeroes) {
        StringBuilder finalAccountNumberToBeAdded = new StringBuilder();
        if (stringWithoutZeroes.length() != 6) {
            int zeroesToAddInFront = 6 - stringWithoutZeroes.length();
            finalAccountNumberToBeAdded.append("0".repeat(Math.max(0, zeroesToAddInFront)));
        }
        finalAccountNumberToBeAdded.append(stringWithoutZeroes);
        return finalAccountNumberToBeAdded;
    }

    /**
     * Retrieves the plain integer number from the lastly created account.
     */
    private int getNumberToIncrease(String lastAccountNumber) {
        int firstNumberOccurrenceInAccountString = 0;
        for (int i = 0; i < lastAccountNumber.length(); i++) {

            if (lastAccountNumber.charAt(i) != '0') {
                firstNumberOccurrenceInAccountString = i;
                break;
            }

        }

        String number = lastAccountNumber.substring(firstNumberOccurrenceInAccountString);
        return Integer.parseInt(number);
    }


}
