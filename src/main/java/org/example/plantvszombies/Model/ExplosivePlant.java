package org.example.plantvszombies.Model;

public class ExplosivePlant extends Plant {
    private int destructionPower;
    private int destructionRange;
    private DestructionType destructionType;


    public ExplosivePlant(int solarCost, String plantName , int destructionPower, int destructionRange, DestructionType destructionType) {
        super(solarCost, plantName);
        this.destructionPower = destructionPower;
        this.destructionRange = destructionRange;
        this.destructionType = destructionType;
    }

    public static void Explosion(){    }



    public int getDestructionPower() {
        return destructionPower;
    }

    public void setDestructionPower(int destructionPower) {
        this.destructionPower = destructionPower;
    }

    public int getDestructionRange() {
        return destructionRange;
    }

    public void setDestructionRange(int destructionRange) {
        this.destructionRange = destructionRange;
    }

    public DestructionType getDestructionType() {
        return destructionType;
    }

    public void setDestructionType(DestructionType destructionType) {
        this.destructionType = destructionType;
    }
}
