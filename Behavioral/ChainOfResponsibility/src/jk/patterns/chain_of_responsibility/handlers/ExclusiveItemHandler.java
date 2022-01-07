package jk.patterns.chain_of_responsibility.handlers;

import jk.patterns.chain_of_responsibility.Person;
import jk.patterns.chain_of_responsibility.exceptions.ExclusiveItemException;
import jk.patterns.chain_of_responsibility.store.Storage;

import java.util.Map;

public class ExclusiveItemHandler extends ChainHandler {

    private final Storage storage;

    public ExclusiveItemHandler(Storage storage, ChainHandler nextHandler) {
        super(nextHandler);
        this.storage = storage;
    }

    @Override
    public boolean handleOrder(Person person, Map<String, Integer> goods) {
        verifyExclusivePermissions(person.getAge(), goods);

        return passHandleAction(person, goods);
    }

    private void verifyExclusivePermissions(int customerAge, Map<String, Integer> goods) {
        if (customerAge >= 18)
            return;

        if (goods.keySet().stream().anyMatch(itemName -> this.storage.getItemByName(itemName).isExclusiveItem()))
            throw new ExclusiveItemException();
    }
}
