package org.example.plantvszombies.Model;

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




    public static void Shot(){}








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
