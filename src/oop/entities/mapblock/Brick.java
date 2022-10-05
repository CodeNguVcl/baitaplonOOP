package oop.entities.mapblock;

import javafx.scene.image.Image;
import oop.entities.Entity;

public class Brick extends Entity {
  public Brick(int x, int y, Image img) {
    super(x, y, img);
    setLayer(3);
    live = true;
  }

  @Override
  public void update() {
    // bom no vao tuong
  }
}
