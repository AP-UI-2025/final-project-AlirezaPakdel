package org.example.plantvszombies.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.plantvszombies.HelloApplication;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.Game.TimelineManager;
import org.example.plantvszombies.Model.Plants.*;
import org.example.plantvszombies.Model.Zombies.*;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class NightLevelThreeController implements Initializable {

    private int currentWave = 0;
    private final int totalWaves = 5;
    private final int[] zombiesPerWave = {2 , 4 , 6};

    @FXML
    private Pane gamePane;

    @FXML
    private ImageView background;
    private enum PlantType {FUMESHROOM , DOOM , SCAREDY , ICESHROOM , PUFFSHROMM, SUNSHROOM , NONE}

    private PlantType selectedPlant = PlantType.NONE;
    private Label waveLabel = new Label("Remaining Waves : 3");



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        background.setImage(new Image(getClass().getResource("/images/NightBackground.png").toExternalForm()));
        GameRoot.setInstance();
        GameState.NewGameState();
        GameRoot.getInstance().setGamePane(gamePane);

        Label waveLabel = new Label("Remaining Waves : 1");
        waveLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        waveLabel.setTextFill(Color.WHITE);
        waveLabel.setText("Remaining Waves : 2");
        waveLabel.setLayoutX(900);
        waveLabel.setLayoutY(40);

        GameRoot.getInstance().getGamePane().getChildren().add(waveLabel);



        HBox plantBar = new HBox(20);
        plantBar.setLayoutX(250);
        plantBar.setLayoutY(30);
        plantBar.setPrefHeight(30);
        plantBar.setStyle("-fx-background-color: #dfd360;");


        Image puffShroomImage = new Image(getClass().getResource("/images/puffShroom_Card.jpg").toExternalForm());
        ImageView puffShroomIcon = new ImageView(puffShroomImage);
        puffShroomIcon.setFitWidth(50);
        puffShroomIcon.setFitHeight(70);
        puffShroomIcon.setOnMouseClicked(e -> {
            selectedPlant = PlantType.PUFFSHROMM;
        });

        Image dooomImage = new Image(getClass().getResource("/images/doomCardpng.png").toExternalForm());
        ImageView dooomIcon = new ImageView(dooomImage);
        dooomIcon.setFitWidth(50);
        dooomIcon.setFitHeight(70);
        dooomIcon.setOnMouseClicked(e -> {
            selectedPlant = PlantType.DOOM;
        });

        Image scaredyImage = new Image(getClass().getResource("/images/pofCard.png").toExternalForm());
        ImageView scaredyIcon = new ImageView(scaredyImage);
        scaredyIcon.setFitWidth(50);
        scaredyIcon.setFitHeight(70);
        scaredyIcon.setOnMouseClicked(e -> {
            selectedPlant = PlantType.SCAREDY;
        });

        Image sunShroomImage = new Image(getClass().getResource("/images/sunShroom_Card.jpg").toExternalForm());
        ImageView sunShroomIcon = new ImageView(sunShroomImage);
        sunShroomIcon.setFitWidth(50);
        sunShroomIcon.setFitHeight(70);
        sunShroomIcon.setOnMouseClicked(e -> {
            selectedPlant = PlantType.SUNSHROOM;
        });

        Image FumeImage = new Image(getClass().getResource("/images/Fuume.png").toExternalForm());
        ImageView FumeIcon = new ImageView(FumeImage);
        FumeIcon.setFitWidth(50);
        FumeIcon.setFitHeight(70);
        FumeIcon.setOnMouseClicked(e -> {
            selectedPlant = PlantType.FUMESHROOM;
        });

        Image IceImage = new Image(getClass().getResource("/images/Ice.png").toExternalForm());
        ImageView IceIcon = new ImageView(IceImage);
        IceIcon.setFitWidth(50);
        IceIcon.setFitHeight(70);
        IceIcon.setOnMouseClicked(e -> {
            selectedPlant = PlantType.ICESHROOM;
        });

        plantBar.getChildren().addAll(puffShroomIcon, sunShroomIcon , FumeIcon, IceIcon , scaredyIcon , dooomIcon);
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
                //tile.setStroke(Color.DARKGREEN);
                int finalRow = row;
                int finalCol = col;

                tile.setOnMouseClicked(e -> {
                    if (selectedPlant == PlantType.NONE) {
                        return;
                    }
                    double tileX = grid.getLayoutY() + finalRow * GameRoot.getInstance().getTILE_HEIGHT() +10;
                    double tileY = grid.getLayoutX() + finalCol * GameRoot.getInstance().getTILE_WIDTH()+20;
                    if (!GameRoot.getInstance().getPlantPlaced(finalRow, finalCol)) {
                        switch (selectedPlant) {
                            case PUFFSHROMM:
                                PuffShroom puffshroom = new PuffShroom(grid.getLayoutY() + finalRow * GameRoot.getInstance().getTILE_HEIGHT() + 10, grid.getLayoutX() + finalCol * GameRoot.getInstance().getTILE_WIDTH() + 20, finalRow, finalCol);
                                GameRoot.getInstance().loadSunNum();
                                GameState.getInstance().getPlantsClass().put(puffshroom.getImageView(), puffshroom);
                                GameRoot.getInstance().setPlantPlaced(finalRow, finalCol, true);
                                break;

                            case SUNSHROOM:
                                if (GameState.getInstance().getSunPoints() >= 25) {
                                    GameState.getInstance().setSunPoints(GameState.getInstance().getSunPoints() - 25);
                                    SunShroom sunShroom = new SunShroom(grid.getLayoutY() + finalRow * GameRoot.getInstance().getTILE_HEIGHT() + 10, grid.getLayoutX() + finalCol * GameRoot.getInstance().getTILE_WIDTH() + 20, finalRow, finalCol);
                                    GameRoot.getInstance().loadSunNum();
                                    GameState.getInstance().getPlantsClass().put(sunShroom.getImageView(), sunShroom);
                                    GameRoot.getInstance().setPlantPlaced(finalRow, finalCol, true);
                                    break;
                                } else {
                                    break;
                                }
                            case ICESHROOM:
                                if (GameState.getInstance().getSunPoints() >= 75) {
                                    GameState.getInstance().setSunPoints(GameState.getInstance().getSunPoints() - 75);
                                    IceShroom iceShroom = new IceShroom(grid.getLayoutY() + finalRow * GameRoot.getInstance().getTILE_HEIGHT() + 10, grid.getLayoutX() + finalCol * GameRoot.getInstance().getTILE_WIDTH() + 20, finalRow, finalCol);
                                    GameRoot.getInstance().loadSunNum();
                                    GameState.getInstance().getPlantsClass().put(iceShroom.getImageView(), iceShroom);
                                    GameRoot.getInstance().setPlantPlaced(finalRow, finalCol, true);
                                    break;
                                }else {
                                    break;
                                }
                            case FUMESHROOM:
                                if (GameState.getInstance().getSunPoints() >= 75) {
                                    GameState.getInstance().setSunPoints(GameState.getInstance().getSunPoints() - 75);
                                    FumeShroom fumeShroom = new FumeShroom(grid.getLayoutY() + finalRow * GameRoot.getInstance().getTILE_HEIGHT() + 10, grid.getLayoutX() + finalCol * GameRoot.getInstance().getTILE_WIDTH() + 20, finalRow, finalCol);
                                    GameRoot.getInstance().loadSunNum();
                                    GameState.getInstance().getPlantsClass().put(fumeShroom.getImageView(), fumeShroom);
                                    GameRoot.getInstance().setPlantPlaced(finalRow, finalCol, true);
                                    break;
                                }else{
                                    break;
                                }
                            case DOOM:
                                if (GameState.getInstance().getSunPoints() >= 125) {
                                    GameState.getInstance().setSunPoints(GameState.getInstance().getSunPoints() - 125);
                                    DoomShroom doomShroom = new DoomShroom(grid.getLayoutY() + finalRow * GameRoot.getInstance().getTILE_HEIGHT() + 10, grid.getLayoutX() + finalCol * GameRoot.getInstance().getTILE_WIDTH() + 20, finalRow, finalCol);
                                    GameRoot.getInstance().loadSunNum();
                                    GameState.getInstance().getPlantsClass().put(doomShroom.getImageView() , doomShroom);
                                    GameRoot.getInstance().setPlantPlaced(finalRow, finalCol, true);
                                    break;
                                }else{
                                    break;
                                }
                            case SCAREDY:
                                if (GameState.getInstance().getSunPoints() >= 25) {
                                    GameState.getInstance().setSunPoints(GameState.getInstance().getSunPoints() - 25);
                                    ScaredyShroom scaredyShroom = new ScaredyShroom(grid.getLayoutY() + finalRow * GameRoot.getInstance().getTILE_HEIGHT() + 10, grid.getLayoutX() + finalCol * GameRoot.getInstance().getTILE_WIDTH() + 20, finalRow, finalCol);
                                    GameRoot.getInstance().loadSunNum();
                                    GameState.getInstance().getPlantsClass().put(scaredyShroom.getImageView(), scaredyShroom);
                                    GameRoot.getInstance().setPlantPlaced(finalRow, finalCol, true);
                                    break;
                                }else{
                                    break;
                                }
                        }
                    }


                    selectedPlant = PlantType.NONE;
                });
            }

            Timeline winChecker = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                if (GameState.getInstance().areAllZombiesSpawned() && GameState.getInstance().getZombies().isEmpty()) {
                    TimelineManager.getInstance().stopAll();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Victory");
                    alert.setHeaderText("Win");
                    alert.setContentText("All Zombies have been dead");
                    alert.show();
                    PlayerController.increaseWins(HelloController.logInPlayer.getUserName());
                    if (HelloController.logInPlayer.getLevel()==5) {
                        PlayerController.increaseLevel(HelloController.logInPlayer.getUserName());
                    }
                    HelloController.logInPlayer=PlayerController.LogIn(HelloController.logInPlayer.getUserName() , HelloController.logInPlayer.getPassword());
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("HomePageView.fxml"));
                    Scene scene;
                    try {
                        scene = new Scene(loader.load());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Stage stage = (Stage) gamePane.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }
            }));
            winChecker.setCycleCount(Timeline.INDEFINITE);
            winChecker.play();
            TimelineManager.getInstance().add(winChecker);
        }





        startWaves();

    }




    private void startWaves() {
        Timeline waveTimeline = new Timeline(new KeyFrame(Duration.seconds(20), event -> {
            if (currentWave < zombiesPerWave.length) {
                spawnWave(currentWave);
            } else {
                spawnFinalWave(currentWave);
            }
            currentWave++;
        }));
        waveTimeline.setCycleCount(totalWaves);
        waveTimeline.play();
        TimelineManager.getInstance().getTimelines().add(waveTimeline);
    }



    private void spawnWave(int waveIndex) {
        int zombiesToSpawn = zombiesPerWave[waveIndex];
        Timeline spawner = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            spawnZombie();
            spawnScreenDoorZombie();
        }));
        spawner.setCycleCount(zombiesToSpawn);
        spawner.play();
        TimelineManager.getInstance().getTimelines().add(spawner);
    }

    private void spawnFinalWave(int waveNum) {
        System.out.println(" Final Wave " + (waveNum - zombiesPerWave.length + 1));
        waveLabel.setText(" Final Wave " + (4-waveNum));
        int row = new Random().nextInt(5);
        double startY = 200 + row * 100;
        FlagZombie flagZombie = new FlagZombie(startY);

        Timeline spawner = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            spawnConeHeadZombie();
            spawnNewsPaperZombie();
        }));
        spawner.setCycleCount(10 + waveNum );
        spawner.play();
        TimelineManager.getInstance().getTimelines().add(spawner);

        if (waveNum == zombiesPerWave.length + 1) {
            GameState.getInstance().setAllZombiesSpawned(true);
        }
    }



    private void spawnZombie() {
        Random rand = new Random();
        int row = rand.nextInt(5);
        double startY = 200 + row * 100;
        NormalZombie zombie = new NormalZombie(startY);
    }
    private void spawnConeHeadZombie() {
        Random rand = new Random();
        int row = rand.nextInt(5);
        double startY = 200 + row * 100;
        ConeHeadZombie zombie = new ConeHeadZombie(startY);
    }
    private void spawnScreenDoorZombie() {
        Random rand = new Random();
        int row = rand.nextInt(5);
        double startY = 200 + row * 100;
        ScreenDoorZombie zombie = new ScreenDoorZombie(startY);
    }
    private void spawnNewsPaperZombie() {
        Random rand = new Random();
        int row = rand.nextInt(5);
        double startY = 200 + row * 100;
        NewsPaperZombie zombie = new NewsPaperZombie(startY);
    }







}
