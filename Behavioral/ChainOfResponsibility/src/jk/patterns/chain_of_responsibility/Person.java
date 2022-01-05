package jk.patterns.chain_of_responsibility;

public class Person {
    private final String name;
    private final String surname;
    private final int age;
    private final double money;

    public Person(String name, String surname, int age, double money) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public double getMoney() {
        return money;
    }
}
