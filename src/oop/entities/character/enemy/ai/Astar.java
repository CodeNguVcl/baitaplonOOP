package oop.entities.character.enemy.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static oop.BombermanGame.HEIGHT;
import static oop.BombermanGame.WIDTH;
import static oop.graphics.CreateMap.idMap;


public class Astar {
    Node[][] nodes;
    Node start, goal, current;
    List<Node> openList = new ArrayList<>();
    public List<Node> path = new ArrayList<>();
    boolean goalReached = false;
    int step = 0;

    public Astar() {
        createNodes();
    }

    public void createNodes() {
        nodes = new Node[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                nodes[i][j] = new Node(j, i);
            }
        }
    }

    public void resetNodes() {
        int row = 0;
        int col = 0;
        goalReached = false;
        step = 0;
        openList.clear();
        path.clear();

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                nodes[i][j].solid = false;
                nodes[i][j].open = false;
                nodes[i][j].checked = false;
            }
        }
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();
        start = nodes[startRow][startCol];
        goal = nodes[goalRow][goalCol];
        current = start;

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
               if (Objects.equals(idMap[i][j], "#") || Objects.equals(idMap[i][j], "*")) {
                   nodes[i][j].solid = true;
               }
               getCost(nodes[i][j]);
            }
        }
    }

    public void getCost (Node node) {
        int xDis = Math.abs(node.col - start.col);
        int yDis = Math.abs(node.row - start.row);
        node.gCost = xDis + yDis;

        xDis = Math.abs(node.col - goal.col);
        yDis = Math.abs(node.row - goal.row);
        node.hCost = xDis + yDis;

        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        while (!goalReached && step < 1000) {
            int col = current.col;
            int row = current.row;

            current.checked = true;
            openList.remove(current);

            if (row - 1 >= 0) {
                openNode(nodes[row - 1][col]);
            }
            if (row + 1 < HEIGHT) {
                openNode(nodes[row + 1][col]);
            }
            if (col - 1 >= 0) {
                openNode(nodes[row][col - 1]);
            }
            if (col + 1 < WIDTH) {
                openNode(nodes[row][col + 1]);
            }

            int bestIndex = 0;
            int bestfCost = 1000;

            for (int i = 0; i < openList.size(); i++) {
                if (openList.get(i).fCost < bestfCost) {
                    bestIndex = i;
                    bestfCost = openList.get(i).fCost;
                } else if (openList.get(i).fCost == bestfCost) {
                    if (openList.get(i).gCost < openList.get(bestIndex).gCost) {
                        bestIndex = i;
                    }
                }
            }
            if (openList.size() == 0) {
                break;
            }
            current = openList.get(bestIndex);
            if (current == goal) {
                goalReached = true;
                trackPath();
            }
            step++;
        }
        return goalReached;
    }

    public void trackPath() {
        Node cur = goal;
        while(cur != start) {
            path.add(0, cur);
            cur = cur.parent;
        }
    }

    public void openNode(Node node) {
        if (!node.open && !node.checked && !node.solid) {
            node.open = true;
            node.parent = current;
            openList.add(node);
        }
    }
}
