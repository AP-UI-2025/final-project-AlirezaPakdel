package org.example.plantvszombies.Model.Zombies;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.ResistantZombie;

public class ConeHeadZombie extends ResistantZombie {
    public ConeHeadZombie(double y) {
        super(10, 2, 1);
        Image image = new Image(getClass().getResource("/images/coneheadZombie.gif").toExternalForm());
        //Image imageEat = new Image(getClass().getResource("/images/NormalEat.gif").toExternalForm());
        //super.setEatImage(imageEat);
        super.setImage(image);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(140);
        imageView.setFitWidth(130);
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
