package oop;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
//import java.awt.*;

import javafx.stage.Stage;
import oop.entities.character.Bomber;
import oop.entities.character.bomb.Bomb;
import oop.entities.character.bomb.Flame;
import oop.entities.character.enemy.Enemy;
import oop.entities.Entity;
import oop.entities.item.BombItem;
import oop.entities.item.FlameItem;
import oop.entities.item.Item;
import oop.entities.item.SpeedItem;
import oop.entities.mapblock.Portal;
import oop.graphics.CreateMap;
// import oop.entities.Grass;
// import oop.entities.Wall;
import oop.graphics.Sprite;
// import oop.graphics.CreateMap;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static int WIDTH = 31;
    public static int HEIGHT = 13;
    public static int HEIGHT_MENU = 1;

    public static int w = 15;
    public static int h = 13;

    private GraphicsContext gc;
    private Canvas canvas;
    // private final List<Entity> entities = new ArrayList<>();
    public static final List<Enemy> enemy = new ArrayList<>();// list enemy
    public static final List<Entity> stillObjects = new ArrayList<>();// thuc the trong game
    public static final List<Flame> flameList = new ArrayList<>();
    public static String[][] IdMap;
    public static Scene scene;

    public static Bomber bomberman;

    public CreateMap map;

    public static Stage gameStage;

    public int level = 1;

    public static int time_init;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {

        map = new CreateMap(level);

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.setClip(new Rectangle(Sprite.SCALED_SIZE * w,
                Sprite.SCALED_SIZE * (h + HEIGHT_MENU)));
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
        updateCanvas();
        handleCollisions();
        checkExplode();
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

    // camera
    public void updateCanvas() {
        if (-1 * bomberman.getX() + Sprite.SCALED_SIZE * (WIDTH / 4) <= 0 && -1 * bomberman.getX()
                + Sprite.SCALED_SIZE * (WIDTH / 4) >= -1 * Sprite.SCALED_SIZE * (WIDTH / 2 + 1)) {
            canvas.setLayoutX(-bomberman.getX() + Sprite.SCALED_SIZE * (WIDTH / 4));
        }
    }

    public void handleCollisions() {

        /**
         * check va cham bomber voi gach, tuong
         */
        Rectangle r1 = bomberman.getBounds();

        for (Entity stillObject : stillObjects) {
            Rectangle r2 = stillObject.getBounds();
            if (r1.intersects(r2.getLayoutBounds())) {
                if (stillObject instanceof Item) {
                    if (stillObject instanceof BombItem) {
                        bomberman.setBombRemain(bomberman.getBombRemain() + 1);
                        stillObjects.remove(stillObject);
                    } else if (stillObject instanceof SpeedItem) {
                        // bomberman.setSpeed(bomberman.getSpeed() + 4);
                        bomberman.setAccelration(bomberman.getAccelration() + 1);
                        stillObjects.remove(stillObject);
                    } else if (stillObject instanceof FlameItem) {
                        bomberman.setFlameRadius(bomberman.getFlameRadius() + 1);
                        stillObjects.remove(stillObject);
                    }
                }
                if (stillObject instanceof Portal) {
                    if (enemy.size() == 0) {
                        ++level;
                    }
                }
                if (bomberman.getLayer() >= stillObject.getLayer()) {
                    bomberman.move();
                } else {
                    bomberman.stop();
                }
                break;
            }
        }

        /**
         * check va cham enemy voi tuong, gach.
         */
        for (Enemy enm : enemy) {
            Rectangle r2 = enm.getBounds();
            for (Entity stillObject : stillObjects) {
                Rectangle r3 = stillObject.getBounds();
                if (r2.intersects(r3.getLayoutBounds())) {
                    if (enm.getLayer() >= stillObject.getLayer()) {
                        enm.move();
                    } else {
                        enm.stop();
                    }
                    break;
                }
            }
            for (Bomb bomb : bomberman.getBombs()) {
                Rectangle r3 = bomb.getBounds();
                if (r2.intersects(r3.getLayoutBounds())) {
                    if (enm.getLayer() >= bomb.getLayer()) {
                        enm.move();
                    } else {
                        enm.stop();
                    }
                    break;
                }
            }
        }

        /**
         * Check va cham giua bomberman va enemy
         */
        for (Enemy enm : enemy) {
            Rectangle r2 = enm.getBounds();
            if (r1.intersects(r2.getLayoutBounds())) {
                bomberman.setLive(false);
            }
        }
    }

    /**
     * bomb no vao tuong.
     */
    public void checkExplode() {
        for (Flame f : flameList) {
            Rectangle r0 = f.getBounds();
            Rectangle r1 = new Rectangle(r0.getX() + 2, r0.getY() + 2, r0.getWidth() - 4, r0.getHeight() - 4);
            for (Entity stillObject : stillObjects) {
                Rectangle r2 = stillObject.getBounds();
                if (r1.intersects(r2.getLayoutBounds())) {
                    stillObject.setLive(false);
                }
            }
            Rectangle r3 = bomberman.getBounds();
            if (r1.intersects(r3.getLayoutBounds())) {
                bomberman.setLive(false);
            }
            for (Entity enemy : enemy) {
                Rectangle r2 = enemy.getBounds();
                if (r1.intersects(r2.getLayoutBounds())) {
                    enemy.setLive(false);
                }
            }
        }
    }

    public static Button createButton(String src, int x, int y, String bgColor) {
        InputStream inputStream = BombermanGame.class.getResourceAsStream(src);
        Image img = new Image(inputStream);
        ImageView imageView = new ImageView(img);
        Button button = new Button("", imageView);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setStyle("-fx-background-color: #" + bgColor + "; ");

        return button;
    }
}
