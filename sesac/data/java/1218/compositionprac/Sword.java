package org.example.interfaceprac.compositionprac;

public class Sword extends Weapon implements SpecialAttackable {

    public Sword(int attackPower, int stamina) {
        super(attackPower, stamina);
    }

    @Override
    public void specialAttack() {
        System.out.println("Gun : special attack !");
    }
}
