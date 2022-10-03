package oop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import oop.entities.*;
import oop.entities.character.bomb.Bomb;
import oop.entities.character.bomb.Flame;
import oop.graphics.Sprite;
import oop.entities.character.Character;;

public class Board {

  public static int bomb_rate = 1;

  public static int bomber_speed = 3;

  public static int flame_length = 1;

  private List<Character> characters = new ArrayList<>();

  private List<Bomb> bombs = new ArrayList<>();

  private Camera camera;

  public static final int default_time = 180;

  private boolean win = false;

  public Board() {
    // level = new level(this, 1)
    // level.addEntity();
    // score = 0;
  }

  public Camera getCamera() {
    return this.camera;
  }

  public void setCamera(Camera camera) {
    this.camera = camera;
  }

  public boolean isWin() {
    return this.win;
  }

  public void setWin(boolean win) {
    this.win = win;
  }

  public List<Bomb> getBombs() {
    return this.bombs;
  }

  public void setBombs(List<Bomb> bombs) {
    this.bombs = bombs;
  }

  public void addBomb(Bomb bomb) {
    bombs.add(bomb);
  }

  public void addCharacter(Character character) {
    characters.add(character);
  }

  // get bombs
  public Bomb getBombAt(int x, int y) {
    Iterator<Bomb> bs = bombs.iterator();
    Bomb b;

    while (bs.hasNext()) {
      b = bs.next();
      if (b.getX() == x * Sprite.SCALED_SIZE && b.getY() == y * Sprite.SCALED_SIZE) {
        return b;
      }
    }
    return null;
  }

  /** Get flame */
  public Flame getFlameAt(int x, int y) {
    Iterator<Bomb> bs = bombs.iterator();
    Bomb b;

    while (bs.hasNext()) {
      b = bs.next();
      Flame e = b.flameAt(x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
      if (e != null) {
        return e;
      }
    }
    return null;
  }

  public Entity getEntityAt(int x, int y, Character exceptCharacter) {
    Entity e = null;
    e = getFlameAt(x, y);

    if (e != null) {
      return e;
    }

    e = getBombAt(x, y);
    if (e != null) {
      return e;
    }

    return e;

  }

  public void render(GraphicsContext gc) {
    // level.render(gc);
    bombs.forEach(g -> g.render(gc));
    characters.forEach(g -> g.render(gc));
  }

}
