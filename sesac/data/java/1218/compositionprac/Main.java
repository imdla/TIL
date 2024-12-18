package org.example.interfaceprac.compositionprac;

public class Main {
    public static void main(String[] args) {
        // Pencil & Person
        Pencil red = new Pencil("red");
        Person person = new Person("person", red);
        person.write();

        Pencil blue = new Pencil("blue");
        person.setPencil(blue);
        person.write();

        // Engine
        Engine engine = new Engine(5);
        Car car = new Car("car", engine);
        car.accel();

        // Weapon & Character
//        Character character = new Character("character");
    }
}
