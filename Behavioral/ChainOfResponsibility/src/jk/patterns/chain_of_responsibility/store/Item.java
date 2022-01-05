package jk.patterns.chain_of_responsibility.store;

public class Item {
    private final String name;
    private final int amountInStorage;
    private final double defaultPrice;
    private final boolean exclusiveItem;

    public Item(String name, int amountInStorage, double defaultPrice, boolean exclusiveItem) {
        this.name = name;
        this.amountInStorage = amountInStorage;
        this.defaultPrice = defaultPrice;
        this.exclusiveItem = exclusiveItem;
    }

    public String getName() {
        return name;
    }

    public int getAmountInStorage() {
        return amountInStorage;
    }

    public boolean isExclusiveItem() {
        return exclusiveItem;
    }

    public double getDefaultPrice() {
        return defaultPrice;
    }
}
