package jk.patterns.chain_of_responsibility.handlers;

import jk.patterns.chain_of_responsibility.Person;
import jk.patterns.chain_of_responsibility.exceptions.InvalidQuantityException;
import jk.patterns.chain_of_responsibility.store.Item;
import jk.patterns.chain_of_responsibility.store.Storage;

import java.util.Map;

public class ItemAvailabilityHandler extends ChainHandler {
    private final Storage storage;

    public ItemAvailabilityHandler(Storage storage, ChainHandler nextHandler) {
        super(nextHandler);
        this.storage = storage;
    }

    @Override
    public boolean handleOrder(Person person, Map<String, Integer> goods) {
        verifyItemAvailability(goods);

        return passHandleAction(person, goods);
    }

    private void verifyItemAvailability(Map<String, Integer> goods) {
        goods.forEach(this::verifyItemAvailability);
    }

    private void verifyItemAvailability(String itemName, int orderedPieces) {
        Item storageItem = this.storage.getItemByName(itemName);
        if (storageItem.getAmountInStorage() < orderedPieces)
            throw new InvalidQuantityException(storageItem.getName(), storageItem.getAmountInStorage(), orderedPieces);
    }
}
