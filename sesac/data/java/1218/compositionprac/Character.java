package org.example.interfaceprac.compositionprac;

public class Character {
    protected int health;
    protected Weapon weapon;
    protected boolean hasWeapon;

    public Character() {
        this.health = 100;
        this.hasWeapon = false;
    }

    public Character(Weapon weapon) {
        this.health = 100;
        this.weapon = weapon;
        this.hasWeapon = true;
    }

    public void attack() {
        if (hasWeapon) {
            weapon.specialAttack();
        } else {
            basicAttack();
        }
    }

    public void basicAttack() {
        System.out.println("attack !");
    }
}
