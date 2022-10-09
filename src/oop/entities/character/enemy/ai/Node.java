package oop.entities.character.enemy.ai;

public class Node {
    public int col;
    public int row;
    int gCost;
    int hCost;
    int fCost;
    boolean solid;
    boolean open;
    boolean checked;
    Node parent;

    public Node (int col, int row) {
        this.col = col;
        this.row = row;
    }
}
