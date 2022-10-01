package oop.graphics;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import oop.BombermanGame;
import oop.entities.character.Bomber;
import oop.entities.character.bomb.Bomb;
import oop.entities.character.enemy.Balloom;
import oop.entities.character.enemy.Oneal;
import oop.entities.mapblock.Brick;
import oop.entities.Entity;
import oop.entities.mapblock.Grass;
import oop.entities.mapblock.Portal;
import oop.entities.mapblock.Wall;

import static oop.BombermanGame.*;

public class CreateMap {

  public static Entity ett;
  /**
   * constructor.
   */
  public static String[][] idMap = new String[13][31];
  public CreateMap(String lv) {
    final File file = new File(lv);

    try (FileReader fr = new FileReader(file)) {
      Scanner sc = new Scanner(file);
      String line = sc.nextLine();

      String[] arr  =line.split(" ");

      while (sc.hasNextLine()) {
        for (int i = 0; i < Integer.valueOf(arr[1]); i++) {
          String lineTile = sc.nextLine();
          StringTokenizer tokenTile = new StringTokenizer(lineTile);

          for (int j = 0; j < Integer.valueOf(arr[2]); j++) {
            String s = tokenTile.nextToken();
            switch(s) {
              case "#": 
                ett = new Wall(j, i, Sprite.wall.getFxImage());
                idMap[i][j] = "#";
                break;
              case "*": 
                ett = new Brick(j, i, Sprite.brick.getFxImage());
                idMap[i][j] = "*";
                break;
              case "x":
                ett = new Portal(j, i, Sprite.portal.getFxImage());
                idMap[i][j] = "x";
                break;
              case "p":
                ett = new Grass(j, i, Sprite.grass.getFxImage());
                idMap[i][j] = "-";
                bomberman = new Bomber(j, i, Sprite.player_right.getFxImage());
                entities.add(bomberman);
                break;
              case "1":
                ett = new Grass(j, i, Sprite.grass.getFxImage());
                idMap[i][j] = "-";
                Entity balloom = new Balloom(j, i, Sprite.balloom_left1.getFxImage());
                entities.add(balloom);
                break;
              case "2":
                ett = new Grass(j, i, Sprite.grass.getFxImage());
                idMap[i][j] = "-";
                Entity oneal = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                entities.add(oneal);
                break;
              default:
                ett = new Grass(j, i, Sprite.grass.getFxImage());
                idMap[i][j] = "-";
                break;
            }
            stillObjects.add(ett);
          }
        }
      }
      sc.close();
    } 
    
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*public static String[][] IdMap(String lv) {
    final File file = new File(lv);

    try (FileReader fr = new FileReader(file)) {
      Scanner sc = new Scanner(file);
      String line = sc.nextLine();

      String[] arr  =line.split(" ");

      while (sc.hasNextLine()) {
        for (int i = 0; i < Integer.valueOf(arr[1]); i++) {
          String lineTile = sc.nextLine();
          StringTokenizer tokenTile = new StringTokenizer(lineTile);

          for (int j = 0; j < Integer.valueOf(arr[2]); j++) {
            String s = tokenTile.nextToken();
            IdMap[j][i] = s;
          }
        }
      }
      sc.close();
    } 
    
    catch (Exception e) {
      // e.printStackTrace();
    }
    return IdMap;
  }*/
}
