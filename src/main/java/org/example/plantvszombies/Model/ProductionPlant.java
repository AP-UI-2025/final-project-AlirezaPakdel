package org.example.plantvszombies.Model;

public class ProductionPlant extends Plant{
    private int productionTime;
    private int destructionRate;

    public ProductionPlant(int solarCost, String plantName , int productionTime, int destructionRate) {
        super(solarCost, plantName);
        this.productionTime = productionTime;
        this.destructionRate = destructionRate;
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
