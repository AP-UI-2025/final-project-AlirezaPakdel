package org.example.plantvszombies.Model.Zombies;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.ResistantZombie;

public class ScreenDoorZombie extends ResistantZombie {
    public ScreenDoorZombie(double y) {
        super(110, 2, 2);
        Image image = new Image(getClass().getResource("/images/screenDoor.png").toExternalForm());
        super.setImage(image);
        ImageView imageView = new ImageView(image);
        GameState.getInstance().getScreenDoorZombiesHealth().put(imageView , 50);
        imageView.setFitHeight(120);
        imageView.setFitWidth(110);
        imageView.setPreserveRatio(true);
        imageView.setLayoutX(1100);
        //imageView.setY(200);
        imageView.setLayoutY(y);
        super.setImageView(imageView);
        GameState.getInstance().getZombies().add(imageView);
        GameState.getInstance().getZombiesHealth().put(imageView, getHealth());
        GameRoot.getInstance().getGamePane().getChildren().add(imageView);
        GameState.getInstance().getZombiesClass().put(imageView, this);
        super.startMoving();
    }
}
