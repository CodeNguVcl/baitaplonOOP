package oop.entities.mapblock;

import javafx.scene.image.Image;
import oop.entities.Entity;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        setLayer(4);
    }

    @Override
    public void update() {

    }
}
