package oop.entities.character;

import javafx.event.EventHandler;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import oop.entities.Bomb;
import oop.entities.Entity;
import oop.graphics.CreateMap;
import oop.graphics.Sprite;

import oop.BombermanGame;
import oop.entities.animation.Animation;

import oop.control.Collision;

import static oop.BombermanGame.*;

public class Bomber extends Character {

    public static double getSpeed_y;
    public static double getSpeed_x;
    /**
     * direction
     */
    private double dirX = 0;
    private double dirY = 0;

    /**
     * status (up down left right)
     */
    // public static final int IDLE = 0;
    // public static final int DOWN = 1;
    // public static final int UP = 2;
    // public static final int LEFT = 3;
    // public static final int RIGHT = 4;
    // public static final int DEAD = 5;
    // private int currStt = Bomber.IDLE;


    //public Image image;

    //private Animation[] sttanm;

    /*speed + accel */
    private double speed_x = 1;

    public double getDirX() {
        return dirX;
    }

    public void setDirX(double dirX) {
        this.dirX = dirX;
    }

    public double getDirY() {
        return dirY;
    }

    public void setDirY(double dirY) {
        this.dirY = dirY;
    }

    public void setSpeed_x(double speed_x) {
        this.speed_x = speed_x;
    }

    public void setSpeed_y(double speed_y) {
        this.speed_y = speed_y;
    }

    private double speed_y = 1;

    final private double accelration = 1.0;
    final private double max_speed = 3.0;

    private boolean canMove = false;

    public static Entity bomb;
    private final int bomber_time = 6;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public Bomber(int xUnit, int yUnit, Image img, String direction, /*int step,*/ int stepCount) {
        super(xUnit, yUnit, img, direction, /*step,*/ stepCount);
    }

    public void move() {
        BombermanGame.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                this.handleEvent(keyEvent);
            }

            private void handleEvent(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP: {
                        // currStt = Bomber.UP;
                        direction = "up";
                        //y -= Sprite.SCALED_SIZE/2;
                        dirX = 0;
                        dirY = -1;
                        break;
                    }
                    case DOWN: {
                        // currStt = Bomber.DOWN;
                        direction = "down";
                        //y += Sprite.SCALED_SIZE/2;
                        dirX = 0;
                        dirY = 1;
                        break;
                    }
                    case LEFT: {
                        // currStt = Bomber.LEFT;
                        direction = "left";
                        //x -= Sprite.SCALED_SIZE/2;
                        dirX = -1;
                        dirY = 0;
                        break;
                    }
                    case RIGHT: {
                        // currStt = Bomber.RIGHT;
                        direction = "right";
                        //x += Sprite.SCALED_SIZE/2;
                        dirX = 1;
                        dirY = 0;
                        break;
                    }
                    case SPACE: {
                        Bomb.putBomb();
                        break;
                    }
                    default:
                        direction = "idle";
                        // currStt = Bomber.IDLE;
                        break;

                }

                speed_x += Math.abs(dirX) * accelration;
                if (speed_x > max_speed) {
                    speed_x = max_speed;
                }
                speed_y += Math.abs(dirY) * accelration;
                if (speed_y > max_speed) {
                    speed_y = max_speed;
                }

            }
        });

        BombermanGame.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                // currStt = Bomber.IDLE;
                stepCount = 0;
                loadAnimation();
                direction = "idle";
                dirX = 0;
                dirY = 0;
                speed_x = 1;
                speed_y = 1;
            }
        });
    }

    public void loadAnimation() {
        switch (direction) {
            case "up": {
                /*if (step == 0) {
                    img = Sprite.player_up.getFxImage();
                }
                if (step == 1) {
                    img = Sprite.player_up_1.getFxImage();
                }
                if (step == 2) {
                    img = Sprite.player_up.getFxImage();
                }
                if (step == 3) {
                    img = Sprite.player_up_2.getFxImage();
                }*/
                Sprite bomber_animation = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, stepCount, bomber_time);
                img = bomber_animation.getFxImage();
                break;
            }
            case "down": {
                /*if (step == 0) {
                    img = Sprite.player_down.getFxImage();
                }
                if (step == 1) {
                    img = Sprite.player_down_1.getFxImage();
                } 
                if (step == 2) {
                    img = Sprite.player_down.getFxImage();
                } 
                if (step == 3) {
                    img = Sprite.player_down_2.getFxImage();
                }*/
                Sprite bomber_animation = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, stepCount, bomber_time);
                img = bomber_animation.getFxImage();
                break;
            }
            case "left": {
                /*if (step == 0) {
                    img = Sprite.player_left.getFxImage();
                }
                if (step == 1) {
                    img = Sprite.player_left_1.getFxImage();
                }
                if (step == 2) {
                    img = Sprite.player_left.getFxImage();
                }
                if (step == 3) {
                    img = Sprite.player_left_2.getFxImage();
                }*/
                Sprite bomber_animation = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, stepCount, bomber_time);
                img = bomber_animation.getFxImage();
                break;
            }
            case "right": {
                /*if (step == 0) {
                    img = Sprite.player_right.getFxImage();
                }
                if (step == 1) {
                    img = Sprite.player_right_1.getFxImage();
                }
                if (step == 2) {
                    img = Sprite.player_right.getFxImage();
                }
                if (step == 3) {
                    img = Sprite.player_right_2.getFxImage();
                }*/
                Sprite bomber_animation = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, stepCount, bomber_time);
                img = bomber_animation.getFxImage();
                break;
            }
        }
    }


    @Override
    public void update() {

        move();
        if (canMove) {
            x = (int) (x + speed_x * dirX);
            y = (int) (y + speed_y * dirY);
            canMove = false;
        }

        switch (direction) {
            case "up": {
                if (Collision.colliSionUp(this.x, this.y))
                    canMove = true;
                break;
            }
            case "down": {
                if (Collision.collisionDown(this.x, this.y))
                    canMove = true;
                break;
            }
            case "left": {
                if (Collision.collisionLeft(this.x, this.y))
                    canMove = true;
                break;
            }
            case "right": {
                if (Collision.colliSionRight(this.x, this.y))
                    canMove = true;
                break;
            }
            default:
                break;
        }


        if (!direction.equals("idle")) {
            loadAnimation();
            stepCount++;
            /*if (stepCount == 4) {
                if (step == 3) {
                    step = 0;
                } else {
                    step++;
                }
                stepCount = 0;
            }*/
        } else {
            stepCount = 0;
        }
    }


    public double getSpeed_x() {
        return this.speed_x;
    }

    public double getSpeed_y() {
        return this.speed_y;
    }
}

