package jk.patterns.chain_of_responsibility.handlers;

import jk.patterns.chain_of_responsibility.Person;
import jk.patterns.chain_of_responsibility.exceptions.AuthorizationException;
import jk.patterns.chain_of_responsibility.store.CustomerDatabase;

import java.util.Map;

public class AuthorizationHandler extends ChainHandler {
    private final CustomerDatabase database;

    public AuthorizationHandler(CustomerDatabase database, ChainHandler nextHandler) {
        super(nextHandler);
        this.database = database;
    }

    @Override
    public boolean handleOrder(Person person, Map<String, Integer> goods) {
        verifyCustomerRegistration(person, database);

        return passHandleAction(person, goods);
    }

    private void verifyCustomerRegistration(Person person, CustomerDatabase database) throws AuthorizationException {
        if (!customerIsRegistered(person, database))
            throw new AuthorizationException(person.getName() + " " + person.getSurname());
    }

    private boolean customerIsRegistered(Person user, CustomerDatabase database) {
        return database.customerIsRegistered(user.getName() + user.getSurname());
    }
}
