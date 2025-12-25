package org.example.plantvszombies.Model.Zombies;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.SpecialAbility;
import org.example.plantvszombies.Model.SpecialZombie;

public class NewsPaperZombie extends SpecialZombie {
    public NewsPaperZombie(double y) {
        super(150, 1, 2, SpecialAbility.GetAngry);
        Image image = new Image(getClass().getResource("/images/newspaperZombieFirst.gif").toExternalForm());
        super.setImage(image);
        Image Angry = new Image(getClass().getResource("/images/angryWithOutNewsPaper.gif").toExternalForm());
        super.setAngry(Angry);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(140);
        imageView.setFitWidth(130);
        imageView.setPreserveRatio(true);
        imageView.setLayoutX(1100);
        //imageView.setY(200);
        imageView.setLayoutY(y);
        super.setImageView(imageView);
        GameState.getInstance().getZombies().add(imageView);
        GameState.getInstance().getZombiesHealth().put(imageView, getHealth());
        GameRoot.getInstance().getGamePane().getChildren().add(imageView);
        GameState.getInstance().getZombiesClass().put(imageView, this);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            super.UseAbility();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        super.startMoving();

    }
}
