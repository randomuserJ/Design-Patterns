package jk.patterns.chain_of_responsibility.store;

import jk.patterns.chain_of_responsibility.Person;

import java.util.Map;

public abstract class OrderManager {

    protected final Storage storage;
    protected OrderInfo orderInfo;

    public OrderManager(Storage storage) {
        this.storage = storage;
        this.orderInfo = new OrderInfo("Empty.", 0.0);
    }

    protected void createOrderInfo(Person person, Map<String, Integer> goods) {
        this.orderInfo = new OrderInfo(
                String.format("Order for the customer %s completed.", person.getName()), computeOrderPrice(goods));
    }

    protected double computeOrderPrice(Map<String, Integer> goods) {
        double totalItemsPrice = 0;

        for (Map.Entry<String, Integer> entry : goods.entrySet()) {
            totalItemsPrice += this.storage.getItemByName(entry.getKey()).getDefaultPrice() * entry.getValue();
        }

        return totalItemsPrice;
    }
}
