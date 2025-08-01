package org.example.plantvszombies.Model.Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.plantvszombies.HelloApplication;
import org.example.plantvszombies.Model.Plants.PeaShooter;
import org.example.plantvszombies.Model.Plants.SunFlower;

import java.io.IOException;

public class GameRoot {
    public int getROWS() {
        return ROWS;
    }

    public int getCOLS() {
        return COLS;
    }

    public int getTILE_WIDTH() {
        return TILE_WIDTH;
    }

    public int getTILE_HEIGHT() {
        return TILE_HEIGHT;
    }

    private final int ROWS = 5;
    private final int COLS = 9;
    private final int TILE_WIDTH = 80;
    private final int TILE_HEIGHT = 100;

    Label sunLabel;


    private static GameRoot instance;

    private Pane gamePane ;

    private GameRoot() {}

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

        Button Back = new Button("Back");
        Back.setLayoutX(1100);
        Back.setLayoutY(700);
        gamePane.getChildren().add(Back);
        Back.setOnAction(e -> {
            TimelineManager.getInstance().stopAll();
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
        });



    }

    public void loadSunNum() {
        sunLabel.setText("Sun : " + GameState.getInstance().getSunPoints());
    }

    public Pane getGamePane() {
        return gamePane;
    }
}

