package org.example.plantvszombies.Model;

import javafx.scene.image.ImageView;

public class ProductionPlant extends Plant{
    private int productionTime;
    private int destructionRate;
    private ImageView imageView;
    private int row, col;

    public ProductionPlant(int solarCost, String plantName , int productionTime, int destructionRate) {
        super(solarCost, plantName);
        this.productionTime = productionTime;
        this.destructionRate = destructionRate;
    }


    protected void produce() {

        System.out.println(getPlantName() + " produced sun at (" + getRow() + "," + getCol() + ")");
    }


    public static void Production(){}

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

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
