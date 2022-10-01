package oop.control;
import oop.entities.character.Bomber;
import oop.graphics.CreateMap;
import oop.graphics.Sprite;
public class Collision {

  public static boolean colliSionUp(int _x, int _y) {
    return CreateMap.idMap[(int) ((_y - Bomber.getSpeed_y) / Sprite.SCALED_SIZE)][((_x + 6) / Sprite.SCALED_SIZE)].equals("-")
    && CreateMap.idMap[(int) ((_y - Bomber.getSpeed_y) / Sprite.SCALED_SIZE)][((_x + Sprite.SCALED_SIZE - 12) / Sprite.SCALED_SIZE)].equals("-");
  }

  public static boolean collisionDown(int _x, int _y) {
    return CreateMap.idMap[(int) ((_y + Sprite.SCALED_SIZE + Bomber.getSpeed_y) / Sprite.SCALED_SIZE)][((_x + 6) / Sprite.SCALED_SIZE)].equals("-")
    && CreateMap.idMap[(int) ((_y + Sprite.SCALED_SIZE + Bomber.getSpeed_y) / Sprite.SCALED_SIZE)][(_x + Sprite.SCALED_SIZE - 12) / Sprite.SCALED_SIZE].equals("-");
  }

  public static boolean colliSionRight(int _x, int _y) {
    return CreateMap.idMap[(int) (_y + 6) / Sprite.SCALED_SIZE][(int) ((_x + Sprite.SCALED_SIZE - 9 + Bomber.getSpeed_x) / Sprite.SCALED_SIZE)].equals("-")
    && CreateMap.idMap[(int) (_y + Sprite.SCALED_SIZE - 6)/ Sprite.SCALED_SIZE][(int) ((_x + Sprite.SCALED_SIZE - 9 + Bomber.getSpeed_x) / Sprite.SCALED_SIZE)].equals("-");
  }

  public static boolean collisionLeft(int _x, int _y) {
    return CreateMap.idMap[(int) (_y + 6) / Sprite.SCALED_SIZE][(int) ((_x - Bomber.getSpeed_x) / Sprite.SCALED_SIZE)].equals("-")
    && CreateMap.idMap[(int) (_y + Sprite.SCALED_SIZE - 6)/ Sprite.SCALED_SIZE][(int) ((_x - Bomber.getSpeed_x) / Sprite.SCALED_SIZE)].equals("-");
  }

}
