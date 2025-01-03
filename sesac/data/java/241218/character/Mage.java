package org.example.interfaceprac.character;

public class Mage extends Character implements ManaUsable, DamageTakable {
    protected int mana;
    protected int maxMana;

    public Mage(String name) {
        super(name);
        maxHealth = 75;
        health = 75;
        maxMana = 100;
        mana = 0;
    }

    // 캐릭터를 생성 기능
    static Warrior makeWarrior(String name) {
        // 로직
        return new Warrior(name);
    }

    // 공격 (default)
    @Override
    public void attack() {
        System.out.println("Attack !");
        increaseMana();
    }

    // 공격 (매개변수 있음)
    @Override
    public void attack(DamageTakable target) {
        // target은 Character 타입으로 Character 메서드만 사용 가능
        System.out.println("Attack " + target);
        target.takeDamage(20);
    }

    // 데미지 받음
    @Override
    public void takeDamage(int amount) {
        decreaseMana();
        health -= amount + 10;
    }

    // 레벨업 기능
    @Override
    public void levelUp() {
        level += 1;
        health += 15;
        System.out.println("Level Up");
    }

    // 마나 증가
    @Override
    public void increaseMana() {
        mana += 20;
        mana = Math.min(maxMana, mana + 10);
    }

    // 마나 감소
    @Override
    public void decreaseMana() {
        mana = Math.max(0, mana - 10);
    }
}
