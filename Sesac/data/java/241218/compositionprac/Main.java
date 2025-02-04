package org.example.interfaceprac.compositionprac;

import javax.print.attribute.standard.PrinterName;

public class Main {
    public static void main(String[] args) {
        // Pencil & Person
        Pencil red = new Pencil("red");
        Person person = new Person("person", red);
        person.write();

        Pencil blue = new Pencil("blue");
        person.setPencil(blue);
        person.write();

        Pencil yellow = new Pencil("yellow");
//        Pencil[] pencils = {red, blue, yellow};
        Person gom = new Person("gom", new Pencil[]{red, blue, yellow});
        gom.writeMany();

        // Engine
        Engine engine = new Engine(5);
        Car car = new Car("car", engine);
        car.accel();

        // Weapon & Character
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
