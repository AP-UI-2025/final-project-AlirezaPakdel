package org.example.plantvszombies.Model.Zombies;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.SpecialAbility;
import org.example.plantvszombies.Model.SpecialZombie;

public class FlagZombie extends SpecialZombie {
    public FlagZombie(double y) {
        super(60, 3, 2, SpecialAbility.Fast);
        Image image = new Image(getClass().getResource("/images/flagZombie.gif").toExternalForm());
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
