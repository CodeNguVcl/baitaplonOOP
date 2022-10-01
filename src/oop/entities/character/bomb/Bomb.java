package oop.entities.character.bomb;

import javafx.scene.image.Image;
import javafx.scene.paint.RadialGradient;
import oop.entities.Entity;
import oop.entities.character.Bomber;
import oop.entities.character.Character;
import oop.graphics.Sprite;

import static oop.BombermanGame.bomberman;
import static oop.BombermanGame.entities;
import static oop.entities.character.Bomber.bomb;
import static oop.entities.character.Bomber.bombs;


public class Bomb extends Entity {
    private int timeCount = 0;
    int radius;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Bomb(int xUnit, int yUnit, Image img, int radius) {
        super(xUnit, yUnit, img);
        setLayer(2);
        this.radius = radius;
    }
    @Override
    public void update() {
        if (timeCount ++ == 120) {
            explodeUpgrade();
            bombs.remove(this);
        }
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, timeCount, 60).getFxImage();
    }

    public void explodeUpgrade() {
        Flame f = new Flame(x, y);
        f.setRadius(radius);
        f.render_explosion();
    }

}

