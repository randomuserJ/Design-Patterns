package jk.patterns.chain_of_responsibility.store;

import jk.patterns.chain_of_responsibility.Person;

import java.util.ArrayList;
import java.util.List;

public class CustomerDatabase {
    private final List<String> registeredCustomers = new ArrayList<>();

    public void registerCustomer(Person user) {
        registeredCustomers.add(user.getName() + user.getSurname());
    }

    public boolean customerIsRegistered(String username) {
        return registeredCustomers.contains(username);
    }
}
