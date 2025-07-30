package org.example.plantvszombies.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.Game.TimelineManager;

public class WarriorPlant extends Plant {
    private int attackPower;
    private int attackSpeed;
    private BulletType bulletType;

    public WarriorPlant(int solarCost, String plantName , int attackPower , int attackSpeed , BulletType bulletType) {
        super(solarCost, plantName);
        this.attackPower=attackPower;
        this.attackSpeed=attackSpeed;
        this.bulletType=bulletType;
    }




    public void Shoot(){
        if(bulletType==BulletType.Normal){
            Image peaImg = new Image(getClass().getResource("/images/pea.png").toExternalForm());
            ImageView pea = new ImageView(peaImg);
            pea.setFitWidth(20);
            pea.setFitHeight(20);
            pea.setLayoutX(getCol()+5);
            pea.setLayoutY(getRow() - 15);
            GameRoot.getInstance().getGamePane().getChildren().add(pea);

            final boolean[] hit = {false};
            Timeline movePea = new Timeline(new KeyFrame(Duration.millis(20), e -> {
                if(hit[0]){
                    return;
                }

                pea.setLayoutX(pea.getLayoutX() + 5);

                for (ImageView zombie : GameState.getInstance().getZombies()) {
                    if (pea.getBoundsInParent().intersects(zombie.getBoundsInParent())) {
                        hit[0] = true;
                        GameRoot.getInstance().getGamePane().getChildren().remove(pea);

                        int hp = GameState.getInstance().getZombiesHealth().get(zombie);
                        hp--;

                        if (hp <= 0) {
                            GameRoot.getInstance().getGamePane().getChildren().remove(zombie);
                            GameState.getInstance().getZombies().remove(zombie);
                        } else {
                            GameState.getInstance().getZombiesHealth().put(zombie, hp);
                        }

                        return;
                    }
                }
            }));

        } else if (bulletType==BulletType.Snowy) {
            Image peaImg = new Image(getClass().getResource("/images/snowyPea.png").toExternalForm());
            ImageView pea = new ImageView(peaImg);
            pea.setFitWidth(20);
            pea.setFitHeight(20);
            pea.setLayoutX(getCol()+5);
            pea.setLayoutY(getRow() - 15);
            GameRoot.getInstance().getGamePane().getChildren().add(pea);

            final boolean[] hit = {false};
            Timeline movePea = new Timeline(new KeyFrame(Duration.millis(20), e -> {
                if(hit[0]){
                    return;
                }

                pea.setLayoutX(pea.getLayoutX() + 5);

                for (ImageView zombie : GameState.getInstance().getZombies()) {
                    if (pea.getBoundsInParent().intersects(zombie.getBoundsInParent())) {
                        hit[0] = true;
                        GameRoot.getInstance().getGamePane().getChildren().remove(pea);

                        //low zombie speed

                        int hp = GameState.getInstance().getZombiesHealth().get(zombie);
                        hp--;

                        if (hp <= 0) {
                            GameRoot.getInstance().getGamePane().getChildren().remove(zombie);
                            GameState.getInstance().getZombies().remove(zombie);
                        } else {
                            GameState.getInstance().getZombiesHealth().put(zombie, hp);
                        }

                        return;
                    }
                }
            }));
        } else if (bulletType==BulletType.Smoke) {
            Image peaImg = new Image(getClass().getResource("/images/smokePea.png").toExternalForm());
            ImageView pea = new ImageView(peaImg);
            pea.setFitWidth(20);
            pea.setFitHeight(20);
            pea.setLayoutX(getCol()+5);
            pea.setLayoutY(getRow() - 15);
            GameRoot.getInstance().getGamePane().getChildren().add(pea);

            final boolean[] hit = {false};
            Timeline movePea = new Timeline(new KeyFrame(Duration.millis(20), e -> {
                if(hit[0]){
                    return;
                }

                pea.setLayoutX(pea.getLayoutX() + 5);

                for (ImageView zombie : GameState.getInstance().getZombies()) {
                    if (pea.getBoundsInParent().intersects(zombie.getBoundsInParent())) {
                        hit[0] = true;
                        GameRoot.getInstance().getGamePane().getChildren().remove(pea);

                        int hp = GameState.getInstance().getZombiesHealth().get(zombie);
                        hp--;

                        if (hp <= 0) {
                            GameRoot.getInstance().getGamePane().getChildren().remove(zombie);
                            GameState.getInstance().getZombies().remove(zombie);
                        } else {
                            GameState.getInstance().getZombiesHealth().put(zombie, hp);
                        }

                        return;
                    }
                }
            }));
        }
    }

    public void startShooting(TimelineManager manager) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(getAttackSpeed()), e -> Shoot()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        manager.add(timeline);
    }



    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public BulletType getBulletType() {
        return bulletType;
    }

    public void setBulletType(BulletType bulletType) {
        this.bulletType = bulletType;
    }
}
