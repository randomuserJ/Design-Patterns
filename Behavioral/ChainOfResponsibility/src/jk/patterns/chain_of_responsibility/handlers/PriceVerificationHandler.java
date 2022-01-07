package jk.patterns.chain_of_responsibility.handlers;

import jk.patterns.chain_of_responsibility.Person;
import jk.patterns.chain_of_responsibility.exceptions.MoneyException;
import jk.patterns.chain_of_responsibility.store.Storage;

import java.util.Map;

public class PriceVerificationHandler extends ChainHandler {

    private final Storage storage;

    public PriceVerificationHandler(Storage storage, ChainHandler nextHandler) {
        super(nextHandler);
        this.storage = storage;
    }

    @Override
    public boolean handleOrder(Person person, Map<String, Integer> goods) {
        verifyPriceAmount(person.getMoney(), goods);

        return passHandleAction(person, goods);
    }

    private void verifyPriceAmount(double availableCustomerResources, Map<String, Integer> goods) {
        double totalItemsPrice = computeOrderPrice(goods);

        if (totalItemsPrice > availableCustomerResources)
            throw new MoneyException(totalItemsPrice, availableCustomerResources);
    }

    private double computeOrderPrice(Map<String, Integer> goods) {
        double totalItemsPrice = 0;

        for (Map.Entry<String, Integer> entry : goods.entrySet()) {
            totalItemsPrice += this.storage.getItemByName(entry.getKey()).getDefaultPrice() * entry.getValue();
        }

        return totalItemsPrice;
    }
}
