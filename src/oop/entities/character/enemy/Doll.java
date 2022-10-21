package oop.entities.character.enemy;

import javafx.scene.image.Image;
import oop.BombermanGame;
import oop.graphics.Sprite;

import java.util.Random;

public class Doll extends Enemy {
    public Doll(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
        setSpeed(Sprite.SCALE / 2);
        generateDirection();
        live = true;
        point = 150;
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
            img = Sprite.doll_dead.getFxImage();
        } else {
            BombermanGame.enemy.remove(this);
        }
    }

    public void turnUp() {
        super.turnUp();
        img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animation++, 18)
                .getFxImage();
    }

    public void turnDown() {
        super.turnDown();
        img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animation++, 18)
                .getFxImage();
    }

    public void turnLeft() {
        super.turnLeft();
        img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animation++, 18)
                .getFxImage();
    }

    public void turnRight() {
        super.turnRight();
        img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animation++, 18)
                .getFxImage();
    }

    @Override
    public void stop() {
        super.stop();
        generateDirection();
        generateSpeed();
    }

    public void generateSpeed() {
        Random r = new Random();
        setSpeed(Sprite.SCALE / 2 * (1 + r.nextInt(4)));
    }
}


