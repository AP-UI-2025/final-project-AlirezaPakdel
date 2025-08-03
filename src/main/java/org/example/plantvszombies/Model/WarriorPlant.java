package org.example.plantvszombies.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.plantvszombies.Model.Game.GameRoot;
import org.example.plantvszombies.Model.Game.GameState;
import org.example.plantvszombies.Model.Game.TimelineManager;
import javafx.scene.effect.ColorAdjust;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.image.ImageView;

public class WarriorPlant extends Plant {
    private int attackPower;
    private int attackSpeed;
    private BulletType bulletType;
    private Timeline shootTimeline;
    private Timeline shootTimeline2;

    public Timeline getShootTimeline2() {
        return shootTimeline2;
    }

    public void setShootTimeline2(Timeline shootTimeline2) {
        this.shootTimeline2 = shootTimeline2;
    }

    public Timeline getShootTimeline() {
        return shootTimeline;
    }

    public void setShootTimeline(Timeline shootTimeline) {
        this.shootTimeline = shootTimeline;
    }

    public WarriorPlant(int solarCost, String plantName , int attackPower , int attackSpeed , BulletType bulletType , int x , int y) {
        super(solarCost, plantName , x, y);
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
            pea.setLayoutX(getCol()+35);
            pea.setLayoutY(getRow() - 5);
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
                        hp=hp-getAttackPower();


                        if (hp <= 0) {
                            GameState.getInstance().getZombiesClass().get(zombie).die();
                            GameRoot.getInstance().getGamePane().getChildren().remove(zombie);
                            GameState.getInstance().getZombies().remove(zombie);
                        } else {
                            GameState.getInstance().getZombiesHealth().put(zombie, hp);
                        }

                        return;
                    }
                }
            }));

            movePea.setCycleCount(Timeline.INDEFINITE);
            movePea.play();

        } else if (bulletType==BulletType.Snowy) {
            Image peaImg = new Image(getClass().getResource("/images/snowyPea.png").toExternalForm());
            ImageView pea = new ImageView(peaImg);
            pea.setFitWidth(20);
            pea.setFitHeight(20);
            pea.setLayoutX(getCol()+2);
            pea.setLayoutY(getRow() - 5);
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

                        Zombie z = GameState.getInstance().getZombiesClass().get(zombie);
                        Timeline ztime = z.getMoveTimeline();
                        double currentRate = ztime.getRate();
                        int currentSpeed = z.getSpeed();
                        if (currentSpeed > 1) {
                            z.setSpeed(1);
                        }

                        ColorAdjust blueEffect = new ColorAdjust();
                        blueEffect.setHue(-0.5);
                        z.getImageView().setEffect(blueEffect);
                        Timeline removeEffect = new Timeline(
                                new KeyFrame(Duration.seconds(6), ee -> zombie.setEffect(null))
                        );
                        removeEffect.setCycleCount(1);
                        removeEffect.play();


                        Timeline slowTimer = new Timeline(new KeyFrame(Duration.seconds(6), eee -> {
                            Zombie z2 = GameState.getInstance().getZombiesClass().get(zombie);
                            if (z2 != null) {
                                z2.setSpeed(currentSpeed);
                            }
                        }));
                        slowTimer.setCycleCount(1);
                        slowTimer.play();




                        int hp = GameState.getInstance().getZombiesHealth().get(zombie);
                        hp=hp-getAttackPower();


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
            movePea.setCycleCount(Timeline.INDEFINITE);
            movePea.play();
        } else if (bulletType==BulletType.Smoke) {
            Image peaImg = new Image(getClass().getResource("/images/puff.png").toExternalForm());
            ImageView pea = new ImageView(peaImg);
            pea.setFitWidth(20);
            pea.setFitHeight(20);
            pea.setLayoutX(getCol()+5);
            pea.setLayoutY(getRow() );
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
                        hp=hp-getAttackPower();


                        if (hp <= 0) {
                            GameState.getInstance().getZombiesClass().get(zombie).die();
                            GameRoot.getInstance().getGamePane().getChildren().remove(zombie);
                            GameState.getInstance().getZombies().remove(zombie);
                        } else {
                            GameState.getInstance().getZombiesHealth().put(zombie, hp);
                        }

                        return;
                    }
                }
                if (pea.getLayoutX() > 1200) {
                    GameRoot.getInstance().getGamePane().getChildren().remove(pea);
                }
            }));
            movePea.setCycleCount(Timeline.INDEFINITE);
            movePea.play();
            super.getAllTimelines().add(movePea);
            TimelineManager.getInstance().add(movePea);
        }else if(bulletType==BulletType.Fume){
            Image peaImg = new Image(getClass().getResource("/images/fume.png").toExternalForm());
            ImageView pea = new ImageView(peaImg);
            pea.setFitWidth(100);
            pea.setFitHeight(50);
            pea.setLayoutX(getCol()+35);
            pea.setLayoutY(getRow());
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

                        if (GameState.getInstance().getScreenDoorZombiesHealth().containsKey(zombie)) {
                            int hp = GameState.getInstance().getScreenDoorZombiesHealth().get(zombie);
                            hp = hp - getAttackPower();
                            if (hp <= 0) {
                                GameState.getInstance().getZombiesClass().get(zombie).die();
                                GameRoot.getInstance().getGamePane().getChildren().remove(zombie);
                                GameState.getInstance().getZombies().remove(zombie);
                            } else {
                                GameState.getInstance().getZombiesHealth().put(zombie, hp);
                                GameState.getInstance().getScreenDoorZombiesHealth().put(zombie, hp);
                            }
                        }else {
                            int hp = GameState.getInstance().getZombiesHealth().get(zombie);
                            hp = hp - getAttackPower();

                            if (hp <= 0) {
                                GameState.getInstance().getZombiesClass().get(zombie).die();
                                GameRoot.getInstance().getGamePane().getChildren().remove(zombie);
                                GameState.getInstance().getZombies().remove(zombie);
                            } else {
                                GameState.getInstance().getZombiesHealth().put(zombie, hp);
                            }
                        }

                        return;
                    }
                }
            }));

            movePea.setCycleCount(Timeline.INDEFINITE);
            movePea.play();

        }
    }

    public void startShooting() {
        shootTimeline = new Timeline(new KeyFrame(Duration.seconds(getAttackSpeed()), e -> Shoot()));
        shootTimeline.setCycleCount(Timeline.INDEFINITE);
        shootTimeline.play();
        TimelineManager.getInstance().add(shootTimeline);
        super.getAllTimelines().add(shootTimeline);

    }
    public void startShooting2() {
        shootTimeline2 = new Timeline(new KeyFrame(Duration.seconds(getAttackSpeed()+0.1), e -> Shoot()));
        shootTimeline2.setCycleCount(Timeline.INDEFINITE);
        shootTimeline2.play();
        TimelineManager.getInstance().add(shootTimeline2);
        super.getAllTimelines().add(shootTimeline2);

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
