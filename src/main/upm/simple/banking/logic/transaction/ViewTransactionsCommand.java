package main.upm.simple.banking.logic.transaction;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class ViewTransactionsCommand  {


    /**
     * Returns a table with sending account, receiving account
     * and amount as columns.
     */
    public String execute() {
        return null;
    }

    /**
     * Returns a table with sending account, receiving account and
     * amount as columns for account with a given accountNumber.
     */
    public String execute(String accountNumber) {
        return null;
    }

    /**
     * Returns a table with sending account, receiving account and
     * amount as columns for accounts given. All transactions that both accounts were involved in.
     */
    public String execute(String sendersAccount, String receiversAccount) {
        return null;
    }

}
