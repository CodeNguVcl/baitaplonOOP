package oop.entities.character.enemy;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import oop.BombermanGame;
import oop.entities.character.enemy.ai.Astar;
import oop.graphics.Sprite;

import java.util.Random;

import static oop.BombermanGame.bomberman;

public class Oneal extends Enemy {
    private int lastDirection;
    public Astar pathFinder = new Astar();

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
        setSpeed(Sprite.SCALE / 2);
        generateDirection();
        live = true;
        point = 300;
    }

    @Override
    public void restartEnemy() {
        x = startX * Sprite.SCALED_SIZE;
        posX = x;
        y = startY * Sprite.SCALED_SIZE;
        posY = y;
    }

    @Override
    public void generateDirection() {
        Rectangle r1 = bomberman.getBounds();
        int goalCol = (int) r1.getX() / Sprite.SCALED_SIZE;
        int goalRow = (int) r1.getY() / Sprite.SCALED_SIZE;
        findPath(goalCol, goalRow);
        if (!bomberman.isLive()) {
            restartEnemy();
        }
    }

    @Override
    public void update() {
        if (isLive()) {
            generateDirection();
            if (direction != lastDirection) {
                generateSpeed();
            }
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
            lastDirection = direction;
        } else if (animated < 30) {
            animated++;
            img = Sprite.oneal_dead.getFxImage();
            img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animated, 20)
                    .getFxImage();
        } else {
            BombermanGame.enemy.remove(this);
        }

    }

    public void turnUp() {
        super.turnUp();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animation++, 18)
                .getFxImage();
    }

    public void turnDown() {
        super.turnDown();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animation++, 18)
                .getFxImage();
    }

    public void turnLeft() {
        super.turnLeft();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animation++, 18)
                .getFxImage();
    }

    public void turnRight() {
        super.turnRight();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animation++, 18)
                .getFxImage();
    }

    @Override
    public void stop() {
        super.stop();
        generateDirection();
        generateSpeed();
    }

    public void findPath(int goalCol, int goalRow) {
        Rectangle r2 = this.getBounds();
        int startCol = (int) r2.getX() / Sprite.SCALED_SIZE;
        int startRow = (int) r2.getY() / Sprite.SCALED_SIZE;
        pathFinder.setNodes(startCol, startRow, goalCol, goalRow);
        if (pathFinder.search()) {
            int pathX = pathFinder.path.get(0).col * Sprite.SCALED_SIZE;
            int pathY = pathFinder.path.get(0).row * Sprite.SCALED_SIZE;

            if (r2.getX() > pathX && r2.getX() + r2.getWidth() < pathX + Sprite.SCALED_SIZE) {
                if (r2.getY() > pathY) {
                    direction = 0;
                } else {
                    direction = 1;
                }
            } else if (r2.getY() > pathY && r2.getY() + r2.getHeight() < pathY + Sprite.SCALED_SIZE) {
                if (r2.getX() > pathX) {
                    direction = 2;
                } else {
                    direction = 3;
                }
            }
        }
    }

    public void generateSpeed() {
        Random r = new Random();
        setSpeed(1 + r.nextInt(4));
    }
}
