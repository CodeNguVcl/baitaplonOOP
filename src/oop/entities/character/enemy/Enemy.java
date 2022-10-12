package oop.entities.character.enemy;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import oop.entities.character.Character;
import oop.graphics.Sprite;

public abstract class Enemy extends Character {
  protected int startX;
  protected int startY;

  public Enemy(int xUnit, int yUnit, Image img) {
    super(xUnit, yUnit, img);
    setLayer(9);
    startX = xUnit;
    startY = yUnit;
  }

  public Rectangle getBounds() {
    return new Rectangle(posX + 2, posY + 2, Sprite.SCALED_SIZE * 7 / 8, Sprite.SCALED_SIZE * 7 / 8);
  }

  public abstract void restartEnemy();

  public abstract void generateDirection();

}
