package org.example.plantvszombies.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.Plants.PeaShooter;
import org.example.plantvszombies.Model.Zombies.NormalZombie;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class LevelOneController implements Initializable {

    @FXML
    private Pane gamePane;

    @FXML
    private ImageView background;

    private final int CELL_WIDTH = 85;
    private final int CELL_HEIGHT = 100;
    private final int GRID_ROWS = 5;
    private final int GRID_COLS = 9;
    private int sun = 1000;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        background.setImage(new Image(getClass().getResource("/images/DayBackground.png").toExternalForm()));
        GameRoot.setInstance();
        GameState.NewGameState();


        spawnZombies();
        GameRoot.getInstance().setGamePane(gamePane);
    }


    private void spawnZombies() {
        Timeline spawner = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            Random rand = new Random();
            int row = rand.nextInt(5);
            double startY = 210 + row * 100;
            NormalZombie normalZombie = new NormalZombie(startY);

        }));
        spawner.setCycleCount(10);
        spawner.play();
    }
}
