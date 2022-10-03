package oop.level;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.SepiaTone;
import javafx.scene.layout.HBox;
import oop.Board;
import oop.entities.block.Block;
import oop.entities.block.Brick;
import oop.entities.block.Grass;
import oop.entities.block.Portal;
import oop.entities.block.Wall;
import oop.entities.character.Bomber;
import oop.graphics.Sprite;

public class level {

  public static final int max_level = 4;

  private int width, height;

  private int level;

  private String[][] idMap;

  private Board board;

  private Block[][] tile;

  public void loadTileMap() {
    String path = "res/levels/lv" + level + ".txt";
    File file = new File(path);
    try (FileReader fr = new FileReader(file)) {

      Scanner sc = new Scanner(file);
      String line = sc.nextLine();

      String[] arr = line.split(" ");

      height = Integer.valueOf(arr[1]);
      width = Integer.valueOf(arr[2]);
      idMap = new String[height][width];
      tile = new Block[height][width];

      while (sc.hasNextLine()) {
        for (int i = 0; i < height; i++) {
          String lineTile = sc.nextLine();
          StringTokenizer st = new StringTokenizer(lineTile);
          for (int j = 0; j < width; j++) {
            String s = st.nextToken();
            switch (s) {
              case "#": {
                tile[i][j] = new Wall(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.wall.getFxImage(), board);
                idMap[i][j] = "#";
                break;
              }
              case "*": {
                tile[i][j] = new Brick(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.brick.getFxImage(), board,
                    new Grass(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.grass.getFxImage(), board));
                idMap[i][j] = "*";
                break;
              }
              case "x": {
                tile[i][j] = new Brick(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.brick.getFxImage(), board,
                    new Portal(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.portal.getFxImage(), board));
                idMap[i][j] = "x";
                break;
              }
              case "p": {
                tile[i][j] = new Grass(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.grass.getFxImage(),
                    board);
                board.addCharacter(new Bomber(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE,
                    Sprite.player_right.getFxImage(), board));
                idMap[i][j] = "p";
                break;
              }
              case "1":
                tile[i][j] = new Grass(j + Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.grass.getFxImage(),
                    board);
                // board.addCharacter(balloom)
                idMap[i][j] = "1";
                break;
              case "2":
                tile[i][j] = new Grass(j + Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.grass.getFxImage(),
                    board);
                // board.addCharacter(oneal)
                idMap[i][j] = "2";
                break;
              default:
                tile[i][j] = new Grass(j + Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.grass.getFxImage(),
                    board);
                // board.addCharacter(balloom)
                idMap[i][j] = "1";
                break;
            }
          }
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void render(GraphicsContext gc) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        tile[i][j].render(gc);
      }
    }
  }
}
