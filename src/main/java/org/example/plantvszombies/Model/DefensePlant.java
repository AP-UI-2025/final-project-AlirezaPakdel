package org.example.plantvszombies.Model;

public class DefensePlant extends Plant {
    private int health;


    public DefensePlant(int solarCost, String plantName , int health , int x , int y) {
        super(solarCost, plantName , x, y);
        this.health = health;
    }

    public static void BlockZombie(){}


    public int getHealth() {
        return health;
    }

}
