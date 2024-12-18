package org.example.interfaceprac.compositionprac;

public class Character {
    protected int health;
    protected Weapon weapon;

    public Character(Weapon weapon) {
        this.health = 100;
        this.weapon = weapon;
    }

    public void attack() {
        System.out.println("attack !");
    }
}
