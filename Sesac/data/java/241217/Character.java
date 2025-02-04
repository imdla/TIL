package org.example.interfaceprac;

public abstract class Character {
    protected String name;
    protected int level;
    protected int power;

    public Character(String name) {
        this.name = name;
        level = 1;
        power = 100;
    }

    public void attack() {
        System.out.println("attack !");
    };

    public abstract void levelUp();
}
