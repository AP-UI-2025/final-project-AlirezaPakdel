package org.example.plantvszombies.Model.Plants;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.plantvszombies.Model.DefensePlant;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;

public class WallNut extends DefensePlant {
    public WallNut(double row, double col , int x, int y) {
        super(50, "WallNut", 30, x, y);
        Image image2 = new Image(getClass().getResource("/images/wallNutSecond.gif").toExternalForm());
        super.setScondImage(image2);
        Image image = new Image(getClass().getResource("/images/wallnut.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(60);
        imageView.setLayoutX(col);
        imageView.setLayoutY(row);
        GameRoot.getInstance().getGamePane().getChildren().add(imageView);
        imageView.setPreserveRatio(true);
        super.setImageView(imageView);
        GameState.getInstance().getPlants().add(imageView);
        GameState.getInstance().getPlantsHealth().put(imageView, 50);
        setCol(col);
        setRow(row);
    }
}
