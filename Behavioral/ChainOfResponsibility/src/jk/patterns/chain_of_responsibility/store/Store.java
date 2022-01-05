package jk.patterns.chain_of_responsibility.store;

import jk.patterns.chain_of_responsibility.Person;
import jk.patterns.chain_of_responsibility.exceptions.AuthorizationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Store {

    /* RULES */
    // 1. Only registered customer may create an order.
    // 2. Order is denied if some of the items are not in the storage.
    // 3. Order is denied if underage customer want to buy an exclusive item.
    // 4. Order is denied if customer does not have enough money.
    // 5. Order is accepted if no other rules are violated.
    // 6. Information about each completed order is written in the log.

    private final String storeName = "The Ultimate Chain Store";
    private final CustomerDatabase database;
    private final SimpleOrderManager simpleOrderManager;
    private List<Item> storage;

    public Store() {
        initializeStorage();

        this.database = new CustomerDatabase();
        this.simpleOrderManager = new SimpleOrderManager(this.storage);
    }

    private void initializeStorage() {
        this.storage = new ArrayList<>();

        this.storage.add(new Item("Apple", 48, 0.45, false));
        this.storage.add(new Item("Video Game", 12, 19.99, false));
        this.storage.add(new Item("Bread", 33, 1.29, false));
        this.storage.add(new Item("Energy Drink", 23, 0.99, true));
        this.storage.add(new Item("Whiskey", 3, 15.99, true));
    }

    public void registerUser(Person user) {
        this.database.registerCustomer(user);
    }

    public void createSimpleOrder(Person person, Map<String, Integer> goods){

        OrderInfo orderInfo;

        try {
            verifyCustomerRegistration(person);

            this.simpleOrderManager.createSimpleOrder(person, goods);

            orderInfo = this.simpleOrderManager.getOrderInfo();
        }
        catch (Exception e) {
            System.err.printf("Order for the customer %s denied. %s%n", person.getName(), e.getMessage());
            return;
        }

        System.out.printf("Order for the customer %s completed.%n", person.getName());
        System.out.printf("Total order price: %.2f €%n", orderInfo.getPrice());
        System.out.printf("Money before: %.2f €%n", person.getMoney());
        System.out.printf("Money after: %.2f €%n", person.getMoney() - orderInfo.getPrice());
    }

    private void verifyCustomerRegistration(Person person) throws AuthorizationException {
        if (!customerIsRegistered(person))
            throw new AuthorizationException(person.getName() + " " + person.getSurname());
    }

    private boolean customerIsRegistered(Person user) {
        return this.database.customerIsRegistered(user.getName() + user.getSurname());
    }
}
