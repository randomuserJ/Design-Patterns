package jk.patterns.chain_of_responsibility;

import jk.patterns.chain_of_responsibility.store.Store;

import java.util.HashMap;
import java.util.Map;


/**
 * CHAIN OF RESPONSIBILITY
 * The purpose of this design pattern is mainly to divide the implementation into small separated blocks, called Handlers.
 * Instead of writing the whole implementation in the same place, we could define multiple blocks and link them together,
 * so they will create a chain (similar architecture like in the linked list).
 * As the name of the pattern tells us, each handler is responsible for its own little business. It can either break the
 * evaluation or pass it further to the next handler, when its own job is done.
 *
 * Here I have defined two separate principles of how does the Ultimate Chain Store handle the orders from customers.
 * The first one is The Simple Principle - the entire logic is defined in the SimpleOrderManager class.
 * The second "advanced" principle follows the intent of the Chain Of Responsibility pattern. There is a chain defined
 * in the AdvancedOrderManager class, which specifies the order of how is the customer request verified.
 * If there is any problem with the request (see the rules in the Store class), the evaluation is terminated and
 * the exception is thrown.
 */

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

        System.out.println(chainStore.getStoreName().toUpperCase());

        System.out.println("--- Simple principle ---");

        chainStore.createSimpleOrder(alice, aliceOrder);    // unknown Item 'Candy'
        chainStore.createSimpleOrder(bob, bobOrder);        // ok
        chainStore.createSimpleOrder(clair, clairOrder);    // not enough money
        chainStore.createSimpleOrder(david, davidOrder);    // underage
        chainStore.createSimpleOrder(eric, ericOrder);      // unregistered
        chainStore.createSimpleOrder(frank, frankOrder);    // not enough goods in the storage

        System.out.println("--- Chain of Responsibility ---");

        chainStore.createAdvancedOrder(alice, aliceOrder);    // unknown Item 'Candy'
        chainStore.createAdvancedOrder(bob, bobOrder);        // ok
        chainStore.createAdvancedOrder(clair, clairOrder);    // not enough money
        chainStore.createAdvancedOrder(david, davidOrder);    // underage
        chainStore.createAdvancedOrder(eric, ericOrder);      // unregistered
        chainStore.createAdvancedOrder(frank, frankOrder);    // not enough goods in the storage
    }
}
