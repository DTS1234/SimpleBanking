package main.upm.simple.banking.ui;

import main.upm.simple.banking.logic.account.*;
import main.upm.simple.banking.logic.program.ExitCommand;
import main.upm.simple.banking.logic.program.HelpCommand;
import main.upm.simple.banking.logic.transaction.ExecuteTransactionCommand;
import main.upm.simple.banking.logic.transaction.ViewTransactionsCommand;
import main.upm.simple.banking.persistance.AccountNotFoundException;
import main.upm.simple.banking.persistance.AccountRepository;
import main.upm.simple.banking.persistance.TransactionNotFoundException;

import java.util.Scanner;

/**
 * @author akazmierczak
 * @create 17.11.2021
 */
public class UIInterface {

    private AccountRepository accountRepository = AccountRepository.getInstance();

    public void run() {

        while (true) {
            System.out.println("Enter the command: ");
            String input = readAnInput();
            try {
                runTheCommand(input);
            } catch (InvalidMoneyFormat |
                    InvalidAccountNumber |
                    WrongInputException |
                    AccountNotFoundException |
                    TransactionNotFoundException exception) {
                System.out.println("Try again: ");
                run();
            }
        }
    }

    /**
     * Depending on the input should run appropriate command.
     */
    public void runTheCommand(String input) {
        switch (input) {
            case "help":
                new HelpCommand().execute();
                break;
            case "exit":
                new ExitCommand().execute();
                break;
            case "list_accounts":
                new ListAccountsCommand().execute();
                break;
            case "list_accountnumbers":
                new ListAccountNumbersCommand().execute();
                break;
            case "add_account":
                new AddAccountCommand().execute();
                break;
            default:
                runCommandWithArgument(input);
        }
    }

    private void runCommandWithArgument(String input) {
        if (input.startsWith("delete_account ")) {
            String accountNumber = getArguments(input, 1, "Delete command should receive one argument in the format of account number")[1];
            new DeleteAccountCommand(accountNumber).execute();
        } else if (input.startsWith("add_money ")) {
            String[] arguments = getArguments(input, 2, "Add money command should receive two arguments account number and amount of money.");
            String accountNumber = arguments[0];
            Verify.verifyAccountNumber(accountNumber);
            String moneyString = arguments[1];
            Verify.verifyMoney(moneyString);
            new AddMoneyCommand(accountNumber, Double.parseDouble(moneyString)).execute();
        } else if (input.startsWith("withdraw_money ")) {
            String[] arguments = getArguments(input, 2, "Withdraw money command should receive two arguments account number and amount to withdraw.");
            String accountNumber = arguments[0];
            Verify.verifyAccountNumber(accountNumber);
            String moneyString = arguments[1];
            Verify.verifyMoney(moneyString);
            new WithdrawCommand(accountNumber, Double.parseDouble(moneyString));
        } else if (input.startsWith("execute_transaction ")) {
            String[] arguments = getArguments(input, 3, "Execute transaction command should receive three arguments sender, receiver and money amount");
            String senderAccount = arguments[0];
            Verify.verifyAccountNumber(senderAccount);
            String receiverAccount = arguments[1];
            Verify.verifyAccountNumber(receiverAccount);
            String moneyString = arguments[2];
            Verify.verifyMoney(moneyString);
            new ExecuteTransactionCommand(senderAccount, receiverAccount, Double.parseDouble(moneyString)).execute();
        } else if (input.startsWith("view_account")) {
            String[] arguments = getArguments(input, 1, "View account method should receive one argument with account number");
            String accountNumber = arguments[0];
            Verify.verifyAccountNumber(accountNumber);
            new ViewAccountCommand(accountNumber).execute();
        } else if (input.startsWith("view_transactions")) {
            String[] argumentsViewTransactions = getArgumentsViewTransactions(input, "View transactions command should receive one two or no arguments.");
            ViewTransactionsCommand viewTransactionsCommand = new ViewTransactionsCommand();
            if (argumentsViewTransactions.length == 1) {
                viewTransactionsCommand.execute();
            } else if (argumentsViewTransactions.length == 2) {
                String accountNumber = argumentsViewTransactions[1];
                Verify.verifyAccountNumber(accountNumber);
                viewTransactionsCommand.execute(accountNumber);
            } else {
                String argumentsViewTransaction1 = argumentsViewTransactions[1];
                String argumentsViewTransaction2 = argumentsViewTransactions[2];
                Verify.verifyAccountNumber(argumentsViewTransaction1);
                Verify.verifyAccountNumber(argumentsViewTransaction2);
                viewTransactionsCommand.execute(argumentsViewTransaction1, argumentsViewTransaction2);
            }
        }
    }

    private String[] getArguments(String input, int expectedNumberOfArguments, String errorMessage) {
        String[] s = input.split(" ");
        if (s.length != expectedNumberOfArguments + 1) {
            throw new WrongInputException(errorMessage);
        }
        return s;
    }

    private String[] getArgumentsViewTransactions(String input, String errorMessage) {
        String[] s = input.split(" ");
        if (s.length + 1 < 0 || s.length + 1 > 3) {
            throw new WrongInputException(errorMessage);
        }
        return s;
    }

    /**
     * Should return the character entered by the user.
     */
    private String readAnInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
