package oop.entities.character.enemy;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import oop.BombermanGame;
import oop.entities.character.bomb.Bomb;
import oop.entities.character.enemy.ai.Astar;
import oop.graphics.Sprite;

import java.util.Random;

import static oop.BombermanGame.bomberman;
import static oop.entities.character.Bomber.bombs;

public class Minvo extends Enemy {
    public Astar pathFinder = new Astar();

    public Minvo(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
        setSpeed(2);
        generateDirection();
        live = true;
        point = 400;
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
        if (bombs.size() > 0) {
            Rectangle b = bombs.get(0).getBounds();
            int bombCol = (int) b.getX() / Sprite.SCALED_SIZE;
            int bombRow = (int) b.getY() / Sprite.SCALED_SIZE;
            findPath(bombCol, bombRow);
            reverseDirection();

        }
        else {
            Rectangle r1 = bomberman.getBounds();
            int goalRow = (int) r1.getY() / Sprite.SCALED_SIZE;
            int goalCol = (int) r1.getX() / Sprite.SCALED_SIZE;
            findPath(goalCol, goalRow);
            if (!bomberman.isLive()) {
                restartEnemy();
            }
        }
    }

    @Override
    public void update() {
        if (isLive()) {
            generateDirection();
            if (direction % 4 == 0 ) {
                turnUp();
            }
            if (direction % 4 == 1) {
                turnDown();
            }
            if (direction % 4 == 2) {
                turnLeft();
            }
            if (direction % 4 == 3) {
                turnRight();
            }
        } else if (animated < 30) {
            animated++;
            img = Sprite.minvo_dead.getFxImage();
        } else {
            BombermanGame.enemy.remove(this);
        }

    }

    public void turnUp() {
        super.turnUp();
        img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animation++, 18)
                .getFxImage();
    }

    public void turnDown() {
        super.turnDown();
        img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animation++, 18)
                .getFxImage();
    }

    public void turnLeft() {
        super.turnLeft();
        img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animation++, 18)
                .getFxImage();
    }

    public void turnRight() {
        super.turnRight();
        img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animation++, 18)
                .getFxImage();
    }

    @Override
    public void stop() {
        super.stop();
        generateRandomDirection();
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

    public void generateRandomDirection() {
        Random r = new Random();
        direction = r.nextInt(4);
    }
    public void reverseDirection() {
        if (direction == 0)
            direction = 5;
        else if (direction == 1)
            direction = 4;
        else if (direction == 2)
            direction = 7;
        else if (direction == 3)
            direction = 6;
    }
}
