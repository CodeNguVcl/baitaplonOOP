package oop.entities.mapblock;

import javafx.scene.image.Image;
import oop.BombermanGame;
import oop.entities.Entity;
import oop.graphics.Sprite;

public class Brick extends Entity {
  public Brick(int x, int y, Image img) {
    super(x, y, img);
    setLayer(3);
    live = true;
  }

  @Override
  public void update() {
    // bom no vao tuong
    if (!isLive()) {
      if (animated < 45) {
        animated++;
        img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animated, 45)
            .getFxImage();
      } else {
        BombermanGame.stillObjects.remove(this);
      }
    }
  }
}
