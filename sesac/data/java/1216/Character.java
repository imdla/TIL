package org.example.inheritanceprac;

public class Character {
    protected String name;
    protected int level;
    protected int power;
    protected int maxPower;

    public Character (String name) {
        this.name = name;
        this.level = 1;
        this.power = 100;
        this.maxPower = 100;
    }

    // 공격 기능
    public void attack() {
        System.out.println("attack !");
    }

    // 레벨업
    public void levelUp() {
        level += 1;
        System.out.println("Current level: " + level);
    }

    // 체력 확인
    private boolean validatePower (int power) {
        if (power < 0) {
            return true;
        } else {
            return false;
        }
    }
}
