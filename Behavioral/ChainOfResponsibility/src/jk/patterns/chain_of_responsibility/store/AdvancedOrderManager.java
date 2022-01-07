package jk.patterns.chain_of_responsibility.store;

import jk.patterns.chain_of_responsibility.Person;
import jk.patterns.chain_of_responsibility.handlers.*;

import java.util.Map;

public class AdvancedOrderManager extends OrderManager {
    private final CustomerDatabase database;

    public AdvancedOrderManager(Storage storage, CustomerDatabase database) {
        super(storage);
        this.database = database;
    }

    public void createAdvancedOrder(Person person, Map<String, Integer> goods) {
        ChainHandler chain = new AuthorizationHandler(this.database,
                new ItemAvailabilityHandler(this.storage,
                    new PriceVerificationHandler(this.storage,
                        new ExclusiveItemHandler(this.storage,
                            new FinalizeOrderHandler()))));

        chain.handleOrder(person, goods);

        createOrderInfo(person, goods);
    }
}
