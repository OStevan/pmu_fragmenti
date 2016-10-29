package com.example.os130004.fragmenti;

/**
 * Created by os130004 on 10/26/2016.
 */

class Model {
    private final int rows;
    private final int columns;
    private boolean content[][];
    private int verticalPosition;
    private int horizontalPosition;

    Model(int rows, int columns, int count) {
        content = new boolean[rows][columns];
        this.rows = rows;
        this.columns = columns;


        for (; count > 0;) {
            int y = (int) (Math.random() * rows);
            int x = (int) (Math.random() * columns);

            if (!content[y][x]) {
                content[y][x] = true;
                count--;
            }
        }
        this.verticalPosition = rows / 2;
        this.horizontalPosition = columns / 2;
        content[this.verticalPosition][this.horizontalPosition] = false;
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
        }
    }

    boolean getValue(int i, int j) {
        return content[i][j];
    }

    int getHorizontalPosition() {
        return horizontalPosition;
    }

    public int getVerticalPosition() {
        return verticalPosition;
    }
}
