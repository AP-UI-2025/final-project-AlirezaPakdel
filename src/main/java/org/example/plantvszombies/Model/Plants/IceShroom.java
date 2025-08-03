package org.example.plantvszombies.Model.Plants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.plantvszombies.Model.DestructionType;
import org.example.plantvszombies.Model.ExplosivePlant;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Plant;

public class IceShroom extends ExplosivePlant {
    public IceShroom(double row, double col , int x, int y) {
        super(75, "IceShroom", 1, 45, DestructionType.SquareIce, x, y);
        Image image = new Image(getClass().getResource("/images/ice_Shroom.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(60);
        imageView.setPreserveRatio(false);
        imageView.setLayoutX(col);
        imageView.setLayoutY(row);
        super.setImageView(imageView);
        setRow(row);
        setCol(col);
        GameRoot.getInstance().getGamePane().getChildren().add(imageView);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            super.Explosion();
        }));
        timeline.play();
    }
}
