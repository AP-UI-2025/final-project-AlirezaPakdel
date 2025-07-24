package org.example.plantvszombies.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HomePageController {

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
    void GoToSeting(MouseEvent event) {

    }

    @FXML
    void GoToUserInformation(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert DayMode != null : "fx:id=\"DayMode\" was not injected: check your FXML file 'HomePageView.fxml'.";
        assert Info != null : "fx:id=\"Info\" was not injected: check your FXML file 'HomePageView.fxml'.";
        assert NightMode != null : "fx:id=\"NightMode\" was not injected: check your FXML file 'HomePageView.fxml'.";
        assert Seting != null : "fx:id=\"Seting\" was not injected: check your FXML file 'HomePageView.fxml'.";

    }

}
