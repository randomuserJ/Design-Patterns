package jk.patterns.chain_of_responsibility.handlers;

import jk.patterns.chain_of_responsibility.Person;

import java.util.Map;

public class FinalizeOrderHandler extends ChainHandler {
    public FinalizeOrderHandler() {
        super(null);
    }

    @Override
    public boolean handleOrder(Person person, Map<String, Integer> goods) {
        // We do not want to continue with the validation. If we came all the way up here, the order is accepted.
        return true;
    }
}
