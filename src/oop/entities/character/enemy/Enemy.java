package oop.entities.character.enemy;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import oop.entities.character.Character;
import oop.graphics.Sprite;

public abstract class Enemy extends Character {
  protected int startX;
  protected int startY;

  protected int direction;
  protected int point;

  public Enemy(int xUnit, int yUnit, Image img) {
    super(xUnit, yUnit, img);
    setLayer(9);
    startX = xUnit;
    startY = yUnit;
    point = 0;
  }

  public Rectangle getBounds() {
    return new Rectangle(posX + 2, posY + 2, Sprite.SCALED_SIZE * 7 / 8, Sprite.SCALED_SIZE * 7 / 8);
  }

  public abstract void restartEnemy();

  public abstract void generateDirection();

  public int getDirection() {
    return direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public int getStartX() {
    return startX;
  }

  public void setStartX(int startX) {
    this.startX = startX;
  }

  public int getStartY() {
    return startY;
  }

  public void setStartY(int startY) {
    this.startY = startY;
  }

  public int getPoint() {
    return point;
  }

  public void setPoint(int point) {
    this.point = point;
  }
}
