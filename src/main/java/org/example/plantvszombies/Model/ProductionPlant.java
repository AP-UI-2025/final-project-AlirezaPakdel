package org.example.plantvszombies.Model;

import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.plantvszombies.Model.Game.GameRoot;

public class ProductionPlant extends Plant{
    private int productionTime;
    private int destructionRate;

    public ProductionPlant(int solarCost, String plantName , int productionTime, int destructionRate) {
        super(solarCost, plantName);
        this.productionTime = productionTime;
        this.destructionRate = destructionRate;
    }


    public void produce() {
        Sun sun = new Sun(getRow(), getCol());


        javafx.animation.Timeline removeTimeline = new javafx.animation.Timeline(new javafx.animation.KeyFrame(Duration.seconds(5), e -> {
            GameRoot.getInstance().getGamePane().getChildren().remove(sun.getImageView());
        }));
        removeTimeline.setCycleCount(1);
        removeTimeline.play();
    }



    public static void Production(){}





    public int getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(int productionTime) {
        this.productionTime = productionTime;
    }

    public int getDestructionRate() {
        return destructionRate;
    }

    public void setDestructionRate(int destructionRate) {
        this.destructionRate = destructionRate;
    }
}
