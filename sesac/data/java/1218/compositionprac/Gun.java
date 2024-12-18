package org.example.interfaceprac.compositionprac;

public class Gun extends Weapon implements SpecialAttackable {

    public Gun(int attackPower, int stamina) {
        super(attackPower, stamina);
    }

    @Override
    public void specialAttack() {
        System.out.println("Gun : special attack !");
    }
}
