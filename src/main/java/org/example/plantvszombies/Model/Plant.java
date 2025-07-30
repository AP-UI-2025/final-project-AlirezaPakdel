package org.example.plantvszombies.Model;

public class Plant {

    private String plantName;
    private final int solarCost;
    private int row, col;

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

