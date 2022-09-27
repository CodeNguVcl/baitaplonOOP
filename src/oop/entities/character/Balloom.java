package oop.entities.character;

import javafx.scene.image.Image;
import oop.graphics.Sprite;

import java.util.Random;
public class Balloom extends Character {
    Random random = new Random();
    private int rand = random.nextInt(4);
    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void move() {

        //if (x % 32 == 0 && y % 32 == 0) {

        if (x%32 == 0 && y%32 == 0) {
            rand = random.nextInt(4);
        }
            switch (rand) {
                case 0: {
                    direction = "up";
                    y -= Sprite.SCALED_SIZE / 16;

                    break;
                }
                case 1: {
                    direction = "down";
                    y += Sprite.SCALED_SIZE / 16;
                    break;
                }
            
                case 2: {
                    direction = "left";
                    x -= Sprite.SCALED_SIZE / 16;
                    break;
                }
                
                case 3: {
                    direction = "right";
                    x += Sprite.SCALED_SIZE / 16;
                    break;
                }
            }
       // }
    }

    @Override
    public void update() {
        move();
        switch(direction) {
            case "up" : {
                if (step == 0) {
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
                }
                break;
            }
            case "down" : {
                if (step == 0) {
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
                }
                break;
            }
            case "left" : {
                if (step == 0) {
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
                }
                break;
            }
            case "right" : {
                if (step == 0) {
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
                }
                break;
            }
        }
        stepCount++;
        if (stepCount == 8) {
            if (step == 3) {
                step = 0;
            }
            else {
                step++;
            }
            stepCount = 0;
        }
    }
}
