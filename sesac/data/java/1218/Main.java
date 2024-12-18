package org.example.interfaceprac.character;

public class Main {
    public static void main(String[] args) {
        Warrior warrior = new Warrior("warrir");
        System.out.println(warrior.level);

        warrior.attack();
        warrior.increaseRage(100);
        warrior.attack();
    }
}
