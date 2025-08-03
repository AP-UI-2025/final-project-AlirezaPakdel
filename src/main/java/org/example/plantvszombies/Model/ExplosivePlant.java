package org.example.plantvszombies.Model;

import javafx.scene.image.ImageView;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;

import java.util.ArrayList;
import java.util.List;

public class ExplosivePlant extends Plant {
    private int destructionPower;
    private int destructionRange;
    private DestructionType destructionType;


    public ExplosivePlant(int solarCost, String plantName , int destructionPower, int destructionRange, DestructionType destructionType , int x , int y ) {
        super(solarCost, plantName , x, y);
        this.destructionPower = destructionPower;
        this.destructionRange = destructionRange;
        this.destructionType = destructionType;
    }

    public void Explosion() {

        if (destructionType==DestructionType.Square) {
            List<ImageView> imageViews = new ArrayList<ImageView>();

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
                    imageViews.add(zombieView);
                    GameState.getInstance().getZombiesClass().get(zombieView).getBorn();
                }
            }
            GameRoot.getInstance().getGamePane().getChildren().remove(this.getImageView());
            GameRoot.getInstance().setPlantPlaced(plantX, plantY, false);


            for (ImageView zombieView : imageViews) {
                GameState.getInstance().getZombies().remove(zombieView);
            }
        }

        if (destructionType==DestructionType.SquareIce) {
            for (ImageView zombieView : GameState.getInstance().getZombies()) {
                GameState.getInstance().getZombiesClass().get(zombieView).getIce();
                GameRoot.getInstance().getGamePane().getChildren().remove(this.getImageView());
                GameRoot.getInstance().setPlantPlaced(getX(), getY(), false);
            }
        }

        if (destructionType==DestructionType.liner) {
            for (ImageView zombieView : GameState.getInstance().getZombies()) {
                GameState.getInstance().getZombiesClass().get(zombieView).getBorn();
            }
            GameRoot.getInstance().setPlantPlaced(getX(), getY(), false);
            GameRoot.getInstance().getGamePane().getChildren().remove(this.getImageView());
            GameState.getInstance().getZombies().removeAll(GameState.getInstance().getZombies());
        }

    }




    public int getDestructionPower() {
        return destructionPower;
    }

    public void setDestructionPower(int destructionPower) {
        this.destructionPower = destructionPower;
    }

    public int getDestructionRange() {
        return destructionRange;
    }

    public void setDestructionRange(int destructionRange) {
        this.destructionRange = destructionRange;
    }

    public DestructionType getDestructionType() {
        return destructionType;
    }

    public void setDestructionType(DestructionType destructionType) {
        this.destructionType = destructionType;
    }
}
