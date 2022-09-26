package oop.graphics;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import oop.BombermanGame;
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
                break;
              case "*": 
                ett = new Brick(j, i, Sprite.brick.getFxImage());
                break;
              case "x":
                ett = new Portal(j, i, Sprite.portal.getFxImage());
                break;
              default:
                ett = new Grass(j, i, Sprite.grass.getFxImage());
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

  public static String[][] IdMap(String lv) {
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
  }
}
