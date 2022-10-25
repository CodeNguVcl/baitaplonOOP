package oop.entities.character;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
//import java.awt.*;
import oop.control.Move;
import oop.entities.Entity;
import oop.graphics.Sprite;

public abstract class Character extends Entity implements Move {

    protected int animation = 0;

    protected final int max_animation = 7500;

    protected int posX = x;

    protected int posY = y;

    protected int speed;

    // protected int up = 0;
    // protected int down = 0;
    // protected int left = 0;
    // protected int right = 0;

    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        live = true;
    }

    public Character(int x, int y) {
        super(x, y);
    }

    @Override
    public void turnUp() {
        posY = y - speed;
    }

    @Override
    public void turnDown() {
        posY = y + speed;
    }

    @Override
    public void turnLeft() {
        posX = x - speed;
    }

    @Override
    public void turnRight() {
        posX = x + speed;
    }

    public void move() {
        x = posX;
        y = posY;
    }

    public void stop() {
        posX = x;
        posY = y;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    protected void animate() {
        if (animation < max_animation) {
            animation++;
        } else {
            animation = 0;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(posX, posY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

}
