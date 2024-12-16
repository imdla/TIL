package org.example.inheritanceprac;

public class Main {
    public static void main(String[] args) {
        System.out.println("parent------------------------");
        Parent parent = new Parent("Parent Public Value");
        System.out.println(parent.publicValue);
        System.out.println(parent.protectedValue);
//        System.out.println(parent.privateValue);
        parent.publicMethod();

        System.out.println();
        System.out.println("child------------------------");
//        Child child = new Child("Child Public Value");
        Child child = new Child("Child Public Value", "Child Value");
        System.out.println(child.publicValue);
        System.out.println(child.protectedValue);
//        System.out.println(child.privateValue);
        child.publicMethod();
        child.childMethod();

        // Animal
        System.out.println();
        System.out.println("Animal------------------------");
        Animal animal = new Animal("ani",6);
        animal.makeSound();

        System.out.println();
        System.out.println("Dog------------------------");
        Dog dog = new Dog("dogi", 5, "puddle");
        dog.makeSound();

        System.out.println();
        System.out.println("Cat------------------------");
        Cat cat = new Cat("cati", 4);
        cat.makeSound();
        cat.boneless();

        // Vehicle
        System.out.println();
        System.out.println("Vehicle------------------------");
        Vehicle avante = new Vehicle("Avante");
        avante.addFuel(300);

        avante.speedUp();
        avante.speedUp();
        avante.speedUp();
        avante.speedDown();

        System.out.println();
        System.out.println("Bus------------------------");
        Bus gosok = new Bus("gosok");
        gosok.addFuel(400);

        gosok.speedUp();
        gosok.speedUp();
        gosok.speedUp();
        gosok.speedDown();

        gosok.boardingPassenger(10);
        gosok.quitPassenger(5);


        System.out.println();
        System.out.println("Truck------------------------");
        Truck truck = new Truck("truck");
        truck.addFuel(300);

        truck.speedUp();
        truck.speedUp();
        truck.speedUp();
        truck.speedDown();

        truck.addBagage(10);
        truck.minusBagage(5);


        // Character
        System.out.println();
        System.out.println("Character------------------------");
        Character character = new Character("character");
        character.levelUp();

        System.out.println();
        System.out.println("Warrior------------------------");
        Warrior warrior = new Warrior("warrior");
        warrior.angerGaugeUp();
        warrior.angerGaugeUp();
        warrior.angerGaugeUp();
        warrior.angerGaugeDown();

        warrior.levelUp();
        warrior.levelUp();

        System.out.println();
        System.out.println("Mage------------------------");
        Mage mage = new Mage("mage");
        mage.manaUp();
        mage.manaUp();
        mage.manaUp();
        mage.manaDown();

        mage.levelUp();

        System.out.println();
        mage.attackTo(warrior);
        warrior.attackTo(mage);
    }
}
