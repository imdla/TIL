package org.example.inheritanceprac;

public class Warrior extends Character {
    private int angerGauge;
    private static final int MAX_ANGER_GAUGE = 100;

    public Warrior(String name) {
        super(name);
        this.power = 120;
        this.maxPower = 120;
        this.angerGauge = 0;
    }

    // 분노 게이지 증가
    public void angerGaugeUp() {
        angerGauge += 10;
        if (validateAngerGauge(angerGauge)) {
            angerGauge = MAX_ANGER_GAUGE;
            System.out.println("Out of anger gauge !");
        }
        System.out.println("Current anger gauge: " + angerGauge);
    }

    // 분노 게이지 감소
    public void angerGaugeDown() {
        angerGauge -= 10;
        if (validateAngerGauge(angerGauge)) {
            angerGauge = 0;
            System.out.println("Out of anger gauge !");
        }
        System.out.println("Current anger gauge: " + angerGauge);
    }

    // 레벨업
    @Override
    public void levelUp() {
        level += 1;
        power += 30;
        maxPower += 30;
        System.out.println("Current level: " + level + ", Current power: " + power);
    }

    // 공격 기능
    @Override
    public void attack() {
        System.out.println("attack !");
        angerGaugeUp();

        if (angerGauge >= 50) {
            System.out.println("Anger attack !");
        }
    }

    // 분노 확인
    private boolean validateAngerGauge (int angerGauge) {
        if (angerGauge < 0 || angerGauge > MAX_ANGER_GAUGE) {
            return true;
        } else {
            return false;
        }
    }
}
