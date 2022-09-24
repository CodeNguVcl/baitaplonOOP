package oop.entities;

import javafx.event.EventHandler;
import java.text.BreakIterator;

import javax.swing.SpringLayout;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import oop.BombermanGame;
import oop.graphics.Sprite;

public class Bomber extends Entity {



    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    public void move() {
        BombermanGame.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override 
            public void handle(KeyEvent keyEvent) {
                this.handleEvent(keyEvent);
            }

            private void handleEvent(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case UP: {
                        // currentStatus=Bomber.UP;
                        // dir_x = 0;
                        // dir_y = -1;
                        y -= Sprite.SCALED_SIZE;
                        break;
                    }
                    case DOWN: {
                        // currentStatus=Bomber.DOWN;
                        // dir_x = 0;
                        // dir_y = 1;
                        y += Sprite.SCALED_SIZE / 2;
                        break;
                    }
                    case LEFT: {
                        // currentStatus=Bomber.LEFT;
                        // dir_x = -1;
                        // dir_y = 0;
                        x -= Sprite.SCALED_SIZE / 2;
                        break;
                    }
                    case RIGHT: {
                        // currentStatus=Bomber.RIGHT;
                        // dir_x = 1;
                        // dir_y = 0;
                        x += Sprite.SCALED_SIZE / 2;
                        break;
                    }
                }
                // speed_x += Math.abs(dir_x) * acc;
                // speed_y += Math.abs(dir_y) * acc;
            }
        });
    }


    public void checkCollision() {
        
    }

    @Override
    public void update() {
        move();
    }
}
