package org.example.interfaceprac.compositionprac;

public class Sword extends Weapon {
    protected int attackPower;
    protected int stamina;

    public Sword() {
        this.attackPower = 110;
        this.stamina = 110;
    }

    @Override
    public void specialAttack() {
        System.out.println("Sword : special attack !");
    }
}
