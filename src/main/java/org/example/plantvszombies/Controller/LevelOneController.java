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
        grid.setStyle("-fx-border-color: #81782a;");
        gamePane.getChildren().add(grid);
        for (int row = 0; row < GameRoot.getInstance().getROWS(); row++) {
            for (int col = 0; col < GameRoot.getInstance().getCOLS(); col++) {
                Rectangle tile = new Rectangle(GameRoot.getInstance().getTILE_WIDTH(), GameRoot.getInstance().getTILE_HEIGHT());
                tile.setFill(Color.rgb(0, 0, 0, 0));
                grid.add(tile, col, row);
                //tile.setStroke(Color.DARKGREEN);
                //tile.setStyle("-fx-border-color: #81782a;");
                //tile.setStyle("-fx-border-color: #00ffcc;");
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
                                PeaShooter peaShooter = new PeaShooter(grid.getLayoutY() + finalRow * GameRoot.getInstance().getTILE_HEIGHT() , grid.getLayoutX() + finalCol * GameRoot.getInstance().getTILE_WIDTH()+20);
                                GameRoot.getInstance().loadSunNum();
                                break;
                            }else{
                                break;
                            }
                        case SUNFLOWER:
                            if (GameState.getInstance().getSunPoints()>=50) {
                                GameState.getInstance().setSunPoints(GameState.getInstance().getSunPoints()-50);
                                SunFlower sunFlower = new SunFlower(grid.getLayoutY() + finalRow * GameRoot.getInstance().getTILE_HEIGHT() , grid.getLayoutX() + finalCol * GameRoot.getInstance().getTILE_WIDTH()+20);
                                GameRoot.getInstance().loadSunNum();
                                break;
                            }else{
                                break;
                            }
                    }

                    selectedPlant = PlantType.NONE;
                });
            }
        }



        spawnZombies();
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
