package org.example.plantvszombies.Model;

import javafx.scene.image.ImageView;
import org.example.plantvszombies.Model.Game.GameRoot;

public class ProductionPlant extends Plant{
    private int productionTime;
    private int destructionRate;

    public ProductionPlant(int solarCost, String plantName , int productionTime, int destructionRate) {
        super(solarCost, plantName);
        this.productionTime = productionTime;
        this.destructionRate = destructionRate;
    }


    protected void produce() {
        Sun sun = new Sun(getRow(), getCol());

        GameRoot.getInstance().getGamePane().getChildren().add(sun.getImageView());
        sun.playDropAnimation();
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
