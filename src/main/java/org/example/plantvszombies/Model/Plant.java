package org.example.plantvszombies.Model;

public class Plant {

    private String plantName;
    private final int solarCost;


    public Plant(int solarCost, String plantName) {
        this.solarCost = solarCost;
        this.plantName = plantName;
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

