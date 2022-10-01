package oop;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import oop.entities.character.Bomber;
import oop.entities.character.bomb.Bomb;
import oop.entities.character.bomb.Flame;
import oop.entities.character.enemy.Balloom;
import oop.entities.Entity;

// import oop.entities.Grass;
// import oop.entities.Wall;
import oop.graphics.Sprite;
import oop.graphics.CreateMap;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public int tranX = 0;
    public int tranY = 0;
    
    private GraphicsContext gc;
    private Canvas canvas;
    //private final List<Entity> entities = new ArrayList<>();
    public static final List<Entity> entities = new ArrayList<>();//list enemy
    public static final List<Entity> stillObjects = new ArrayList<>();//thuc the trong game
    public static final List<Flame> flameList = new ArrayList<>();
    public static String[][] IdMap;
    public static Scene scene;

    public static Bomber bomberman;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
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

        new CreateMap("res/levels/lv1.txt");
    }


    public void update() {
        entities.forEach(Entity::update);
        List<Bomb> bombs = bomberman.getBombs();
        for (int i = 0; i < flameList.size(); i++) {
            flameList.get(i).update();
        }
        bomberman.update();
        for (Bomb bomb : bombs) {
            bomb.update(); //updata cac event bomb
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));

        List<Bomb> bombs = bomberman.getBombs();
        for (Bomb bomb : bombs) {
            bomb.render(gc);
        }

        bomberman.render(gc);

        entities.forEach(g -> g.render(gc));
        flameList.forEach(g -> g.render(gc));
    }
}
