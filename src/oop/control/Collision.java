package oop.control;
import oop.entities.character.Bomber;
import oop.graphics.CreateMap;
import oop.graphics.Sprite;
public class Collision {

  public static boolean colliSionUp(int _x, int _y) {
    return CreateMap.idMap[(int) ((_y + 4 - Bomber.getSpeed_y) / Sprite.SCALED_SIZE)][((_x + 4) / Sprite.SCALED_SIZE)].equals("-")
    && CreateMap.idMap[(int) ((_y + 4 - Bomber.getSpeed_y) / Sprite.SCALED_SIZE)][((_x + Sprite.SCALED_SIZE - 4) / Sprite.SCALED_SIZE)].equals("-");
  }

  public static boolean collisionDown(int _x, int _y) {
    return CreateMap.idMap[(int) ((_y + Sprite.SCALED_SIZE - 4 + Bomber.getSpeed_y) / Sprite.SCALED_SIZE)][((_x + 4) / Sprite.SCALED_SIZE)].equals("-")
    && CreateMap.idMap[(int) ((_y + Sprite.SCALED_SIZE - 4 + Bomber.getSpeed_y) / Sprite.SCALED_SIZE)][(_x + Sprite.SCALED_SIZE - 4) / Sprite.SCALED_SIZE].equals("-");
  }

  public static boolean colliSionRight(int _x, int _y) {
    return CreateMap.idMap[(_y + 4) / Sprite.SCALED_SIZE][(int) ((_x + Sprite.SCALED_SIZE - 4 + Bomber.getSpeed_x) / Sprite.SCALED_SIZE)].equals("-")
    && CreateMap.idMap[(_y + Sprite.SCALED_SIZE - 4)/ Sprite.SCALED_SIZE][(int) ((_x + Sprite.SCALED_SIZE - 4 + Bomber.getSpeed_x) / Sprite.SCALED_SIZE)].equals("-");
  }

  public static boolean collisionLeft(int _x, int _y) {
    return CreateMap.idMap[(_y + 4) / Sprite.SCALED_SIZE][(int) ((_x + 4 - Bomber.getSpeed_x) / Sprite.SCALED_SIZE)].equals("-")
    && CreateMap.idMap[(_y + Sprite.SCALED_SIZE - 4)/ Sprite.SCALED_SIZE][(int) ((_x + 4 - Bomber.getSpeed_x) / Sprite.SCALED_SIZE)].equals("-");
  }

}
