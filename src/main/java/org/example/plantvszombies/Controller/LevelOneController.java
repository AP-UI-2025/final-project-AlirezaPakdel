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
import org.example.plantvszombies.Model.Plants.PeaShooter;

import java.net.URL;
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
    private int sun = 100;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        background.setImage(new Image(getClass().getResource("/images/DayBackground.png").toExternalForm()));

        GameRoot.setInstance();
        GameRoot.getInstance().setGamePane(gamePane);

        gamePane.addEventFilter(MouseEvent.MOUSE_CLICKED, this::handleMouseClick);

        spawnZombies();
    }

    private void handleMouseClick(MouseEvent e) {
        int col = (int) (e.getX() / CELL_WIDTH);
        int row = (int) ((e.getY() - 80) / CELL_HEIGHT);

        if (row >= 0 && row < GRID_ROWS && col >= 0 && col < GRID_COLS && sun >= 50) {
            PeaShooter p = new PeaShooter(row, col);
            ImageView iv = new ImageView(new Image(getClass().getResource("/peashooter.png").toExternalForm()));
            iv.setLayoutX(col * CELL_WIDTH + 30);
            iv.setLayoutY(row * CELL_HEIGHT + 80);
            p.setImageView(iv);
            gamePane.getChildren().add(iv);
            sun -= 50;
            p.Shoot();
        }
    }

    private void spawnZombies() {
        Timeline spawner = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            int row = (int) (Math.random() * GRID_ROWS);
            ImageView zombie = new ImageView(new Image(getClass().getResource("/zombie.png").toExternalForm()));
            zombie.setLayoutX(850);
            zombie.setLayoutY(row * CELL_HEIGHT + 80);
            gamePane.getChildren().add(zombie);

            Timeline move = new Timeline(new KeyFrame(Duration.millis(200), ev -> {
                zombie.setLayoutX(zombie.getLayoutX() - 2);
            }));
            move.setCycleCount(Timeline.INDEFINITE);
            move.play();
        }));
        spawner.setCycleCount(10);
        spawner.play();
    }
}
