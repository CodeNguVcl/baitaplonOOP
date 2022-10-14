package oop.graphics;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import oop.Layer;
import oop.entities.character.Bomber;

import oop.entities.character.enemy.Balloom;
import oop.entities.character.enemy.Enemy;
// import oop.entities.character.enemy.Balloom;
import oop.entities.character.enemy.Oneal;
import oop.entities.item.BombItem;
import oop.entities.item.FlameItem;
import oop.entities.item.SpeedItem;
import oop.entities.mapblock.Brick;
import oop.entities.Entity;
import oop.entities.mapblock.Grass;
import oop.entities.mapblock.Portal;
import oop.entities.mapblock.Wall;

import static oop.BombermanGame.*;

public class CreateMap {

  public static Entity ett;

  private int level;

  public static String[][] idMap = new String[HEIGHT][WIDTH];

  public CreateMap(int level) {
    this.level = level;
    readTileMap();
  }

  public int getLevel() {
    return this.level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void readTileMap() {
    String srcLevel = "res/levels/lv" + level + ".txt";
    final File file = new File(srcLevel);

    try (FileReader fr = new FileReader(file)) {
      Scanner sc = new Scanner(file);
      String line = sc.nextLine();

      String[] arr = line.split(" ");
      int _height = Integer.valueOf(arr[1]);
      int _width = Integer.valueOf(arr[2]);

      while (sc.hasNextLine()) {
        for (int i = 0; i < _height; i++) {
          String lineTile = sc.nextLine();
          StringTokenizer tokenTile = new StringTokenizer(lineTile);

          for (int j = 0; j < _width; j++) {
            String s = tokenTile.nextToken();

            switch (s) {
              case "#":
                ett = new Wall(j, i, Sprite.wall.getFxImage());
                idMap[i][j] = "#";
                break;
              case "*":
                stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                ett = new Brick(j, i, Sprite.brick.getFxImage());
                idMap[i][j] = "*";
                break;
              case "x":
                ett = new Portal(j, i, Sprite.portal.getFxImage());
                idMap[i][j] = "x";
                stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                break;
              case "p":
                ett = new Grass(j, i, Sprite.grass.getFxImage());
                idMap[i][j] = "-";
                bomberman = new Bomber(j, i, Sprite.player_right.getFxImage());
                // entities.add(bomberman);
                break;
              case "1":
                ett = new Grass(j, i, Sprite.grass.getFxImage());
                idMap[i][j] = "-";
                Enemy balloom = new Balloom(j, i, Sprite.balloom_left1.getFxImage());
                enemy.add(balloom);
                break;
              case "2":
                ett = new Grass(j, i, Sprite.grass.getFxImage());
                idMap[i][j] = "-";
                Enemy oneal = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                enemy.add(oneal);
                break;
              case "b":
                stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                stillObjects.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                idMap[i][j] = "*";
                break;
              case "f":
                stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                stillObjects.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                idMap[i][j] = "*";
                break;
              case "s":
                stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                stillObjects.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                idMap[i][j] = "*";
                break;
              default:
                ett = new Grass(j, i, Sprite.grass.getFxImage());
                idMap[i][j] = "-";
                break;
            }
            stillObjects.add(ett);
          }
        }
        stillObjects.sort(new Layer());
      }
      sc.close();
    }

    catch (Exception e) {
      e.printStackTrace();
    }
  }
}