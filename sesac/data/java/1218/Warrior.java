package org.example.interfaceprac.character;

public class Warrior extends Character implements RageUsable {
   protected int rage;
   protected int maxRage;

    public Warrior(String name) {
        super(name);
        this.maxHealth = 120;
        this.health = 120;
        this.maxRage = 100;
        this.rage = 0;
    }

    // 공격 기능
    @Override
    public void attack() {
        System.out.println("Attack !");
        increaseRage();
        if (rage == maxRage) {
            rageAttack();
        }
    }

    // 레벨업 기능
    @Override
    public void levelUp() {
        level += 1;
        System.out.println("Level Up");
    }

    // 분노 증가 (매개변수 있음)
    @Override
    public void increaseRage(int amount) {
        rage = Math.min(maxRage, rage + amount);
        System.out.println("Current increased rage: " + rage);
    }

    // 분노 증가 (default)
    @Override
    public void increaseRage() {
        rage = Math.min(maxRage, rage + 10);
        System.out.println("Current increased rage: " + rage);
    }

    // 분노 공격
    @Override
    public void rageAttack() {
        System.out.println("Rage Attack !");
        rage = 0;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("rage : " + rage);
    }
}
