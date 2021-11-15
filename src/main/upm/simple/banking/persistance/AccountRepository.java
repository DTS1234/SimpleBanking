package main.upm.simple.banking.persistance;

import main.upm.simple.banking.model.Account;

import java.util.List;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public class AccountRepository  implements Repository<Account>{

    @Override
    public Account save(Account o) {
        return null;
    }

    @Override
    public Account findById(Account o) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        return null;
    }
}
