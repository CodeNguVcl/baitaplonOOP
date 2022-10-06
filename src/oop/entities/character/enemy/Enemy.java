package oop.entities.character.enemy;

import javafx.scene.image.Image;
import oop.entities.character.Character;

public abstract class Enemy extends Character {
  protected int startX;
  protected int startY;

  public Enemy(int xUnit, int yUnit, Image img) {
    super(xUnit, yUnit, img);
    setLayer(9);
    startX = xUnit;
    startY = yUnit;
  }

  public abstract void restartEnemy();

  public abstract void generateDirection();

}
