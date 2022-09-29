package oop.entities.character;

import javafx.scene.image.Image;
import oop.control.Collision;
import oop.graphics.Sprite;

import java.util.Random;

public class Balloom extends Character {
    Random random = new Random();
    private int rand = random.nextInt(4);

    private final int balloom_time = 18;

    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void move() {

        //if (x % 32 == 0 && y % 32 == 0) {

        if (x % 32 == 0 && y % 32 == 0) {
            rand = random.nextInt(4);
        }
        switch (rand) {
            case 0: {
                direction = "up";
                if (Collision.colliSionUp(x, y))
                    y -= Sprite.SCALED_SIZE / 32;
                else
                    //y = (y / (Sprite.SCALED_SIZE) + 1) * (Sprite.SCALED_SIZE);
                    rand = random.nextInt(4);
                break;
            }
            case 1: {
                direction = "down";
                if (Collision.collisionDown(x, y))
                    y += Sprite.SCALED_SIZE / 32;
                else
                    //y = y / (Sprite.SCALED_SIZE) * (Sprite.SCALED_SIZE);
                    rand = random.nextInt(4);
                break;
            }

            case 2: {
                direction = "left";
                if (Collision.collisionLeft(x, y))
                    x -= Sprite.SCALED_SIZE / 32;
                else
                    //x = (x / (Sprite.SCALED_SIZE) + 1) * (Sprite.SCALED_SIZE);
                    rand = random.nextInt(4);
                break;
            }

            case 3: {
                direction = "right";
                if (Collision.colliSionRight(x, y))
                    x += Sprite.SCALED_SIZE / 32;
                else
                    //x = x / (Sprite.SCALED_SIZE) * (Sprite.SCALED_SIZE);
                    rand = random.nextInt(4);
                break;
            }
            default:
                break;
        }
        // }
    }

    @Override
    public void update() {
        move();
        switch (direction) {
            case "up": {
                /*if (step == 0) {
                    img = Sprite.balloom_right1.getFxImage();
                }
                if (step == 1) {
                    img = Sprite.balloom_right3.getFxImage();
                }
                if (step == 2) {
                    img = Sprite.balloom_right1.getFxImage();
                }
                if (step == 3) {
                    img = Sprite.balloom_right2.getFxImage();
                }*/
                Sprite balloom_animation = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right3, Sprite.balloom_right2, stepCount, balloom_time);
                img = balloom_animation.getFxImage();
                break;
            }
            case "down": {
                /*if (step == 0) {
                    img = Sprite.balloom_left1.getFxImage();
                }
                if (step == 1) {
                    img = Sprite.balloom_left3.getFxImage();
                }
                if (step == 2) {
                    img = Sprite.balloom_left1.getFxImage();
                }
                if (step == 3) {
                    img = Sprite.balloom_left2.getFxImage();
                }*/
                Sprite balloom_animation = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left3, Sprite.balloom_left2, stepCount, balloom_time);
                img = balloom_animation.getFxImage();
                break;
            }
            case "left": {
                /*if (step == 0) {
                    img = Sprite.balloom_left1.getFxImage();
                }
                if (step == 1) {
                    img = Sprite.balloom_left2.getFxImage();
                }
                if (step == 2) {
                    img = Sprite.balloom_left1.getFxImage();
                }
                if (step == 3) {
                    img = Sprite.balloom_left3.getFxImage();
                }*/
                Sprite balloom_animation = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, stepCount, balloom_time);
                img = balloom_animation.getFxImage();
                break;
            }
            case "right": {
                /*if (step == 0) {
                    img = Sprite.balloom_right1.getFxImage();
                }
                if (step == 1) {
                    img = Sprite.balloom_right2.getFxImage();
                }
                if (step == 2) {
                    img = Sprite.balloom_right1.getFxImage();
                }
                if (step == 3) {
                    img = Sprite.balloom_right3.getFxImage();
                }*/
                Sprite balloom_animation = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, stepCount, balloom_time);
                img = balloom_animation.getFxImage();
                break;
            }
        }
        stepCount++;
        if (stepCount > 1000) {
            stepCount = 0;
        }
        /*if (stepCount == 8) {
            if (step == 3) {
                step = 0;
            } else {
                step++;
            }
            stepCount = 0;
        }*/
    }
}
