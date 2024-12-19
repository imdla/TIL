package org.example.interfaceprac.compositionprac;

import javax.print.attribute.standard.PrinterName;

public class Main {
    public static void main(String[] args) {
        // Pencil & Person
//        Pencil red = new Pencil("red");
//        Person person = new Person("person", red);
//        person.write();
//
//        Pencil blue = new Pencil("blue");
//        person.setPencil(blue);
//        person.write();
//
//        Pencil yellow = new Pencil("yellow");
////        Pencil[] pencils = {red, blue, yellow};
//        Person person2 = new Person("person2", new Pencil[]{red, blue, yellow});
//        person2.writeMany();

        Pencil pencil = new Pencil("red");
        Person p1 = new Person("p1", pencil);
        p1.use();


        // Engine
        System.out.println();
        System.out.println("Engine ------------------");
        Engine engine5 = new Engine(5);
        Engine engine10 = new Engine(10);

        Car car = new Car("car", engine5);
        Car car2 = new Car("car", engine10);

        car.accel();
        car2.accel();

        // Weapon & Character
        System.out.println();
        System.out.println("Weapon & Character ------------------");
        Gun gun = new Gun();
        Sword sword = new Sword();
        Character c1 = new Character();
        Character c2 = new Character(gun);
        Character c3 = new Character(sword);
        c1.attack();
        c2.attack();
        c3.attack();
    }
}
