package org.example.plantvszombies.Model;

import javafx.animation.Timeline;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Plant {
    private List<Timeline> allTimelines;
    private String plantName;
    private final int solarCost;
    private double row, col;
    private ImageView imageView;

    public Plant(int solarCost, String plantName) {
        allTimelines = new ArrayList<>();
        this.solarCost = solarCost;
        this.plantName = plantName;
    }

    public List<Timeline> getAllTimelines() {
        return allTimelines;
    }

    public void setAllTimelines(List<Timeline> allTimelines) {
        this.allTimelines = allTimelines;
    }

    public double getRow() {
        return row;
    }

    public void setRow(double row) {
        this.row = row;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public double getCol() {
        return col;
    }

    public void setCol(double col) {
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

