package com.example.os130004.fragmenti;

import java.io.Serializable;


class Model implements Serializable {
    private int rows;
    private int columns;
    private boolean content[][];
    private int verticalPosition;
    private int horizontalPosition;
    private int count;
    private Direction direction;
    private boolean fragmentChoice;
    private boolean changeFragment;

    String direction() {
        switch (direction) {
            case UP:
                return "UP";
            case RIGHT:
                return "RIGHT";
            case DOWN:
                return "DOWN";
            case LEFT:
                return "LEFT";
        }
        return "";
    }

    boolean fragmentChoice() {
        return fragmentChoice;
    }

    void switchFragment() {
        changeFragment = true;
        fragmentChoice = !fragmentChoice;
    }

    void fragmentChanged() {
        changeFragment = false;
    }

    boolean changeFragment() {
        return changeFragment;
    }

    private enum Direction {UP, DOWN, LEFT, RIGHT}

    Model(int rows, int columns, int count) {
        content = new boolean[rows][columns];
        this.rows = rows;
        this.columns = columns;
        this.count = count;

        this.verticalPosition = (rows + 1) / 2 - 1;
        this.horizontalPosition = (columns + 1) / 2 - 1;

        for (; count > 0; ) {
            int y = (int) (Math.random() * rows);
            int x = (int) (Math.random() * columns);

            if (!content[y][x] && verticalPosition != y && horizontalPosition != x) {
                content[y][x] = true;
                count--;
            }
        }
        content[this.verticalPosition][this.horizontalPosition] = false;
        direction = Direction.UP;
        fragmentChoice = false;
        changeFragment = true;
    }

    boolean up() {
        verticalPosition--;
        if (verticalPosition < 0) {
            verticalPosition = 0;
            return false;
        }
        collectField();
        return true;
    }

    boolean down() {
        verticalPosition++;
        if (verticalPosition >= rows) {
            verticalPosition = rows - 1;
            return false;
        }
        collectField();
        return true;
    }

    boolean left() {
        horizontalPosition--;
        if (horizontalPosition < 0) {
            horizontalPosition = 0;
            return false;
        }
        collectField();
        return true;
    }

    boolean right() {
        horizontalPosition++;
        if (horizontalPosition >= columns) {
            horizontalPosition = columns - 1;
            return false;
        }
        collectField();
        return true;
    }

    private void collectField() {
        if (content[verticalPosition][horizontalPosition]) {
            content[verticalPosition][horizontalPosition] = false;
            count--;
        }
    }

    boolean valueAtPosition(int i, int j) {
        return content[i][j];
    }

    int horizontalPosition() {
        return horizontalPosition;
    }

    int verticalPosition() {
        return verticalPosition;
    }

    boolean finished() {
        return count == 0;
    }

    void rotateRight() {
        switch (direction) {
            case UP:
                direction = Direction.RIGHT;
                break;
            case RIGHT:
                direction = Direction.DOWN;
                break;
            case DOWN:
                direction = Direction.LEFT;
                break;
            case LEFT:
                direction = Direction.UP;
            break;
        }
    }

    void rotateLeft() {
        switch (direction) {
            case UP:
                direction = Direction.LEFT;
                break;
            case RIGHT:
                direction = Direction.UP;
                break;
            case DOWN:
                direction = Direction.RIGHT;
                break;
            case LEFT:
                direction = Direction.DOWN;
                break;
        }
    }

    boolean move() {
        switch (direction) {
            case UP:
                return up();
            case RIGHT:
                return right();
            case DOWN:
                return down();
            case LEFT:
                return left();
        }
        return false;
    }
}
