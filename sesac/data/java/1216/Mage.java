package org.example.inheritanceprac;

public class Mage extends Character {
    private int mana;

    public Mage(String name) {
        super(name);
        this.power = 70;
    }

    // 마나 증가
    public void manaUp() {
        mana += 10;
        if (validateMana(mana)) {
            mana = 100;
            System.out.println("Out of mana !");
            System.out.println("Current mana: " + mana);
        } else {
            System.out.println("Current mana: " + mana);
        }
    }

    // 마나 감소
    public void manaDown() {
        mana -= 10;
        if (validateMana(mana)) {
            mana = 0;
            System.out.println("Out of mana !");
            System.out.println("Current mana: " + mana);
        } else {
            System.out.println("Current mana: " + mana);
        }
    }

    // 레벨업
    @Override
    public void levelUp() {
        level += 1;
        power += 15;
        System.out.println("Current level: " + level + ", Current power: " + power);
    }

    // 공격 기능
    @Override
    public void attack() {
        System.out.println("attack !");
        manaUp();

        if (mana >= 50) {
            System.out.println("mana attack !");
        }
    }

    // 마나 확인
    private boolean validateMana (int mana) {
        if (mana < 0 || mana > 100) {
            return true;
        } else {
            return false;
        }
    }
}
