package jk.patterns.chain_of_responsibility.exceptions;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException(String itemName, int availableInStorage, int ordered) {
        super(String.format("Lack of items '%s' in the storage. Available: (%d), Ordered: (%d).",
                itemName, availableInStorage, ordered));
    }
}
