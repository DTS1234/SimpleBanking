package main.upm.simple.bank;

import main.upm.simple.bank.model.Transaction;
import main.upm.simple.bank.persistance.TransactionRepository;

/**
 * @author akazmierczak
 * @create 18.11.2021
 */
public class Workspace {

    public static void main(String[] args) throws Exception {

        TransactionRepository instance = TransactionRepository.getInstance();

        instance.save(new Transaction(1L, "000001", "0000002", 1.0));
        instance.save(new Transaction(1L, "000001", "0000002", 10.0));
        instance.save(new Transaction(2L, "000001", "0000002", 1.0));
        instance.save(new Transaction(2L, "000003", "0000004", 2.0));
        instance.save(new Transaction(3L, "000001", "0000002", 111.0));
        instance.save(new Transaction(4L, "000003", "0000004", 223.0));
        instance.findAll().forEach(System.out::println);

        instance.deleteById(2L);
        instance.deleteById(1L);
        instance.findAll().forEach(System.out::println);

        instance.deleteById(3L);
        instance.findAll().forEach(System.out::println);

        instance.deleteById(4L);
        instance.findAll().forEach(System.out::println);

    }

}
