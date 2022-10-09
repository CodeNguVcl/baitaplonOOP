package oop.entities.mapblock;

import javafx.scene.image.Image;
import oop.BombermanGame;
import oop.entities.Entity;
import oop.graphics.Sprite;

import static oop.graphics.CreateMap.idMap;

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
      idMap[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] = "-";
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
