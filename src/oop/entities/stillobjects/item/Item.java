package oop.entities.stillobjects.item;

import javafx.scene.image.Image;
import oop.entities.stillobjects.StillObject;

public abstract class Item extends StillObject {
    public Item(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1); // chỉ số va chạm của các Item
    }
}
