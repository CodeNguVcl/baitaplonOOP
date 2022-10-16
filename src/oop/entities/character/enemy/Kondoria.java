package oop.entities.character.enemy;

import javafx.scene.image.Image;
import oop.BombermanGame;
import oop.graphics.Sprite;

import java.util.Random;

public class Kondoria extends Enemy {
    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(3);
        setSpeed(4);
        generateDirection();
        live = true;
        point = 200;
    }

    @Override
    public void restartEnemy() {

    }

    @Override
    public void generateDirection() {
        Random r = new Random();
        direction = r.nextInt(4);
    }

    @Override
    public void update() {
        if (isLive()) {
            if (direction == 0) {
                turnUp();
            }
            if (direction == 1) {
                turnDown();
            }
            if (direction == 2) {
                turnLeft();
            }
            if (direction == 3) {
                turnRight();
            }
        } else if (animated < 30) {
            animated++;
            img = Sprite.kondoria_dead.getFxImage();
            img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animated, 20)
                    .getFxImage();
        } else {
            BombermanGame.enemy.remove(this);
        }
    }

    public void turnUp() {
        super.turnUp();
        img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animation++, 18)
                .getFxImage();
    }

    public void turnDown() {
        super.turnDown();
        img = Sprite
                .movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animation++, 18)
                .getFxImage();
    }

    public void turnLeft() {
        super.turnLeft();
        img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animation++, 18)
                .getFxImage();
    }

    public void turnRight() {
        super.turnRight();
        img = Sprite
                .movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animation++, 18)
                .getFxImage();
    }

    @Override
    public void stop() {
        super.stop();
        generateDirection();
    }
}
