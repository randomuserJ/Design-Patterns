package jk.patterns.chain_of_responsibility.store;

import jk.patterns.chain_of_responsibility.Person;

import java.util.Map;

public class Store {

    /* RULES */
    // 1. Only registered customer may create an order.
    // 2. Order is denied if some of the items are not in the storage.
    // 3. Order is denied if underage customer want to buy an exclusive item.
    // 4. Order is denied if customer does not have enough money.
    // 5. Order is accepted if no other rules are violated.

    private final String storeName = "The Ultimate Chain Store";
    private final CustomerDatabase database;
    private final SimpleOrderManager simpleOrderManager;
    private final AdvancedOrderManager advancedOrderManager;

    public Store() {
        this.database = new CustomerDatabase();

        Storage storage = new Storage();
        this.simpleOrderManager = new SimpleOrderManager(storage, this.database);
        this.advancedOrderManager = new AdvancedOrderManager(storage, this.database);
    }

    public void registerUser(Person user) {
        this.database.registerCustomer(user);
    }

    public void createSimpleOrder(Person person, Map<String, Integer> goods){

        OrderInfo orderInfo;

        try {
            this.simpleOrderManager.createSimpleOrder(person, goods);

            orderInfo = this.simpleOrderManager.orderInfo;
        }
        catch (Exception e) {
            System.err.printf("Order for the customer %s denied. %s%n", person.getName(), e.getMessage());
            return;
        }

        System.out.println(orderInfo.getOrderInfo());
        System.out.printf("Total order price: %.2f €%n", orderInfo.getPrice());
        System.out.printf("Money before: %.2f €%n", person.getMoney());
        System.out.printf("Money after: %.2f €%n", person.getMoney() - orderInfo.getPrice());
    }

    public void createAdvancedOrder(Person person, Map<String, Integer> goods){

        OrderInfo orderInfo;

        try {
            this.advancedOrderManager.createAdvancedOrder(person, goods);

            orderInfo = this.advancedOrderManager.orderInfo;
        }
        catch (Exception e) {
            System.err.printf("Order for the customer %s denied. %s%n", person.getName(), e.getMessage());
            return;
        }

        System.out.println(orderInfo.getOrderInfo());
        System.out.printf("Total order price: %.2f €%n", orderInfo.getPrice());
        System.out.printf("Money before: %.2f €%n", person.getMoney());
        System.out.printf("Money after: %.2f €%n", person.getMoney() - orderInfo.getPrice());
    }

    public String getStoreName() {
        return storeName;
    }
}
