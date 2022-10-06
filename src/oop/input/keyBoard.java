package oop.input;

import javafx.scene.input.KeyCode;

public class keyBoard {

  private boolean[] keys = new boolean[120];
  public boolean up, down, left, right, space;

  public keyBoard() {

  }

  public void update() {
    up = keys[KeyCode.UP.getCode()] || keys[KeyCode.W.getCode()];
    down = keys[KeyCode.DOWN.getCode()] || keys[KeyCode.S.getCode()];
    left = keys[KeyCode.LEFT.getCode()] || keys[KeyCode.A.getCode()];
    right = keys[KeyCode.RIGHT.getCode()] || keys[KeyCode.D.getCode()];
    space = keys[KeyCode.SPACE.getCode()];
  }
}
