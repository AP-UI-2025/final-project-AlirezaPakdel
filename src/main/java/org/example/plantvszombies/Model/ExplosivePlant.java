package org.example.plantvszombies.Model;

import javafx.scene.image.ImageView;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;

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

        double minR = getRow() - GameRoot.getInstance().getTILE_WIDTH();
        double maxR = getRow() + GameRoot.getInstance().getTILE_WIDTH();

        double minC = getCol() - GameRoot.getInstance().getTILE_WIDTH();
        double maxC = getCol() + GameRoot.getInstance().getTILE_WIDTH();

        int plantX = getX();
        int plantY = getY();

        for (ImageView zombie : GameState.getInstance().getZombies()) {

            if (zombie.getX() > minR && zombie.getX() < maxR) {
                if (zombie.getY() > minC && zombie.getY() < maxC) {
                    GameState.getInstance().getZombiesClass().get(zombie).die();
                }
            }


        }

        GameRoot.getInstance().getGamePane().getChildren().remove(this.getImageView());
        GameRoot.getInstance().setPlantPlaced(plantX, plantY, false);
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
