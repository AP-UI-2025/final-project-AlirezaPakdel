package org.example.plantvszombies.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import org.example.plantvszombies.HelloApplication;
import org.example.plantvszombies.Model.Player;


public class HomePageController {

    @FXML
    private ImageView isNightModeUnlock;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView DayMode;

    @FXML
    private ImageView Info;

    @FXML
    private ImageView NightMode;

    @FXML
    private ImageView Seting;

    @FXML
    void GoToDayMode(MouseEvent event) {

    }

    @FXML
    void GoToNightMode(MouseEvent event) {

    }

    @FXML
    void GoToSetting(MouseEvent event) {

    }

    @FXML
    void GoToUserInformation(MouseEvent event) {

    }

    @FXML
    void initialize() {

        String musicPath = getClass().getResource("/music/Moongrains.mp3").toExternalForm();
        Media backgroundMusic = new Media(musicPath);
        MediaPlayer mediaPlayer = new MediaPlayer(backgroundMusic);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        if (HelloController.logInPlayer.getLevel()>2){
            isNightModeUnlock.setVisible(false);
        }


        assert DayMode != null : "fx:id=\"DayMode\" was not injected: check your FXML file 'HomePageView.fxml'.";
        assert Info != null : "fx:id=\"Info\" was not injected: check your FXML file 'HomePageView.fxml'.";
        assert NightMode != null : "fx:id=\"NightMode\" was not injected: check your FXML file 'HomePageView.fxml'.";
        assert Seting != null : "fx:id=\"Seting\" was not injected: check your FXML file 'HomePageView.fxml'.";

    }

    public void LogOut(MouseEvent mouseEvent) throws IOException {
        HelloController.logInPlayer=null;
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) DayMode.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
