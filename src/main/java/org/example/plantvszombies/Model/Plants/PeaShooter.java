package org.example.plantvszombies.Model.Plants;

import org.example.plantvszombies.Model.BulletType;
import org.example.plantvszombies.Model.WarriorPlant;

public class PeaShooter extends WarriorPlant {
    public PeaShooter(int row, int col) {
        super(100, "PeaShooter", 10, 3, BulletType.Normal);
        setRow(row);
        setCol(col);
    }
}
