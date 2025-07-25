package org.example.plantvszombies.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SettingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label loss;

    @FXML
    private CheckBox music;

    @FXML
    private Label password;

    @FXML
    private Label score;

    @FXML
    private TextField testTxt;

    @FXML
    private Label userName;

    @FXML
    private Label win;

    @FXML
    void EditPassWord(MouseEvent event) {

    }

    @FXML
    void EditUserName(MouseEvent event) {

    }

    @FXML
    void initialize() {
        userName.setText("UserName : "+HelloController.logInPlayer.getUserName());
        password.setText("PassWord : "+HelloController.logInPlayer.getPassword());
        win.setText("Wins : " + HelloController.logInPlayer.getWin());
        loss.setText("Loss : "+ HelloController.logInPlayer.getLoss());
        score.setText("Score : "+ (HelloController.logInPlayer.getWin()*3-HelloController.logInPlayer.getLoss()));

        if (MusicPlayer.isOn){
            music.setSelected(true);
        }else{
            music.setSelected(false);
        }
        music.setOnMouseClicked(event -> {
            if (!music.isSelected()) {
                MusicPlayer.isOn = true;
                MusicPlayer.getInstance().stop();
            }else{
                MusicPlayer.isOn = false;
                MusicPlayer.getInstance().play();
            }
        });

        assert loss != null : "fx:id=\"loss\" was not injected: check your FXML file 'SettingView.fxml'.";
        assert music != null : "fx:id=\"music\" was not injected: check your FXML file 'SettingView.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'SettingView.fxml'.";
        assert score != null : "fx:id=\"score\" was not injected: check your FXML file 'SettingView.fxml'.";
        assert testTxt != null : "fx:id=\"testTxt\" was not injected: check your FXML file 'SettingView.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'SettingView.fxml'.";
        assert win != null : "fx:id=\"win\" was not injected: check your FXML file 'SettingView.fxml'.";

    }

}
