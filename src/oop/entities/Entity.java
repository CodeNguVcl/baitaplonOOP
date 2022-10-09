package oop.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.scene.shape.Rectangle;
//import java.awt.*;

import oop.graphics.Sprite;

public abstract class Entity {
    // Tọa độ X tính từ góc trái trên trong Canvas
    // Tọa độ Y tính từ góc trái trên trong Canvas
    protected int x;
    protected int y;

    protected Image img;
    protected int animated = 0;
    protected boolean live;

    protected int layer; // collision

    // Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
        Rectangle r1 = this.getBounds();
        gc.strokeRect(r1.getX(), r1.getY(), r1.getWidth(), r1.getHeight());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean alive) {
        this.live = alive;
    }

    // tao hit box cho doi tuong, su dung de tham gia cac va cham
    public Rectangle getBounds() {
        return new Rectangle(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    public abstract void update();
}
