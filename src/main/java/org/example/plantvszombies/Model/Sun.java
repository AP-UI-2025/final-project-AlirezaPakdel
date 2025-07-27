package org.example.plantvszombies.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sun {
    private final ImageView imageView;
    private final int row, col;

    public Sun(int row, int col) {
        this.row = row;
        this.col = col;

        Image image = new Image(getClass().getResource("/images/sun.png").toExternalForm());
        imageView = new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        imageView.setLayoutX(col * 100 + 30);

        imageView.setOnMouseClicked(e -> {

            GameState.getInstance().addSun(25);
            GameRoot.getInstance().getGamePane().getChildren().remove(imageView);
        });
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void playDropAnimation() {

    }
}
