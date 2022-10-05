package oop.entities.mapblock;

import javafx.scene.image.Image;
import oop.entities.Entity;

public class Grass extends Entity {

    public Grass(int x, int y, Image img) {
        super(x, y, img);
        setLayer(0);
    }

    @Override
    public void update() {

    }
}
