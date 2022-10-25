package oop.entities.stillobjects.mapblock;

import javafx.scene.image.Image;
import oop.entities.stillobjects.StillObject;

public abstract class Block extends StillObject {

    public Block(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Block(int x, int y) {
        super(x, y);
    }
}
