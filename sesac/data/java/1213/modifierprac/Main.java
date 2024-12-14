package org.example.modifierprac;

public class Main {
    public static void main(String[] args) {
        // Person
        Person person = new Person("john", 20, 100);
        System.out.println(person.getName());
        System.out.println(person.age);
        System.out.println(person.height);

        person.runShow();
        person.walk();
        person.study();

        // BankAccount
        System.out.println("BankAccount -------------------------------------------------");
        BankAccount ba = new BankAccount();
        ba.setPassword("1111");
        ba.deposit(5000);
        ba.withdrawal(3000, "111");
        ba.showBalance("1111");
    }
}
