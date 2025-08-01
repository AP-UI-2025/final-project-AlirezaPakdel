package org.example.plantvszombies.Model.Game;

import javafx.scene.image.ImageView;
import org.example.plantvszombies.Model.Zombie;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState {
    private static GameState instance ;

    private int sunPoints = 2000;
    private List<ImageView> zombies = new ArrayList<>();
    private List<ImageView> plants = new ArrayList<>();
    private Map<ImageView, Integer> plantsHealth = new HashMap<>();
    private Map<ImageView, Integer> zombiesHealth = new HashMap<>();

    private GameState() {}
    public static void NewGameState(){
        instance = new GameState();
    }
    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }
    public static void setInstance(){
        instance = new GameState();
    }

    public List<ImageView> getPlants() {
        return plants;
    }

    public List<ImageView> getZombies() {
        return zombies;
    }

    public void setZombies(List<ImageView> zombies) {
        this.zombies = zombies;
    }

    public void setSunPoints(int sunPoints) {
        this.sunPoints = sunPoints;
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

    public Map<ImageView, Integer> getPlantsHealth() {
        return plantsHealth;
    }

    public void setPlantsHealth(Map<ImageView, Integer> plantsHealth) {
        this.plantsHealth = plantsHealth;
    }

    public Map<ImageView, Integer> getZombiesHealth() {
        return zombiesHealth;
    }

    public void setZombiesHealth(Map<ImageView, Integer> zombiesHealth) {
        this.zombiesHealth = zombiesHealth;
    }
}

