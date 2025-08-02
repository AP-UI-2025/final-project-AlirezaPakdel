package org.example.plantvszombies.Model.Plants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.plantvszombies.Model.BulletType;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.Game.TimelineManager;
import org.example.plantvszombies.Model.WarriorPlant;

public class SnowPea extends WarriorPlant {
    public SnowPea(double row, double col , int x, int y) {
        super(175 , "SnowPea", 1, 5 , BulletType.Snowy , x, y);
        Image image = new Image(getClass().getResource("/images/SnowPea.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(60);
        imageView.setPreserveRatio(true);
        imageView.setLayoutX(col);
        imageView.setLayoutY(row);
        super.setImageView(imageView);
        GameState.getInstance().getPlants().add(imageView);
        GameState.getInstance().getPlantsHealth().put(imageView, 5);
        setRow(row);
        setCol(col);
        GameRoot.getInstance().getGamePane().getChildren().add(imageView);
        StartPeaShooter();
    }

    private void StartPeaShooter() {
        Timeline shooter = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
            boolean zombieInRow = false;
            for (ImageView zombie : GameState.getInstance().getZombies()) {
                if (Math.abs(zombie.getLayoutY()  - getRow()) <= 30) {
                    zombieInRow = true;
                    break;
                }
            }

            if (zombieInRow) {
                if (super.getShootTimeline() == null) {
                    startShooting();
                }
            } else {
                if (super.getShootTimeline() != null) {
                    super.getShootTimeline().stop();
                    super.setShootTimeline(null);
                }
            }
        }));
        shooter.setCycleCount(Timeline.INDEFINITE);
        shooter.play();
        TimelineManager.getInstance().add(shooter);
        super.getAllTimelines().add(shooter);
    }
}
