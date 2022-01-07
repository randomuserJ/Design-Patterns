package jk.patterns.chain_of_responsibility.exceptions;

public class MoneyException extends RuntimeException {
    public MoneyException(double totalPrice, double availableResources) {
        super(String.format("Not enough money to buy selected items. Available resources: (%.2f €), Total price: (%.2f €).",
                availableResources, totalPrice));
    }
}