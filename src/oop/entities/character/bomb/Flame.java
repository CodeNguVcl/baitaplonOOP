package oop.entities.character.bomb;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import oop.BombermanGame;
import oop.entities.Entity;
import oop.entities.mapblock.Brick;
import oop.entities.mapblock.Wall;
import oop.graphics.Sprite;

public class Flame extends Entity {
    private int up;
    private int down;
    private int left;
    private int right;
    private int radius;// ban kinh flame
    private final int size = Sprite.SCALED_SIZE - 6;
    private int dir;
    private int time = 0;// thoi gian flame ton tai

    public Flame(int x, int y, Image img, int dir) {
        super(x, y);
        this.img = img;
        this.dir = dir;
    }

    public Flame(int x, int y, Image img) {
        super(x, y);
        this.img = img;
        this.radius = 2;
    }

    public Flame(int x, int y) {
        super(x, y);
    }

    public void setRadius(int r) {
        this.radius = r;
    }

    @Override
    public void update() {// phuong thuc ket thuc vu no
        if (time < 20) {
            time++;
            setImg();
        } else {
            BombermanGame.flameList.remove(this);
        }
    }

    public void render_explosion() {
        explosion_Up();
        explosion_Down();
        explosion_Left();
        explosion_Right();
        create_explosion();

    }

    // phuong thuc tao vu no(chi ve mat hinh anh)
    private void create_explosion() {
        BombermanGame.flameList.add(new Flame(x, y, Sprite.bomb_exploded.getFxImage(), 0));

        for (int i = 0; i < up; i++) {
            Flame flm = new Flame(x, y - size * (i + 1));
            if (i == up - 1) {
                flm.img = Sprite.explosion_vertical_top_last.getFxImage();
                flm.dir = 5;
            } else {
                flm.img = Sprite.explosion_vertical.getFxImage();
                flm.dir = 4;
            }
            BombermanGame.flameList.add(flm);
        }

        for (int i = 0; i < down; i++) {
            Flame flm = new Flame(x, y + size * (i + 1));
            if (i == down - 1) {
                flm.img = Sprite.explosion_vertical_down_last.getFxImage();
                flm.dir = 6;
            } else {
                flm.img = Sprite.explosion_vertical.getFxImage();
                flm.dir = 4;
            }
            BombermanGame.flameList.add(flm);
        }

        for (int i = 0; i < left; i++) {
            Flame flm = new Flame(x - size * (i + 1), y);
            if (i == left - 1) {
                flm.img = Sprite.explosion_horizontal_left_last.getFxImage();
                flm.dir = 3;
            } else {
                flm.img = Sprite.explosion_horizontal.getFxImage();
                flm.dir = 1;
            }
            BombermanGame.flameList.add(flm);
        }

        for (int i = 0; i < right; i++) {
            Flame flm = new Flame(x + size * (i + 1), y);
            if (i == right - 1) {
                flm.img = Sprite.explosion_horizontal_right_last.getFxImage();
                flm.dir = 2;
            } else {
                flm.img = Sprite.explosion_horizontal.getFxImage();
                flm.dir = 1;
            }
            BombermanGame.flameList.add(flm);
        }
    }

    private void explosion_Up() {
        for (int i = 0; i < radius; i++) {
            Rectangle ex_up = new Rectangle(x + 4, y - size * (i + 1), size, size);
            if (collisionType(ex_up) instanceof Wall) {
                up = i;
                return;
            } else if (collisionType(ex_up) instanceof Brick) {
                up = i + 1;
                return;
            }
            up = i + 1;
        }
    }

    private void explosion_Down() {
        for (int i = 0; i < radius; i++) {
            Rectangle ex_down = new Rectangle(x + 4, y + size * (i + 1), size, size);
            if (collisionType(ex_down) instanceof Wall) {
                down = i;
                return;
            } else if (collisionType(ex_down) instanceof Brick) {
                down = i + 1;
                return;
            }
            down = i + 1;
        }
    }

    private void explosion_Left() {
        for (int i = 0; i < radius; i++) {
            Rectangle ex_left = new Rectangle(x - size * (i + 1), y + 4, size, size);
            if (collisionType(ex_left) instanceof Wall) {
                left = i;
                return;
            } else if (collisionType(ex_left) instanceof Brick) {
                left = i + 1;
                return;
            }
            left = i + 1;
        }
    }

    private void explosion_Right() {
        for (int i = 0; i < radius; i++) {
            Rectangle ex_right = new Rectangle(x + size * (i + 1), y + 4, size, size);
            if (collisionType(ex_right) instanceof Wall) {
                right = i;
                return;
            } else if (collisionType(ex_right) instanceof Brick) {
                right = i + 1;
                return;
            }
            right = i + 1;
        }
    }

    private Object collisionType(Rectangle rtg) {
        for (Entity e : BombermanGame.stillObjects) {
            Rectangle rtg2 = e.getBounds();
            if (rtg.intersects(rtg2.getLayoutBounds())) {
                return e;
            }
        }
        return rtg;
    }

    public void setImg() {
        switch (dir) {
            case 0:
                img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, time, 20)
                        .getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1,
                        Sprite.explosion_horizontal2, time, 20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last,
                        Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, time, 20)
                        .getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1,
                        Sprite.explosion_horizontal_left_last2, time, 20).getFxImage();
                break;
            case 4:
                img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1,
                        Sprite.explosion_vertical2, time, 20).getFxImage();
                break;
            case 5:
                img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1,
                        Sprite.explosion_vertical_top_last2, time, 20).getFxImage();
                break;
            case 6:
                img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1,
                        Sprite.explosion_vertical_down_last2, time, 20).getFxImage();
                break;
        }
    }
}
