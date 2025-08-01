package org.example.plantvszombies.Model;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;

public class Sun {
    private final ImageView imageView;
    private final double row, col;

    public Sun(double row, double col) {
        this.row = row;
        this.col = col;

        Image image = new Image(getClass().getResource("/images/sun.png").toExternalForm());
        imageView = new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        imageView.setLayoutX(col  + 30);
        imageView.setLayoutY(row  + 30);

        GameRoot.getInstance().getGamePane().getChildren().add(imageView);

        imageView.setOnMouseClicked(e -> {
            GameState.getInstance().addSun(25);
            GameRoot.getInstance().getGamePane().getChildren().remove(imageView);
            GameRoot.getInstance().loadSunNum();
        });
    }

    public ImageView getImageView() {
        return imageView;
    }


}
