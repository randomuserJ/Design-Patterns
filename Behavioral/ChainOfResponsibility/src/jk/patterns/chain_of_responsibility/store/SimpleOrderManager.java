package jk.patterns.chain_of_responsibility.store;

import jk.patterns.chain_of_responsibility.Person;
import jk.patterns.chain_of_responsibility.exceptions.ExclusiveItemException;
import jk.patterns.chain_of_responsibility.exceptions.InvalidItemException;
import jk.patterns.chain_of_responsibility.exceptions.InvalidQuantityException;
import jk.patterns.chain_of_responsibility.exceptions.MoneyException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SimpleOrderManager {

    private final List<Item> storage;
    private OrderInfo orderInfo;

    public SimpleOrderManager(List<Item> storage) {
        this.storage = storage;
    }

    public void createSimpleOrder(Person person, Map<String, Integer> goods) {
        verifyItemAvailability(goods);
        verifyPriceAmount(person.getMoney(), goods);
        verifyExclusivePermissions(person.getAge(), goods);

        createOrderInfo(person, goods);
    }

    public OrderInfo getOrderInfo() {
        return this.orderInfo;
    }

    private void verifyItemAvailability(Map<String, Integer> goods) {
        goods.forEach(this::verifyItemAvailability);
    }

    private void verifyItemAvailability(String itemName, int orderedPieces) {
        Item storageItem = getItemByName(itemName);
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

        if (goods.keySet().stream().anyMatch(itemName -> getItemByName(itemName).isExclusiveItem()))
            throw new ExclusiveItemException();
    }

    private void createOrderInfo(Person person, Map<String, Integer> goods) {
        this.orderInfo = new OrderInfo(
                String.format("Order for the customer %s completed.", person.getSurname()), computeOrderPrice(goods));
    }


    private Item getItemByName(String itemName) throws InvalidItemException {
        Optional<Item> optionalItem = this.storage.stream()
                .filter(item -> item.getName().equals(itemName))
                .findFirst();

        if (optionalItem.isPresent())
            return optionalItem.get();

        throw new InvalidItemException(itemName);
    }

    private double computeOrderPrice(Map<String, Integer> goods) {
        double totalItemsPrice = 0;

        for (Map.Entry<String, Integer> entry : goods.entrySet()) {
            totalItemsPrice += getItemByName(entry.getKey()).getDefaultPrice() * entry.getValue();
        }

        return totalItemsPrice;
    }
}
