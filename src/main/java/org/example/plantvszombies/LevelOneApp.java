package org.example.plantvszombies;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Plants.PeaShooter;

public class LevelOneApp extends Application {

    private Pane gamePane;
    private final int CELL_WIDTH = 85;
    private final int CELL_HEIGHT = 100;
    private final int GRID_ROWS = 5;
    private final int GRID_COLS = 9;
    private int sun = 100;

    @Override
    public void start(Stage primaryStage) {
        gamePane = new Pane();

        ImageView background = new ImageView(new Image("C:\\Users\\alire\\Desktop\\Advanced programming\\final project\\final-project-AlirezaPakdel\\src\\main\\resources\\images\\DayBackground.png"));
        gamePane.getChildren().add(background);
        GameRoot.setInstance();
        GameRoot.getInstance().setGamePane(gamePane);

        gamePane.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            int col = (int) (e.getX() / CELL_WIDTH);
            int row = (int) ((e.getY() - 80) / CELL_HEIGHT);

            if (row >= 0 && row < GRID_ROWS && col >= 0 && col < GRID_COLS) {
                if (sun >= 50) {
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
        });

        spawnZombies();

        Scene scene = new Scene(gamePane, 900, 600);
        primaryStage.setTitle("Level 1 - Simple PvZ");
        primaryStage.setScene(scene);
        primaryStage.show();
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

    public static void main(String[] args) {
        launch(args);
    }
}
