package org.example.plantvszombies.Model;

public class SpecialZombie extends Zombie{
    private SpecialAbility ability;
    public SpecialZombie(int health, int speed, int damage , SpecialAbility ability) {
        super(health, speed, damage);
        this.ability = ability;
    }

    public static void UseAbility() {}

    public SpecialAbility getAbility() {
        return ability;
    }

    public void setAbility(SpecialAbility ability) {
        this.ability = ability;
    }
}
