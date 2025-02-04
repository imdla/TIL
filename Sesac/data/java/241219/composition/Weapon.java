package org.example.interfaceprac.compositionprac;

public abstract class Weapon implements SpecialAttackable {
    protected int attackPower;
    protected int stamina;

    public Weapon() {
        this.attackPower = 100;
        this.stamina = 100;
    }

    @Override
    public abstract void specialAttack();
}
