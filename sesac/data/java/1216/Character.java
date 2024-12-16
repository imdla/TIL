package org.example.inheritanceprac;

public class Character {
    protected String name;
    protected int level = 1;
    protected int power = 100;

    public Character (String name) {
        this.name = name;
    }

    // 기본 공격 기능
    public void attackTo(Character args) {
        System.out.println(this.name + " attack to " + args.name);
        args.power -= 10;
        System.out.println(args.name + "'s Current power: " + args.power);
    }

    // 레벨업
    public void levelUp() {
        level += 1;
        System.out.println("Current level: " + level);
    }
}
