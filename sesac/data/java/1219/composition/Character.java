package org.example.interfaceprac.compositionprac;

public class Character {
    protected int health;
    protected Weapon weapon;

    public Character() {
        this.health = 100;
    }

    public Character(Weapon weapon) {
        this.health = 100;
        this.weapon = weapon;
    }

    public void attack() {
        if (weapon == null) {
            basicAttack();
        } else {
            weapon.specialAttack();
        }
    }

    public void basicAttack() {
        System.out.println("attack !");
    }
}
