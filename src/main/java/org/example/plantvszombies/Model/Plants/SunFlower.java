package org.example.plantvszombies.Model.Plants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.plantvszombies.Model.ProductionPlant;
import org.example.plantvszombies.Model.TimelineManager;

public class SunFlower extends ProductionPlant {
    public SunFlower(int row, int col) {
        super(50, "SunFlower", 10, 25);
        Image image = new Image(getClass().getResource("/images/sunflower-pvz.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(60);
        imageView.setPreserveRatio(true);
        super.setImageView(imageView);
        setCol(col);
        setRow(row);
    }

    public void startProduction(TimelineManager manager) {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(this.getProductionTime()),
                e -> produce()
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        manager.add(timeline);
    }



}
