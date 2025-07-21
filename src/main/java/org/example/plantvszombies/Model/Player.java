package org.example.plantvszombies.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private String userName;
    private String password;
    private int level;
    private int plants;

    public Player(String userName, String password , int level , int plants) {
        this.userName = userName;
        this.password = password;
        this.level = level;
        this.plants = plants;


    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public int getLevel() {
        return level;
    }
    public int getPlants() {
        return plants;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPlants(int plants) {
        this.plants = plants;
    }

   /* public String toFileFormat() {
        return userName + "," + password + "," + level + "," + plants;
    }

    public static Player fromFileFormat(String line) {
        String[] parts = line.split(",");
        ArrayList<String> plants;
        if (parts.length > 3 && !parts[3].isEmpty()) {
            plants = new ArrayList<>(Arrays.asList(parts[3].split("\\|")));
        }else {
            plants = new ArrayList<>();
        }
        return new Player(parts[0], parts[1], Integer.parseInt(parts[2] ) , plants);
    }*/


}
