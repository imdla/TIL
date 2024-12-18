package org.example.interfaceprac.character;

public abstract class Character {
    protected String name;
    protected int level;
    protected int health;
    protected int maxHealth;

    public Character (String name) {
        this.name = name;
        this.level = 1;
    }

    public abstract void attack();

    public abstract void attack(Character target);

    public abstract void takeDamage(int amount);

    public abstract void levelUp();

    public void showInfo() {
        System.out.println("hp : " + health);
        System.out.println("level : " + level);
    }
}
