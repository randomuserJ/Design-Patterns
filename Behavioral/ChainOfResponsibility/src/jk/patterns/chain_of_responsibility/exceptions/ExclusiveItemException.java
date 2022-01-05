package jk.patterns.chain_of_responsibility.exceptions;

public class ExclusiveItemException extends RuntimeException {
    public ExclusiveItemException() {
        super("This customer is not capable of buying exclusive items.");
    }
}
