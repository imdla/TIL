package org.example.interfaceprac;

public class Cat extends Animal implements Grrable {

    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("mew");
    }

    @Override
    public void grr() {
        System.out.println("Grr");
    }
}