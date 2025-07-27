package org.example.plantvszombies.Model.Game;

import javafx.scene.layout.Pane;

public class GameRoot {
    private static final GameRoot instance = new GameRoot();

    private Pane gamePane;

    private GameRoot() {}

    public static GameRoot getInstance() {
        return instance;
    }

    public void setGamePane(Pane pane) {
        this.gamePane = pane;
    }

    public Pane getGamePane() {
        return gamePane;
    }
}

