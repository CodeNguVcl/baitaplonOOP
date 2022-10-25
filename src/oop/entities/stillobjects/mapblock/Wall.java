package oop.entities.stillobjects.mapblock;

import javafx.scene.image.Image;
import oop.entities.Entity;

public class Wall extends Block {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        setLayer(4);
    }

    @Override
    public void update() {

    }
}
