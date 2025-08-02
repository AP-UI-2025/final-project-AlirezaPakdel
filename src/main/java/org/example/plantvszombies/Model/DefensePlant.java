package org.example.plantvszombies.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.example.plantvszombies.Model.Game.GameState;

import java.beans.EventHandler;

public class DefensePlant extends Plant {
    private int health;
    private Image scondImage;

    public Image getScondImage() {
        return scondImage;
    }

    public void setScondImage(Image scondImage) {
        this.scondImage = scondImage;
    }

    public DefensePlant(int solarCost, String plantName , int health , int x , int y ) {
        super(solarCost, plantName , x, y);
        this.health = health;
        BlockZombie();
    }

    public void BlockZombie(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e->{
            if (this.scondImage != null) {
                if (super.getImageView().getImage() != this.scondImage) {
                    if (GameState.getInstance().getPlantsHealth().get(super.getImageView().getImage()) <= 25) {
                        super.getImageView().setImage(scondImage);
                    }
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    public int getHealth() {
        return health;
    }

}
