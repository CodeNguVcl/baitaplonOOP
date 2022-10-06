package oop.entities.character;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
//import java.awt.*;
import oop.entities.Entity;
import oop.graphics.Sprite;

import oop.entities.character.bomb.Bomb;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Character {

    public static final List<Bomb> bombs = new ArrayList<>();

    private int bombRemain;

    private KeyCode direction = null;

    private boolean isPutBomb = false;

    public static Entity bomb;

    private int radius;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1);
        setSpeed(2);
        setBombRemain(1);
        setRadius(1);
    }

    @Override
    public void update() {
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

        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (!bomb.isLive()) {
                bombs.remove(bomb);
                bombRemain++;
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
    }

    public void turnUp() {
        super.turnUp();
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, up++, 20).getFxImage();
    }

    public void turnDown() {
        super.turnDown();
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, down++, 20)
                .getFxImage();
    }

    public void turnLeft() {
        super.turnLeft();
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, left++, 20)
                .getFxImage();
    }

    public void turnRight() {
        super.turnRight();
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, right++, 20)
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
            bombs.add(new Bomb(xB, yB, Sprite.bomb.getFxImage(), radius));
            bombRemain--;
            System.out.println(bombRemain);
        }
    }

    public int getBombRemain() {
        return this.bombRemain;
    }

    public void setBombRemain(int bombRemain) {
        this.bombRemain = bombRemain;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public Rectangle getBounds() {
        return new Rectangle(posX + 6, posY + 6, Sprite.SCALED_SIZE - 16, Sprite.SCALED_SIZE - 10);
    }

    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
