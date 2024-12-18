package org.example.interfaceprac.compositionprac;

public class Main {
    public static void main(String[] args) {
        // Pencil & Person
        Pencil pencil = new Pencil("red");
        Person person = new Person(pencil);
        person.writePen();

        // Engine
        Engine engine = new Engine(5);
        Car car = new Car("car", engine);
        car.accel();

        // Weapon & Character
//        Character character = new Character("character");
    }
}
