package oop.entities.character.bomb;

import javafx.scene.image.Image;
import oop.entities.character.Character;

import oop.graphics.Sprite;

import oop.sound.Sound;

public class Bomb extends Character {
    private int timeCount = 0;
    public boolean kill;
    int radius;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(2);
        this.radius = 1;
    }

    public Bomb(int xUnit, int yUnit, Image img, int radius) {
        super(xUnit, yUnit, img);
        setLayer(2);
        this.radius = radius;
    }

    @Override
    public void update() {
        if (timeCount++ == 120) {
            Sound.play("bombExploded");
            explodeUpgrade();
        }
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, timeCount, 60).getFxImage();
    }

    public void explodeUpgrade() {
        Flame f = new Flame(x, y);
        f.setRadius(radius);
        f.render_explosion();
        live = false;
    }

}
