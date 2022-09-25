package oop.entities;

import javafx.event.EventHandler;

import javax.crypto.spec.IvParameterSpec;
import javax.swing.SpringLayout;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import oop.graphics.CreateMap;
import oop.graphics.Sprite;

import oop.BombermanGame;
import oop.entities.animation.Animation;

import static oop.BombermanGame.IdMap;

import oop.graphics.CreateMap.*;
public class Bomber extends Entity {

    /**direction */
    private double dirX = 0;
    private double dirY = 0;

    /**status (up down left right) */
    public static final int IDLE = 0;
    public static final int DOWN = 1;
    public static final int UP = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    public static final int DEAD = 5;
    private int currStt = Bomber.IDLE;

    public Image image;

    private Animation[] sttanm;

    /*speed + accel */
    private double speed_x = 1;
    private double speed_y = 1;
    final private double accelration = 0.5;


    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    private void move() {
        BombermanGame.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override 
            public void handle(KeyEvent keyEvent) {
                this.handleEvent(keyEvent);
            }

            private void handleEvent(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case UP: {
                        currStt = Bomber.UP;
                        dirX = 0;
                        dirY = -1;
                        image = Sprite.player_up.getFxImage();
                        break;
                    }
                    case DOWN: {
                        currStt = Bomber.DOWN;
                        dirX = 0;
                        dirY = 1;
                        image = Sprite.player_down.getFxImage();
                        break;
                    }
                    case LEFT: {
                        currStt = Bomber.LEFT;
                        dirX = -1;
                        dirY = 0;
                        image = Sprite.player_left.getFxImage();
                        break;
                    }
                    case RIGHT: {
                        currStt = Bomber.RIGHT;
                        dirX = 1;
                        dirY = 0;
                        image = Sprite.player_right.getFxImage();
                        break;
                    }
                }
                speed_x += Math.abs(dirX) * accelration;
                speed_y += Math.abs(dirY) * accelration;
            }
        });

        BombermanGame.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                currStt = Bomber.IDLE;
                dirX = 0;
                dirY = 0;
                speed_x = 1;
                speed_y = 1;
            }
        });
    }


    @Override
    public void update() {
        img = image;
        move();

        x = (int) (x + speed_x * dirX);
        y = (int) (y + speed_y * dirY);
        
    }

}
