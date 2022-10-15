package oop.entities.character;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
//import java.awt.*;
import javafx.scene.transform.Scale;
import oop.entities.Entity;
import oop.graphics.Sprite;

import oop.entities.character.bomb.Bomb;
// import oop.sound.Sound;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Character {

    public static final List<Bomb> bombs = new ArrayList<>();

    private int bombRemain;

    private KeyCode direction = null;

    private boolean isPutBomb = false;

    public static Entity bomb;

    private int flameRadius;

    private double accelration;
    private double bomber_speed = (double) Sprite.SCALE;

    private double max_speed = (double) Sprite.SCALE * 3;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1);
        setSpeed((int) bomber_speed);
        setAccelration(0);
        setBombRemain(1);
        setFlameRadius(1);
    }

    @Override
    public void update() {
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (!bomb.isLive()) {
                bombs.remove(bomb);
                bombRemain++;
            }
        }

        if (!isLive()) {
            direction = null;
            animated++;
            if (animated < 120) {
                img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animated, 120)
                        .getFxImage();
            } else {
                this.setLive(true);
                img = Sprite.player_right.getFxImage();
                posX = 48;
                posY = 48;
                // Sound.play("stageStart");
                animated = 0;
            }
        } else {
            if (direction == KeyCode.UP) {
                turnUp();
            }

            if (direction == KeyCode.DOWN) {
                turnDown();
            }

            if (direction == KeyCode.LEFT) {
                turnLeft();
            }

            if (direction == KeyCode.RIGHT) {
                turnRight();
            }

            if (isPutBomb) {
                putBomb();
            }
        }
    }

    public void handleKeyPressed(KeyCode keyCode) {
        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT
                || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN) {
            this.direction = keyCode;
        }
        if (keyCode == KeyCode.SPACE) {
            isPutBomb = true;
        }
        speed += (int) getAccelration();
        if (speed >= max_speed) {
            speed = (int) max_speed;
        }
    }

    public void handleKeyReleased(KeyCode keyCode) {
        if (direction == keyCode) {
            if (direction == KeyCode.LEFT) {
                img = Sprite.player_left.getFxImage();
            }
            if (direction == KeyCode.RIGHT) {
                img = Sprite.player_right.getFxImage();
            }
            if (direction == KeyCode.UP) {
                img = Sprite.player_up.getFxImage();
            }
            if (direction == KeyCode.DOWN) {
                img = Sprite.player_down.getFxImage();
            }
            direction = null;
        }
        if (keyCode == KeyCode.SPACE) {
            isPutBomb = false;
        }
        speed = (int) bomber_speed;
    }

    public void turnUp() {
        super.turnUp();
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animation++, 20)
                .getFxImage();
    }

    public void turnDown() {
        super.turnDown();
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animation++, 20)
                .getFxImage();
    }

    public void turnLeft() {
        super.turnLeft();
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animation++, 20)
                .getFxImage();
    }

    public void turnRight() {
        super.turnRight();
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animation++, 20)
                .getFxImage();
    }

    public void putBomb() {
        if (bombRemain > 0) {
            int xB = (int) Math.round((x + 4) / (double) Sprite.SCALED_SIZE);
            int yB = (int) Math.round((y + 4) / (double) Sprite.SCALED_SIZE);

            for (Bomb bomb : bombs) {
                if (xB * Sprite.SCALED_SIZE == bomb.getX() && yB * Sprite.SCALED_SIZE == bomb.getY())
                    return;
            }
            // Sound.play("bombPut");
            bombs.add(new Bomb(xB, yB, Sprite.bomb.getFxImage(), flameRadius));
            bombRemain--;
        }
    }

    public int getBombRemain() {
        return this.bombRemain;
    }

    public void setBombRemain(int bombRemain) {
        this.bombRemain = bombRemain;
    }

    public int getFlameRadius() {
        return this.flameRadius;
    }

    public void setFlameRadius(int radius) {
        this.flameRadius = radius;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public double getAccelration() {
        return this.accelration;
    }

    public void setAccelration(double accelration) {
        this.accelration = accelration;
    }

    public Rectangle getBounds() {
        return new Rectangle(posX + Sprite.SCALE * 2, posY + Sprite.SCALE * 2.5, Sprite.SCALED_SIZE / 2,
                Sprite.SCALED_SIZE * 5 / 8);
    }

    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
