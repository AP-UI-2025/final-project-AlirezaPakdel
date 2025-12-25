package org.example.plantvszombies.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;

import java.util.ArrayList;
import java.util.List;

public class LawnMower {
    private ImageView imageView;
    private Timeline moveTimeline;
    private boolean activated = false;

    public LawnMower(double x, double y) {
        Image image = new Image(getClass().getResource("/images/LawnMower.png").toExternalForm());
        imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        GameRoot.getInstance().getGamePane().getChildren().add(imageView);
    }

    public void checkCollisionWithZombie(ImageView zombieImage) {
        if (!activated && imageView.getBoundsInParent().intersects(zombieImage.getBoundsInParent())) {
            activate();
        }
    }

    public void activate() {
        if (activated) return;
        activated = true;

        moveTimeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            List<ImageView> toRemove = new ArrayList<>();
            imageView.setLayoutX(imageView.getLayoutX() + 10);


            for (ImageView z : GameState.getInstance().getZombiesHealth().keySet()) {
                if (imageView.getBoundsInParent().intersects(z.getBoundsInParent())) {
                    toRemove.add(z);
                }
            }

            for (ImageView z : toRemove) {
                GameState.getInstance().getZombies().remove(z);
                GameState.getInstance().getZombiesClass().get(z).die();
                GameRoot.getInstance().getGamePane().getChildren().remove(z);
            }



            if (imageView.getLayoutX() > 1000) {
                moveTimeline.stop();
                GameRoot.getInstance().getGamePane().getChildren().remove(imageView);
            }
        }));
        moveTimeline.setCycleCount(Timeline.INDEFINITE);
        moveTimeline.play();
    }

    public ImageView getImageView() {
        return imageView;
    }

    public boolean isActivated() {
        return activated;
    }
}
