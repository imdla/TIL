package org.example.interfaceprac.compositionprac;

public abstract class Weapon {
    protected int attackPower;
    protected int stamina;

    public Weapon(int attackPower, int stamina) {
        this.attackPower = attackPower;
        this.stamina = stamina;
    }
}
