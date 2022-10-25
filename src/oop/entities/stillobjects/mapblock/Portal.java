package oop.entities.stillobjects.mapblock;

import javafx.scene.image.Image;
import oop.entities.Entity;

public class Portal extends Block {

    public Portal(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1);
    }

    @Override
    public void update() {

    }
}
