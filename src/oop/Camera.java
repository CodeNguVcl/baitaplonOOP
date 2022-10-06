package oop;

import oop.entities.Entity;
import oop.graphics.Sprite;

public class Camera {

  private int x;

  private int y;

  private Board board;

  public Camera(Board board, int x, int y) {
    this.board = board;
    this.x = x;
    this.y = y;
  }

  public void centerEntity(Entity entity) {
    x = entity.getX() - BombermanGame.WIDTH * Sprite.SCALED_SIZE / 4;
    y = entity.getY() - BombermanGame.HEIGHT * Sprite.SCALED_SIZE / 4;

    if (x < 0) {
      x = 0;
    }
  }

  public int getX() {
    return this.x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return this.y;
  }

  public void setY(int y) {
    this.y = y;
  }
}
