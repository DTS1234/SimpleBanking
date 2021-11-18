package main.upm.simple.banking.persistance;

import java.util.List;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public interface Repository<T> {

    T save(T o);
    T findById(Object o);
    List<T> findAll();
    void deleteById(Object o);

}
