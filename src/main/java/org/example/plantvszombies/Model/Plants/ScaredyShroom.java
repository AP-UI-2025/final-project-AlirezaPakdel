package org.example.plantvszombies.Model.Plants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.plantvszombies.Model.BulletType;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.Game.TimelineManager;
import org.example.plantvszombies.Model.WarriorPlant;

public class ScaredyShroom extends WarriorPlant {
    public ScaredyShroom(double row, double col , int x , int y) {
        super(25, "Scar" , 10, 3, BulletType.Smoke, x, y);
        Image image = new Image(getClass().getResource("/images/scaredy-shroom.gif").toExternalForm());
        Image image2 = new Image(getClass().getResource("/images/scaredy-shroom-hide.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(60);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
        imageView.setLayoutX(col);
        imageView.setLayoutY(row);
        super.setImageView(imageView);
        GameState.getInstance().getPlants().add(imageView);
        GameState.getInstance().getPlantsHealth().put(imageView, 5);
        setRow(row);
        setCol(col);
        GameRoot.getInstance().getGamePane().getChildren().add(imageView);
        StartPeaShooter();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {
            if (IsAnyBodyHere()){
                if (super.getImageView().getImage()!=image2){
                    getImageView().setImage(image2);
                }
            }else{
                if (super.getImageView().getImage()!=image){
                    getImageView().setImage(image);
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    private void StartPeaShooter() {
        Timeline shooter = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
            if (IsAnyBodyHere()){
                return;
            }
            boolean zombieInRow = false;
            for (ImageView zombie : GameState.getInstance().getZombies()) {
                if (Math.abs(zombie.getLayoutY()  - getRow()) <= 30) {
                    zombieInRow = true;
                    break;
                }
            }

            if (zombieInRow) {
                if (super.getShootTimeline() == null) {
                    startShooting();
                }
            } else {
                if (super.getShootTimeline() != null) {
                    super.getShootTimeline().stop();
                    super.setShootTimeline(null);
                }
            }
        }));
        shooter.setCycleCount(Timeline.INDEFINITE);
        shooter.play();
        TimelineManager.getInstance().add(shooter);
        super.getAllTimelines().add(shooter);
    }

    private boolean IsAnyBodyHere(){
        double centerX = getCol() + this.getImageView().getBoundsInParent().getWidth() / 2;
        double centerY = getRow() + this.getImageView().getBoundsInParent().getHeight() / 2;

        double minX = centerX - GameRoot.getInstance().getTILE_WIDTH() * 1.5;
        double maxX = centerX + GameRoot.getInstance().getTILE_WIDTH() * 1.5;
        double minY = centerY - GameRoot.getInstance().getTILE_HEIGHT() * 1.5;
        double maxY = centerY + GameRoot.getInstance().getTILE_HEIGHT() * 1.5;

        int plantX = getX();
        int plantY = getY();
        for (ImageView zombieView : GameState.getInstance().getZombies()) {
            double zx = zombieView.getLayoutX() + zombieView.getBoundsInParent().getWidth() / 2;
            double zy = zombieView.getLayoutY() + zombieView.getBoundsInParent().getHeight() / 2;
            if (zx >= minX && zx <= maxX && zy >= minY && zy <= maxY) {
                return true;
            }
        }
        return false;
    }
}
