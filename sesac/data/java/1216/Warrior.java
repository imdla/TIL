package org.example.inheritanceprac;

public class Warrior extends Character {
    private int angerGauge = 0;

    public Warrior(String name) {
        super(name);
        this.power = 120;
    }

    // 분노 게이지 증가
    public void angerGaugeUp() {
        angerGauge += 10;
        if (validateAngerGauge(angerGauge)) {
            angerGauge = 100;
            System.out.println("Out of angerGauge !");
            System.out.println("Current angerGauge: " + angerGauge);
        } else {
            System.out.println("Current angerGauge: " + angerGauge);
        }
    }

    // 분노 게이지 감소
    public void angerGaugeDown() {
        angerGauge -= 10;
        if (validateAngerGauge(angerGauge)) {
            angerGauge = 0;
            System.out.println("Out of angerGauge !");
            System.out.println("Current angerGauge: " + angerGauge);
        } else {
            System.out.println("Current angerGauge: " + angerGauge);
        }
    }

    // 레벨업
    @Override
    public void levelUp() {
        level += 1;
        power += 30;
        System.out.println("Current level: " + level + ", Current power: " + power);
    }

    // 분노 확인
    private boolean validateAngerGauge (int angerGauge) {
        if (angerGauge < 0 || angerGauge > 100) {
            return true;
        } else {
            return false;
        }
    }
}
