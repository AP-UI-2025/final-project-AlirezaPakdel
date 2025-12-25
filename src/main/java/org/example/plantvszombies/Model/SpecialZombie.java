package org.example.plantvszombies.Model;

import javafx.scene.image.Image;
import org.example.plantvszombies.Model.Game.GameState;

public class SpecialZombie extends Zombie{
    private SpecialAbility ability;
    Image Angry;

    public Image getAngry() {
        return Angry;
    }

    public void setAngry(Image angry) {
        Angry = angry;
    }

    public SpecialZombie(int health, int speed, int damage , SpecialAbility ability) {
        super(health, speed, damage);
        this.ability = ability;
    }

    public void UseAbility() {
        if (GameState.getInstance().getZombiesHealth().get(super.getImageView())!=null) {
            if (GameState.getInstance().getZombiesHealth().get(super.getImageView()) <= 100) {
                if (super.getImageView().getImage() != Angry) {
                    super.getImageView().setImage(Angry);
                    setSpeed(5);
                }
            }
        }
    }

    public SpecialAbility getAbility() {
        return ability;
    }

    public void setAbility(SpecialAbility ability) {
        this.ability = ability;
    }
}
