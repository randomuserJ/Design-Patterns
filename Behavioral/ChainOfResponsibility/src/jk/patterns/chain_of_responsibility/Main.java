package jk.patterns.chain_of_responsibility;

import jk.patterns.chain_of_responsibility.store.Store;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Store chainStore = new Store();

        Person alice = new Person("Alice", "Adventure", 12, 12.39);
        Person bob = new Person("Bob", "Bridge", 27, 124.87);
        Person clair = new Person("Clair", "Clever", 36, 63.29);
        Person david = new Person("David", "Dust", 9, 20.00);
        Person eric = new Person("Eric", "Enterprise", 24, 174.57);
        Person frank = new Person("Frank", "Funny", 17, 94.11);

        Map<String, Integer> aliceOrder = new HashMap<>() {};
        aliceOrder.put("Apple", 2);
        aliceOrder.put("Candy", 3);
        aliceOrder.put("Bread", 1);

        Map<String, Integer> bobOrder = new HashMap<>() {};
        bobOrder.put("Video Game", 3);
        bobOrder.put("Energy Drink", 4);
        bobOrder.put("Apple", 1);

        Map<String, Integer> clairOrder = new HashMap<>() {};
        clairOrder.put("Bread", 20);
        clairOrder.put("Video Game", 10);

        Map<String, Integer> davidOrder = new HashMap<>() {};
        davidOrder.put("Whiskey", 1);

        Map<String, Integer> ericOrder = new HashMap<>() {};
        ericOrder.put("Apple", 2);

        Map<String, Integer> frankOrder = new HashMap<>() {};
        frankOrder.put("Energy Drink", 25);

        chainStore.registerUser(alice);
        chainStore.registerUser(bob);
        chainStore.registerUser(clair);
        chainStore.registerUser(david);
        chainStore.registerUser(frank);

        chainStore.createSimpleOrder(alice, aliceOrder);    // unknown Item 'Candy'
        chainStore.createSimpleOrder(bob, bobOrder);        // ok
        chainStore.createSimpleOrder(clair, clairOrder);    // not enough money
        chainStore.createSimpleOrder(david, davidOrder);    // underage
        chainStore.createSimpleOrder(eric, ericOrder);      // unregistered
        chainStore.createSimpleOrder(frank, frankOrder);    // not enough goods in the storage
    }
}
