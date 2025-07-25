package org.example.plantvszombies.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.plantvszombies.HelloApplication;

public class NightModeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView BackBtn;

    @FXML
    private Label mission1;

    @FXML
    private Label mission2On;

    @FXML
    private Label mission2off;

    @FXML
    private Label mission3Off;

    @FXML
    private Label mission3On;

    @FXML
    void GoBack(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("HomePageView.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) BackBtn.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        if (HelloController.logInPlayer.getLevel()>=5){
            mission2off.setVisible(false);
            mission3Off.setVisible(false);
            mission3Off.setVisible(false);
            mission2off.setVisible(false);
        }
        if (HelloController.logInPlayer.getLevel()==4){
            mission3On.setVisible(false);
            mission3On.setVisible(false);
            mission2off.setVisible(false);
            mission2off.setVisible(false);
        }

        assert BackBtn != null : "fx:id=\"BackBtn\" was not injected: check your FXML file 'NightModeView.fxml'.";

    }

    public void mission1(MouseEvent mouseEvent) {
    }

    public void mission2(MouseEvent mouseEvent) {
    }

    public void mission3(MouseEvent mouseEvent) {
    }
}
