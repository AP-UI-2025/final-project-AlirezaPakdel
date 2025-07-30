package org.example.plantvszombies.Model.Game;

import javafx.scene.layout.Pane;

public class GameRoot {
    private static GameRoot instance;

    private Pane gamePane;

    private GameRoot() {}

    public static GameRoot getInstance() {
        if (instance == null) {
            instance = new GameRoot();
        }
        return instance;
    }

    public void setGamePane(Pane pane) {
        this.gamePane = pane;
    }

    public Pane getGamePane() {
        return gamePane;
    }
}

