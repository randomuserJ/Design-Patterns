package jk.patterns.chain_of_responsibility.store;

import jk.patterns.chain_of_responsibility.exceptions.InvalidItemException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Storage {
    private List<Item> offeredItems;

    public Storage() {
        initialize();
    }

    public Item getItemByName(String itemName) throws InvalidItemException {
        Optional<Item> optionalItem = this.offeredItems.stream()
                .filter(item -> item.getName().equals(itemName))
                .findFirst();

        if (optionalItem.isPresent())
            return optionalItem.get();

        throw new InvalidItemException(itemName);
    }

    private void initialize() {
        this.offeredItems = new ArrayList<>();

        this.offeredItems.add(new Item("Apple", 48, 0.45, false));
        this.offeredItems.add(new Item("Video Game", 12, 19.99, false));
        this.offeredItems.add(new Item("Bread", 33, 1.29, false));
        this.offeredItems.add(new Item("Energy Drink", 23, 0.99, true));
        this.offeredItems.add(new Item("Whiskey", 3, 15.99, true));
    }
}
