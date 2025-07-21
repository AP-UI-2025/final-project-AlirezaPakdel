package org.example.plantvszombies.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField PassWord;

    @FXML
    private TextField UserName;

    @FXML
    void Login(ActionEvent event) {
       if (PlayerController.LogIn( UserName.getText() , PassWord.getText())!=null){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Login");
           alert.setHeaderText(null);
           alert.setContentText("You have successfully logged in");
           alert.showAndWait();
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
        System.out.println(" JavaFX Runtime: " + System.getProperty("javafx.runtime.version"));
        assert PassWord != null : "fx:id=\"PassWord\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert UserName != null : "fx:id=\"UserName\" was not injected: check your FXML file 'hello-view.fxml'.";
    }

}
