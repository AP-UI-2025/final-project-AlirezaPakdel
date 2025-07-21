module org.example.plantvszombies {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.plantvszombies to javafx.fxml;
    exports org.example.plantvszombies;
    exports org.example.plantvszombies.Model;
    opens org.example.plantvszombies.Model to javafx.fxml;
    exports org.example.plantvszombies.Controller;
    opens org.example.plantvszombies.Controller to javafx.fxml;
}