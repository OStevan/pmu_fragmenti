package com.example.os130004.fragmenti;

/**
 * Created by os130004 on 10/26/2016.
 */

public class Model {
    private final int n;
    private final int m;
    private boolean content[][];
    private int y;
    private int x;

    public Model(int n, int m, int count) {
        content = new boolean[n][m];
        this.n = n;
        this.m = m;


        for (; count > 0;) {
            int y = (int) (Math.random() * n);
            int x = (int) (Math.random() * m);

            if (!content[y][x]) {
                content[y][x] = true;
                count--;
            }
        }
        this.y = n / 2;
        this.x = m / 2;
        content[this.y][this.x] = false;
    }

    public boolean up() {
        y--;
        if ( y < 0) {
            y = 0;
            return false;
        }
        checknewField();
        return true;
    }

    public boolean down() {
        y++;
        if (y > n - 1) {
            y = n - 1;
            return false;
        }
        checknewField();
        return true;
    }

    public boolean left() {
        x--;
        if (x < 0) {
            x = 0;
            return false;
        }
        checknewField();
        return true;
    }

    public boolean right() {
        x++;
        if(x > m - 1) {
            x = m - 1;
            return false;
        }
        checknewField();
        return true;
    }

    public boolean checknewField() {
        if (content[y][x]) {
            content[y][x] = false;
            return true;
        }
        return false;
    }

    public boolean getValue(int i, int j) {
        return content[i][j];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
