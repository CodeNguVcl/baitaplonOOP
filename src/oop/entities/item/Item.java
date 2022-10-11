package oop.entities.item;

import javafx.scene.image.Image;
import oop.entities.Entity;

public abstract class Item extends Entity {
    public Item(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1); // chỉ số va chạm của các Item
    }
}
