package org.example.plantvszombies.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.Plants.PeaShooter;
import org.example.plantvszombies.Model.Plants.SunFlower;
import org.example.plantvszombies.Model.Zombies.NormalZombie;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class LevelOneController implements Initializable {

    @FXML
    private Pane gamePane;

    @FXML
    private ImageView background;
    private enum PlantType { PEASHOOTER, SUNFLOWER , NONE}
    private PlantType selectedPlant = PlantType.NONE;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        background.setImage(new Image(getClass().getResource("/images/DayBackground.png").toExternalForm()));
        GameRoot.setInstance();
        GameState.NewGameState();
        GameRoot.getInstance().setGamePane(gamePane);


        HBox plantBar = new HBox(20);
        plantBar.setLayoutX(250);
        plantBar.setLayoutY(30);
        plantBar.setPrefHeight(30);
        plantBar.setStyle("-fx-background-color: #dfd360;");


        Image peashooterImage = new Image(getClass().getResource("/images/PeashooterSeedPacket.png").toExternalForm());
        ImageView peashooterIcon = new ImageView(peashooterImage);
        peashooterIcon.setFitWidth(50);
        peashooterIcon.setFitHeight(70);
        peashooterIcon.setOnMouseClicked(e -> {
            selectedPlant = PlantType.PEASHOOTER;
            System.out.println("Peashooter selected");
        });

        Image sunflowerImage = new Image(getClass().getResource("/images/SunflowerSeedPacket.png").toExternalForm());
        ImageView sunflowerIcon = new ImageView(sunflowerImage);
        sunflowerIcon.setFitWidth(50);
        sunflowerIcon.setFitHeight(70);
        sunflowerIcon.setOnMouseClicked(e -> {
            selectedPlant = PlantType.SUNFLOWER;
            System.out.println("Sunflower selected");
        });

        plantBar.getChildren().addAll(peashooterIcon, sunflowerIcon);
        gamePane.getChildren().add(plantBar);


        GridPane grid = new GridPane();
        grid.setLayoutX(220);
        grid.setLayoutY(220);
        //grid.setStyle("-fx-border-color: #81782a;");
        gamePane.getChildren().add(grid);
        for (int row = 0; row < GameRoot.getInstance().getROWS(); row++) {
            for (int col = 0; col < GameRoot.getInstance().getCOLS(); col++) {
                Rectangle tile = new Rectangle(GameRoot.getInstance().getTILE_WIDTH(), GameRoot.getInstance().getTILE_HEIGHT());
                tile.setFill(Color.rgb(0, 0, 0, 0));
                grid.add(tile, col, row);
                tile.setStroke(Color.DARKGREEN);
                int finalRow = row;
                int finalCol = col;

                tile.setOnMouseClicked(e -> {
                    if (selectedPlant == PlantType.NONE) {
                        System.out.println("No plant selected.");
                        return;
                    }

                    switch (selectedPlant) {
                        case PEASHOOTER:
                            if (GameState.getInstance().getSunPoints()>=100) {
                                GameState.getInstance().setSunPoints(GameState.getInstance().getSunPoints()-100);
                                PeaShooter peaShooter = new PeaShooter(grid.getLayoutY() + finalRow * GameRoot.getInstance().getTILE_HEIGHT() +10 , grid.getLayoutX() + finalCol * GameRoot.getInstance().getTILE_WIDTH()+20);
                                GameRoot.getInstance().loadSunNum();
                                GameState.getInstance().getPlantsClass().put(peaShooter.getImageView() , peaShooter);
                                break;
                            }else{
                                break;
                            }
                        case SUNFLOWER:
                            if (GameState.getInstance().getSunPoints()>=50) {
                                GameState.getInstance().setSunPoints(GameState.getInstance().getSunPoints()-50);
                                SunFlower sunFlower = new SunFlower(grid.getLayoutY() + finalRow * GameRoot.getInstance().getTILE_HEIGHT() +10, grid.getLayoutX() + finalCol * GameRoot.getInstance().getTILE_WIDTH()+20);
                                GameRoot.getInstance().loadSunNum();
                                GameState.getInstance().getPlantsClass().put(sunFlower.getImageView() , sunFlower);

                                break;
                            }else{
                                break;
                            }
                    }

                    selectedPlant = PlantType.NONE;
                });
            }
        }

        Timeline fallingSuns = new Timeline(new KeyFrame(Duration.seconds(7), e -> {
            spawnFallingSun();
        }));
        fallingSuns.setCycleCount(Timeline.INDEFINITE);
        fallingSuns.play();



        spawnZombies();
    }

    private void spawnFallingSun() {
        Image sunImg = new Image(getClass().getResource("/images/sun.png").toExternalForm());
        ImageView sun = new ImageView(sunImg);
        sun.setFitWidth(40);
        sun.setFitHeight(40);


        Random rand = new Random();
        double startX = 260 + rand.nextInt(800);
        sun.setLayoutX(startX);
        sun.setLayoutY(-50);

        GameRoot.getInstance().getGamePane().getChildren().add(sun);


        Timeline fall = new Timeline(new KeyFrame(Duration.millis(20), e -> {
            sun.setLayoutY(sun.getLayoutY() + 2);

            if (sun.getLayoutY() >= 700) {
                GameRoot.getInstance().getGamePane().getChildren().remove(sun);
            }
        }));
        fall.setCycleCount(Timeline.INDEFINITE);
        fall.play();


        sun.setOnMouseClicked(e -> {
            GameRoot.getInstance().getGamePane().getChildren().remove(sun);
            GameState.getInstance().setSunPoints(GameState.getInstance().getSunPoints()+25);
            GameRoot.getInstance().loadSunNum();
            fall.stop();
        });
    }


    private void spawnZombies() {
        Timeline spawner = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            Random rand = new Random();
            int row = rand.nextInt(5);
            double startY = 200 + row * 100;
            NormalZombie normalZombie = new NormalZombie(startY);

        }));
        spawner.setCycleCount(10);
        spawner.play();
    }
}
