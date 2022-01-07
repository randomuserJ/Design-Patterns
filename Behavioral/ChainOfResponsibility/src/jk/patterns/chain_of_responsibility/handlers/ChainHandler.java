package jk.patterns.chain_of_responsibility.handlers;

import jk.patterns.chain_of_responsibility.Person;

import java.util.Map;

public abstract class ChainHandler {
    private final ChainHandler nextHandler;

    public ChainHandler(ChainHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract boolean handleOrder(Person person, Map<String, Integer> goods);

    protected boolean passHandleAction(Person person, Map<String, Integer> goods) {
        if (nextHandler == null) {
            throw new RuntimeException("Unable to call handler - uninitialized.");
        }

        return nextHandler.handleOrder(person, goods);
    }
}
