package org.example.plantvszombies.Model.Plants;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.plantvszombies.Model.BulletType;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.WarriorPlant;

public class PeaShooter extends WarriorPlant {
    public PeaShooter(int row, int col) {
        super(100, "PeaShooter", 10, 3, BulletType.Normal);
        Image image = new Image(getClass().getResource("/images/peaShooter.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(60);
        imageView.setPreserveRatio(true);
        super.setImageView(imageView);
        GameState.getInstance().getPlants().add(imageView);
        GameState.getInstance().getPlantsHealth().put(imageView, 5);
        setRow(row);
        setCol(col);
    }
}
