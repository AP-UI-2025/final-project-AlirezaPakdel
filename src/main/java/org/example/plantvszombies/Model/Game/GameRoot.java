package org.example.plantvszombies.Model.Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.example.plantvszombies.Model.Plants.PeaShooter;
import org.example.plantvszombies.Model.Plants.SunFlower;

public class GameRoot {
    private final int ROWS = 5;
    private final int COLS = 9;
    private final int TILE_WIDTH = 80;
    private final int TILE_HEIGHT = 100;
    private enum PlantType { PEASHOOTER, SUNFLOWER , NONE}
    private PlantType selectedPlant = PlantType.NONE;
    Label sunLabel;


    private static GameRoot instance;

    private Pane gamePane ;

    private GameRoot() {

    }

    public static GameRoot getInstance() {
        if (instance == null) {
            instance = new GameRoot();
        }
        return instance;
    }

    public static void setInstance(){
        instance = new GameRoot();
    }

    public void setGamePane(Pane pane) {
        this.gamePane = pane;


        VBox sunNum = new VBox(10);
        sunNum.setLayoutX(200);
        sunNum.setLayoutY(30);
        sunNum.setPrefHeight(5);
        //sunNum.setStyle("-fx-background-color: #81782a;");

        /*Timeline fallingSuns = new Timeline(new KeyFrame(Duration.seconds(7), e -> {
        }));
        fallingSuns.setCycleCount(Timeline.INDEFINITE);
        fallingSuns.play();*/

        Image sunImage = new Image(getClass().getResource("/images/Sun.png").toExternalForm());
        ImageView sunView = new ImageView(sunImage);
        sunView.setFitWidth(40);
        sunView.setFitHeight(40);
        sunView.setPreserveRatio(false);
        sunNum.getChildren().add(sunView);

        sunLabel = new Label("Sun : " + GameState.getInstance().getSunPoints());
        sunLabel.setStyle("-fx-text-fill: yellow;");
        sunNum.getChildren().add(sunLabel);
        gamePane.getChildren().add(sunNum);


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
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Rectangle tile = new Rectangle(TILE_WIDTH, TILE_HEIGHT);
                tile.setFill(Color.rgb(0, 0, 0, 0));
                grid.add(tile, col, row);
                tile.setStroke(Color.DARKGREEN);
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
                                loadSunNum();
                                PeaShooter peaShooter = new PeaShooter(grid.getLayoutY() + finalRow * TILE_HEIGHT , grid.getLayoutX() + finalCol * TILE_WIDTH+20);
                                break;
                            }else{
                                break;
                            }
                        case SUNFLOWER:
                            if (GameState.getInstance().getSunPoints()>=50) {
                                GameState.getInstance().setSunPoints(GameState.getInstance().getSunPoints()-50);
                                loadSunNum();
                                SunFlower sunFlower = new SunFlower(grid.getLayoutY() + finalRow * TILE_HEIGHT , grid.getLayoutX() + finalCol * TILE_WIDTH+20);
                                break;
                            }else{
                                break;
                            }
                    }

                    selectedPlant = PlantType.NONE;
                });
            }
        }
    }

    private void loadSunNum() {
        sunLabel.setText("Sun : " + GameState.getInstance().getSunPoints());
    }

    public Pane getGamePane() {
        return gamePane;
    }
}

