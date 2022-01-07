package jk.patterns.chain_of_responsibility.store;

import jk.patterns.chain_of_responsibility.Person;
import jk.patterns.chain_of_responsibility.exceptions.AuthorizationException;
import jk.patterns.chain_of_responsibility.exceptions.ExclusiveItemException;
import jk.patterns.chain_of_responsibility.exceptions.InvalidQuantityException;
import jk.patterns.chain_of_responsibility.exceptions.MoneyException;

import java.util.Map;

public class SimpleOrderManager extends OrderManager {

    private final CustomerDatabase database;

    public SimpleOrderManager(Storage storage, CustomerDatabase database) {
        super(storage);
        this.database = database;
    }

    public void createSimpleOrder(Person person, Map<String, Integer> goods) {
        verifyCustomerRegistration(person);
        verifyItemAvailability(goods);
        verifyPriceAmount(person.getMoney(), goods);
        verifyExclusivePermissions(person.getAge(), goods);

        createOrderInfo(person, goods);
    }

    private void verifyCustomerRegistration(Person person) throws AuthorizationException {
        if (!customerIsRegistered(person))
            throw new AuthorizationException(person.getName() + " " + person.getSurname());
    }

    private boolean customerIsRegistered(Person user) {
        return this.database.customerIsRegistered(user.getName() + user.getSurname());
    }

    private void verifyItemAvailability(Map<String, Integer> goods) {
        goods.forEach(this::verifyItemAvailability);
    }

    private void verifyItemAvailability(String itemName, int orderedPieces) {
        Item storageItem = this.storage.getItemByName(itemName);
        if (storageItem.getAmountInStorage() < orderedPieces)
            throw new InvalidQuantityException(storageItem.getName(), storageItem.getAmountInStorage(), orderedPieces);
    }

    private void verifyPriceAmount(double availableCustomerResources, Map<String, Integer> goods) {
        double totalItemsPrice = computeOrderPrice(goods);

        if (totalItemsPrice > availableCustomerResources)
            throw new MoneyException(totalItemsPrice, availableCustomerResources);
    }

    private void verifyExclusivePermissions(int customerAge, Map<String, Integer> goods) {
        if (customerAge >= 18)
            return;

        if (goods.keySet().stream().anyMatch(itemName -> this.storage.getItemByName(itemName).isExclusiveItem()))
            throw new ExclusiveItemException();
    }
}
