package oop.entities.stillobjects;

import javafx.scene.image.Image;
import oop.entities.Entity;

public abstract class StillObject extends Entity {

    public StillObject(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public StillObject(int x, int y) {
        super(x, y);
    }
}
