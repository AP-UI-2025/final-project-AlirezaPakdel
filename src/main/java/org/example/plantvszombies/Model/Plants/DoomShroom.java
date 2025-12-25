package org.example.plantvszombies.Model.Plants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.plantvszombies.Model.DestructionType;
import org.example.plantvszombies.Model.ExplosivePlant;
import org.example.plantvszombies.Model.Game.GameRoot;

public class DoomShroom extends ExplosivePlant {
    public DoomShroom(double row, double col , int x, int y) {
        super(125, "DoomShroom", 45, 45, DestructionType.AllInFire, x, y);
        Image image = new Image(getClass().getResource("/images/doom-shroom-pvz.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(90);
        imageView.setPreserveRatio(false);
        imageView.setLayoutX(col);
        imageView.setLayoutY(row);
        super.setImageView(imageView);
        setRow(row);
        setCol(col);
        GameRoot.getInstance().getGamePane().getChildren().add(imageView);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
            super.Explosion();
        }));
        timeline.play();
    }
}
