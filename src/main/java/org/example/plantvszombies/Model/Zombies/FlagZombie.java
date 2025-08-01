package org.example.plantvszombies.Model.Zombies;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.SpecialAbility;
import org.example.plantvszombies.Model.SpecialZombie;

public class FlagZombie extends SpecialZombie {
    public FlagZombie(double y) {
        super(10, 2, 2, SpecialAbility.Fast);
        Image image = new Image(getClass().getResource("/images/flagZombie.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(120);
        imageView.setFitWidth(110);
        imageView.setPreserveRatio(false);
        imageView.setX(1100);
        //imageView.setY(200);
        System.out.println(y);
        imageView.setLayoutY(y);
        super.setImageView(imageView);
        GameState.getInstance().getZombies().add(imageView);
        GameState.getInstance().getZombiesHealth().put(imageView, getHealth());
        GameRoot.getInstance().getGamePane().getChildren().add(imageView);
        super.startMoving();
    }
}
