package org.example.inheritanceprac;

public class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("make a Sound : Cat");
    }

    public void boneless() {
        System.out.println("make a boneless : Cat");
    }
}
