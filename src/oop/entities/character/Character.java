package oop.entities.character;

import javafx.scene.image.Image;
import oop.entities.Entity;

public abstract class Character extends Entity {
    protected String direction = "right";
    protected int step = 0;
    protected int stepCount = 0;

    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Character(int xUnit, int yUnit, Image img, String direction, int step, int stepCount) {
        super(xUnit, yUnit, img);
        this.direction = direction;
        this.step = step;
        this.stepCount = stepCount;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }
}
