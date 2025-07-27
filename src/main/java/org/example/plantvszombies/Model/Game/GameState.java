package org.example.plantvszombies.Model.Game;

public class GameState {
    private static final GameState instance = new GameState();

    private int sunPoints = 50;

    private GameState() {}

    public static GameState getInstance() {
        return instance;
    }

    public int getSunPoints() {
        return sunPoints;
    }

    public void addSun(int amount) {
        sunPoints += amount;
        System.out.println("Sun increased to: " + sunPoints);

    }

    public boolean spendSun(int amount) {
        if (sunPoints >= amount) {
            sunPoints -= amount;
            System.out.println("Sun decreased to: " + sunPoints);
            return true;
        }
        return false;
    }
}

