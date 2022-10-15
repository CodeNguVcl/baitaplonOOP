package oop.entities.character.enemy;

import java.util.Random;

import javafx.scene.image.Image;
import oop.BombermanGame;
import oop.graphics.Sprite;

public class Balloom extends Enemy {

  public Balloom(int xUnit, int yUnit, Image img) {
    super(xUnit, yUnit, img);
    setLayer(1);
    setSpeed(Sprite.SCALE / 2);
    generateDirection();
    live = true;
    point = 100;
  }

  @Override
  public void restartEnemy() {

  }

  @Override
  public void generateDirection() {
    Random r = new Random();
    direction = r.nextInt(4);
  }

  @Override
  public void update() {
    if (isLive()) {
      if (direction == 0) {
        turnUp();
      }
      if (direction == 1) {
        turnDown();
      }
      if (direction == 2) {
        turnLeft();
      }
      if (direction == 3) {
        turnRight();
      }
    } else if (animated < 30) {
      animated++;
      img = Sprite.balloom_dead.getFxImage();
    } else {
      BombermanGame.enemy.remove(this);
    }
  }

  public void turnUp() {
    super.turnUp();
    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animation++, 18)
        .getFxImage();
  }

  public void turnDown() {
    super.turnDown();
    img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animation++, 18)
        .getFxImage();
  }

  public void turnLeft() {
    super.turnLeft();
    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animation++, 18)
        .getFxImage();
  }

  public void turnRight() {
    super.turnRight();
    img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animation++, 18)
        .getFxImage();
  }

  @Override
  public void stop() {
    super.stop();
    generateDirection();
  }

}
