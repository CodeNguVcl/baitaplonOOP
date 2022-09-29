package oop.entities;

import javafx.scene.image.Image;
import oop.entities.character.Bomber;
import oop.entities.character.Character;
import oop.graphics.Sprite;

import static oop.BombermanGame.bomberman;
import static oop.BombermanGame.entities;
import static oop.entities.character.Bomber.bomb;


public class Bomb extends Entity {
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    private int animate = 0;
    private final int time = 9;
    public static void putBomb() {
        int bomb_x = bomberman.getX() / Sprite.SCALED_SIZE;
        int bomb_y = bomberman.getY() / Sprite.SCALED_SIZE;
        bomb = new Bomb(bomb_x, bomb_y, Sprite.bomb.getFxImage());
        entities.add(bomb);
    }

    public void loadAnimation() {
        Sprite bomb_animation = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, time);
        img = bomb_animation.getFxImage();
        animate++;
    }

    @Override
    public void update() {
        loadAnimation();
    }

}

