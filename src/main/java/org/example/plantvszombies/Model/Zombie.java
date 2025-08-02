package org.example.plantvszombies.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.plantvszombies.Controller.HelloController;
import org.example.plantvszombies.Controller.PlayerController;
import org.example.plantvszombies.HelloApplication;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.Game.TimelineManager;

import java.io.IOException;

public class Zombie {
    private int health;
    private int speed;
    private int damage;
    private ImageView imageView;
    private Timeline moveTimeline;
    private boolean hasReachedHouse = false;
    private Image image;
    private Image eatImage;

    public Timeline getMoveTimeline() {
        return moveTimeline;
    }

    public Image getImage() {
        return image;
    }

    public Image getEatImage() {
        return eatImage;
    }

    public void setEatImage(Image eatImage) {
        this.eatImage = eatImage;
    }

    public Zombie(int health, int speed, int damage) {
        this.health = health;
        this.speed = speed;
        this.damage = damage;
    }

    public void startMoving() {
        moveTimeline = new Timeline(new KeyFrame(Duration.millis(100), e -> moveZombie()));
        moveTimeline.setCycleCount(Timeline.INDEFINITE);
        moveTimeline.play();
        TimelineManager.getInstance().add(moveTimeline);
    }

    private void moveZombie() {
        if (GameState.getInstance().getZombiesHealth().get(imageView) <= 0) {
            die();
            return;
        }

        for (LawnMower mower : GameRoot.getInstance().mowers) {
            mower.checkCollisionWithZombie(imageView);
        }

        boolean isEating = false;
        for (ImageView image : GameState.getInstance().getPlants()) {
            if (imageView.getBoundsInParent().intersects(image.getBoundsInParent())) {
                isEating = true;
                moveTimeline.stop();
                setGifImageEat();
                Timeline eat = new Timeline(new KeyFrame(Duration.seconds(speed) , event -> eatPlant(image)));
                eat.setCycleCount(Timeline.INDEFINITE);
                eat.play();
            }
        }
        if (!isEating) {
            imageView.setLayoutX(imageView.getLayoutX() - speed);
        }

        if ( imageView.getLayoutX() + 950 < 0) {
            if (!hasReachedHouse) {
                hasReachedHouse = true;
                System.out.println("Zombie reached the house. Game Over!" + imageView.getLayoutX());
                TimelineManager.getInstance().stopAll();
                PlayerController.increaseLoses(HelloController.logInPlayer.getUserName());
                HelloController.logInPlayer = PlayerController.LogIn(HelloController.logInPlayer.getUserName(), HelloController.logInPlayer.getPassword());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Zombie");
                alert.setHeaderText("Zombie reached!");
                alert.setContentText("You Dead");
                alert.show();

                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("HomePageView.fxml"));
                Scene scene;
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Stage stage = (Stage) GameRoot.getInstance().getGamePane().getScene().getWindow();
                if (scene != null) {
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }
    }

    private void setGifImageEat() {
        if (eatImage != null && imageView.getImage()!=eatImage) {
            imageView.setImage(eatImage);
        }
    }

    private void eatPlant(ImageView plant) {
        Integer currentHP = GameState.getInstance().getPlantsHealth().get(plant);

        if (currentHP == null) {
            setGifImage();
            moveTimeline.play();
            return;
        }
            int newHP = currentHP - damage;
            GameState.getInstance().getPlantsHealth().put(plant, newHP);

            if (newHP <= 0) {
                for (Timeline ti : GameState.getInstance().getPlantsClass().get(plant).getAllTimelines() ){
                    ti.stop();
                }
                setGifImage();
                GameRoot.getInstance().setPlantPlaced(GameState.getInstance().getPlantsClass().get(plant).getX() , GameState.getInstance().getPlantsClass().get(plant).getY() , false);
                GameState.getInstance().getPlants().remove(plant);
                GameState.getInstance().getPlantsClass().remove(plant);
                GameState.getInstance().getPlantsHealth().remove(plant);
                GameRoot.getInstance().getGamePane().getChildren().remove(plant);
                moveTimeline.play();
            }

    }

    private void setGifImage() {
        if (imageView.getImage() != image){
            imageView.setImage(image);
        }
    }

    public void setImage(Image image) {
        this.image = image;
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
