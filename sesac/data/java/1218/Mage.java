package org.example.interfaceprac.character;

public class Mage extends Character {

    public Mage(String name) {
        super(name);
        maxHealth = 75;
        health = 75;
    }

    @Override
    public void attack() {

    }

    @Override
    public void attack(Character target) {
        // target은 Character 타입으로 Character 메서드만 사용 가능
        System.out.println("Attack " + target);
        target.takeDamage(20);
    }

    @Override
    public void takeDamage(int amount) {
        health -= amount + 10;
    }

    @Override
    public void levelUp() {

    }
}
