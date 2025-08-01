package org.example.plantvszombies.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.Game.TimelineManager;

public class Zombie {
    private int health;
    private int speed;
    private int damage;
    private ImageView imageView;
    private Timeline moveTimeline;


    public Zombie(int health, int speed, int damage) {
        this.health = health;
        this.speed = speed;
        this.damage = damage;
    }

    public void startMoving() {
        moveTimeline = new Timeline(new KeyFrame(Duration.millis(100), e -> moveZombie()));
        moveTimeline.setCycleCount(Timeline.INDEFINITE);
        TimelineManager.getInstance().add(moveTimeline);
        moveTimeline.play();
    }

    private void moveZombie() {
        if (GameState.getInstance().getZombiesHealth().get(imageView) <= 0) {
            die();
            return;
        }

        boolean isEating = false;
        for (ImageView image : GameState.getInstance().getPlants()) {
            if (imageView.getBoundsInParent().intersects(image.getBoundsInParent())) {
                isEating = true;
                moveTimeline.stop();
                Image imageEat = new Image(getClass().getResource("/images/NormalEat.gif").toExternalForm());
                setImage(imageEat);
                Timeline eat = new Timeline(new KeyFrame(Duration.seconds(speed) , event -> eatPlant(image)));
                eat.setCycleCount(Timeline.INDEFINITE);
                eat.play();
            }
        }
        if (!isEating) {
            imageView.setLayoutX(imageView.getLayoutX() - speed);
        }

        if (imageView.getX() < 0) {
            System.out.println("Zombie reached the house. Game Over!");
            moveTimeline.stop();
        }
    }

    private void eatPlant(ImageView plant) {
        Integer currentHP = GameState.getInstance().getPlantsHealth().get(plant);

        if (currentHP == null) {
            Image image = new Image(getClass().getResource("/images/NormalZombie.gif").toExternalForm());
            setImage(image);
            moveTimeline.play();
            return;
        }
            int newHP = GameState.getInstance().getPlantsHealth().get(plant) - damage;
            GameState.getInstance().getPlantsHealth().put(plant, newHP);

            if (newHP <= 0) {
                for (Timeline ti : GameState.getInstance().getPlantsClass().get(plant).getAllTimelines() ){
                    ti.stop();
                }
                Image image = new Image(getClass().getResource("/images/NormalZombie.gif").toExternalForm());
                setImage(image);
                GameState.getInstance().getPlants().remove(plant);
                GameState.getInstance().getPlantsHealth().remove(plant);
                GameRoot.getInstance().getGamePane().getChildren().remove(plant);
                moveTimeline.play();
            }

    }

    private void setImage(Image image) {
        if (imageView.getImage() != image) {
            imageView.setImage(image);
        }
    }

    public void die() {
        GameRoot.getInstance().getGamePane().getChildren().remove(imageView);
        GameState.getInstance().getZombiesHealth().remove(imageView);
        TimelineManager.getInstance().getTimelines().remove(moveTimeline);
        moveTimeline.stop();
    }
    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
