package org.example.plantvszombies.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.plantvszombies.HelloApplication;
import org.example.plantvszombies.Model.Player;

public class ScoreBoardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> PlayersList;

    @FXML
    void initialize() {
        ArrayList<Player> players = PlayerController.loadPlayers();
        players.sort((p1, p2) -> p2.getScore() - p1.getScore());
        int num = 1;
        PlayersList.getItems().clear();
        for (Player p : players) {
            PlayersList.getItems().add(num + " -> " + p.toString());
            if (p==HelloController.logInPlayer){
                PlayersList.getSelectionModel().select(num-1);
            }
            num++;
        }
        PlayersList.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-control-inner-background: transparent;" +
                        "-fx-background-insets: 0;" +
                        "-fx-padding: 0;"
        );




        assert PlayersList != null : "fx:id=\"PlayersList\" was not injected: check your FXML file 'ScoreBoardView.fxml'.";
    }

    public void GoBack(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("HomePageView.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) PlayersList.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
