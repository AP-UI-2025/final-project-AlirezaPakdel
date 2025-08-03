package org.example.plantvszombies.Model.Plants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.ProductionPlant;
import org.example.plantvszombies.Model.Game.TimelineManager;
import org.example.plantvszombies.Model.Sun;

public class SunFlower extends ProductionPlant {
    public SunFlower(double row, double col , int x , int y) {
        super(50, "SunFlower", 15, 25 , x , y);
        Image image = new Image(getClass().getResource("/images/sunflower-pvz.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(60);
        imageView.setLayoutX(col);
        imageView.setLayoutY(row);
        GameRoot.getInstance().getGamePane().getChildren().add(imageView);
        imageView.setPreserveRatio(true);
        super.setImageView(imageView);
        GameState.getInstance().getPlants().add(imageView);
        GameState.getInstance().getPlantsHealth().put(imageView, 5);
        setCol(col);
        setRow(row);
        startProduction();
    }


    public void startProduction() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(this.getProductionTime()),
                e -> produce()
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        TimelineManager.getInstance().add(timeline);
        super.getAllTimelines().add(timeline);
    }



}
