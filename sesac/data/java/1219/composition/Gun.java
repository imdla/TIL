package org.example.interfaceprac.compositionprac;

public class Gun extends Weapon {
    protected int attackPower;
    protected int stamina;

    public Gun() {
        this.attackPower = 150;
        this.stamina = 150;
    }

    @Override
    public void specialAttack() {
        System.out.println("Gun : special attack !");
    }
}
