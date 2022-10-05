package oop;

import java.util.Comparator;

import oop.entities.Entity;

public class Layer implements Comparator<Entity> {
  @Override
  public int compare(Entity e1, Entity e2) {
    return Integer.compare(e2.getLayer(), e1.getLayer());
  }
}
