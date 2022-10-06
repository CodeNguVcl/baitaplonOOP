package oop;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

// import javafx.scene.shape.Rectangle;
import java.awt.*;

import javafx.stage.Stage;
import oop.entities.character.Bomber;
import oop.entities.character.bomb.Bomb;
import oop.entities.character.bomb.Flame;
import oop.entities.character.enemy.Enemy;
import oop.entities.Entity;
import oop.graphics.CreateMap;
// import oop.entities.Grass;
// import oop.entities.Wall;
import oop.graphics.Sprite;
// import oop.graphics.CreateMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static int WIDTH = 31;
    public static int HEIGHT = 13;

    public int tranX = 0;
    public int tranY = 0;

    private GraphicsContext gc;
    private Canvas canvas;
    // private final List<Entity> entities = new ArrayList<>();
    public static final List<Enemy> enemy = new ArrayList<>();// list enemy
    public static final List<Entity> stillObjects = new ArrayList<>();// thuc the trong game
    public static final List<Flame> flameList = new ArrayList<>();
    public static String[][] IdMap;
    public static Scene scene;

    public static Bomber bomberman;

    public static int level = 1;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        new CreateMap(1);

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);

        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
        scene.setOnKeyPressed(e -> {
            bomberman.handleKeyPressed(e.getCode());
        });
        scene.setOnKeyReleased(e -> bomberman.handleKeyReleased(e.getCode()));
    }

    public void update() {

        for (int i = 0; i < enemy.size(); i++) {
            enemy.get(i).update();
        }

        for (int i = 0; i < flameList.size(); i++) {
            flameList.get(i).update();
        }
        bomberman.update();

        List<Bomb> bombs = bomberman.getBombs();
        for (Bomb bomb : bombs) {
            bomb.update(); // updata cac event bomb
        }
        for (int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).update();
        }
        handleCollisions();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = stillObjects.size() - 1; i >= 0; i--) {
            stillObjects.get(i).render(gc);
        }

        enemy.forEach(g -> g.render(gc));

        List<Bomb> bombs = bomberman.getBombs();
        for (Bomb bomb : bombs) {
            bomb.render(gc);
        }

        bomberman.render(gc);

        // entities.forEach(g -> g.render(gc));
        flameList.forEach(g -> g.render(gc));
    }

    public void handleCollisions() {
        Rectangle r1 = bomberman.getBounds();

        for (Entity stillObject : stillObjects) {
            Rectangle r2 = stillObject.getBounds();
            if (r1.intersects(r2)) {
                if (bomberman.getLayer() >= stillObject.getLayer()) {
                    bomberman.move();
                } else {
                    bomberman.stop();
                }
                break;
            }

        }

        for (Enemy enm : enemy) {
            Rectangle r2 = enm.getBounds();
            for (Entity stillObject : stillObjects) {
                Rectangle r3 = stillObject.getBounds();
                if (r2.intersects(r3)) {
                    if (enm.getLayer() >= stillObject.getLayer()) {
                        enm.move();
                    } else {
                        enm.stop();
                    }
                    break;
                }
            }
        }
    }
}
