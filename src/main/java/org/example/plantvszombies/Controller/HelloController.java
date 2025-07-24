package org.example.plantvszombies.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.plantvszombies.HelloApplication;
import org.example.plantvszombies.Model.Player;

import javax.swing.*;

public class HelloController {

    public static ArrayList<Player> allPlayers;
    public static Player logInPlayer ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField PassWord;

    @FXML
    private TextField UserName;

    @FXML
    void Login(ActionEvent event) throws IOException {

        System.out.println(UserName.getText());
        logInPlayer=PlayerController.LogIn( UserName.getText() , PassWord.getText());
        if (logInPlayer!=null){
           /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Login");
           alert.setHeaderText(null);
           alert.setContentText("You have successfully logged in");
           alert.showAndWait();*/
           FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("HomePageView.fxml"));
           Scene scene = new Scene(loader.load());
           Stage stage = (Stage) UserName.getScene().getWindow();
           stage.setScene(scene);
           stage.show();
       }else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Login");
           alert.setHeaderText(null);
           alert.setContentText("faild to login");
           alert.showAndWait();
       }
    }

    @FXML
    void SignUp(ActionEvent event) {
        PlayerController.SignUp( UserName.getText() , PassWord.getText());
    }

    @FXML
    void initialize() {
        MusicPlayer.getInstance().play();


        System.out.println(" JavaFX Runtime: " + System.getProperty("javafx.runtime.version"));
        assert PassWord != null : "fx:id=\"PassWord\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert UserName != null : "fx:id=\"UserName\" was not injected: check your FXML file 'hello-view.fxml'.";
    }

}
