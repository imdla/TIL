package org.example.interfaceprac;

public class Warrior extends Character {
    private int rage;
    private int maxRage;

    public Warrior(String name) {
        super(name);
        power = 120;
        rage = 0;
        maxRage = 100;
    }

    @Override
    public void levelUp() {
        level += 1;
        power += 30;
        System.out.println("level: " + level);
        System.out.println("power: " + power);
    }
}
