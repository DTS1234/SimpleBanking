package main.upm.simple.banking.logic.program;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class HelpCommand {

    /**
     * Returns the string with description of all commands.
     */
    public void execute() {

        StringBuilder stringBuilder = new StringBuilder();

        String line = "\n#-----------------------------------------------------------------------------------#\n";

        stringBuilder.append(line);

        stringBuilder.append(" add_account\n" +
                "This command adds a new account to the system.");

        stringBuilder.append(line);

        stringBuilder.append("delete_account <account number>\n" +
                "This command deletes the account which has the specified account number and\n" +
                "notifies the user with a message");

        stringBuilder.append(line);

        stringBuilder.append("add_money <account number> <amount>\n" +
                "This command adds the specified amount to the specified account and notifies the\n" +
                "user afterwards that the addition has been successful.");

        stringBuilder.append(line);

        stringBuilder.append("withdraw_money <account number> <amount>\n" +
                "This command allows the withdrawal of a specified amount from a particular account.");

        stringBuilder.append(line);

        stringBuilder.append("execute_transaction <sending account> <receiving account> <amount>\n" +
                "This command adds the specified amount to the receiving account and subtracts this\n" +
                "amount from the sending account.");

        stringBuilder.append(line);

        stringBuilder.append("execute_transaction <sending account> <receiving account> <amount>\n" +
                "This command adds the specified amount to the receiving account and subtracts this\n" +
                "amount from the sending account.");

        stringBuilder.append(line);

        stringBuilder.append("view_transactions\n" +
                "This command results in the program displaying all transactions that have been\n" +
                "performed by the system");

        stringBuilder.append(line);

        stringBuilder.append("view_transactions <account number>\n" +
                "This command shows all transactions a particular account was involved in (money\n" +
                "received or sent)");

        stringBuilder.append(line);

        stringBuilder.append("view_transactions <account number> <account number>\n" +
                "With this command all transactions are listed in which both accounts are involved.");

        stringBuilder.append(line);

        stringBuilder.append("list_accountnumbers\n" +
                "This command displays a list of all accounts sorted descending by account number");

        stringBuilder.append(line);

        stringBuilder.append("list_accounts\n" +
                "This command displays a list of accounts, including the respective current account\n" +
                "balance");

        stringBuilder.append(line);

        stringBuilder.append("exit\n" +
                "This command shuts down the system");

        stringBuilder.append(line);

        stringBuilder.append("help\n" +
                "This command displays all possible commands with their needed inputs and a short\n" +
                "description to the user");

        stringBuilder.append(line);

        System.out.println(stringBuilder.toString());

    }
}
