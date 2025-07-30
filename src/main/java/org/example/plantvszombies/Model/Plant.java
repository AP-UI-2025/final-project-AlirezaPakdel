package org.example.plantvszombies.Model;

import javafx.scene.image.ImageView;

public class Plant {

    private String plantName;
    private final int solarCost;
    private int row, col;
    private ImageView imageView;

    public Plant(int solarCost, String plantName) {
        this.solarCost = solarCost;
        this.plantName = plantName;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public int getSolarCost() {
        return solarCost;
    }

    @Override
    public String toString() {
        return plantName;
    }

}

