package org.example.plantvszombies.Model.Zombies;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.ResistantZombie;

public class NormalZombie extends ResistantZombie {
    public NormalZombie() {
        super(10, 1, 1);
        Image image = new Image(getClass().getResource("/images/peaShooter.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(60);
        imageView.setPreserveRatio(true);
        super.setImageView(imageView);
        GameState.getInstance().getZombies().add(imageView);
        GameState.getInstance().getZombiesHealth().put(imageView, getHealth());
    }
}
