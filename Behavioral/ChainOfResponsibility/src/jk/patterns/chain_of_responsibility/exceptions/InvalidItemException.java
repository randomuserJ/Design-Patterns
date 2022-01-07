package jk.patterns.chain_of_responsibility.exceptions;

public class InvalidItemException extends RuntimeException {
    public InvalidItemException(String itemName) {
        super(String.format("Item '%s' is not offered in The Chain Store.", itemName));
    }
}
