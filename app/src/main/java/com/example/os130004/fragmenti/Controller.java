package com.example.os130004.fragmenti;

import java.io.Serializable;


class Controller implements Serializable {

    void rotateRight() {
        model.rotateRight();
        refreshable.refresh();
    }

    void rotateLeft() {
        model.rotateLeft();
        refreshable.refresh();
    }

    void move() {
        if (model.move()) {
            refreshable.refresh();
        }
    }

    String direction() {
        return model.direction();
    }

    boolean fragmentChoice() {
        return model.fragmentChoice();
    }

    void switchFragment() {
        model.switchFragment();
        refreshable.refresh();
    }

    void fragmentChanged() {
        model.fragmentChanged();
    }

    boolean changeFragment() {
        return model.changeFragment();
    }

    interface Refreshable {
        void refresh();
    }

    private transient Refreshable refreshable;
    private Model model;

    Controller(Refreshable refreshable) {
        this.refreshable = refreshable;
        model = new Model(10, 10, 10);
    }

    void setRefreshable(Refreshable refreshable) {
        this.refreshable = refreshable;
    }

    boolean valueAtPosition(int i, int j) {
        return model.valueAtPosition(i, j);
    }

    void up() {
        if (model.up())
            refreshable.refresh();
    }

    void down() {
        if (model.down())
            refreshable.refresh();
    }

    void left() {
        if (model.left())
            refreshable.refresh();
    }

    void right() {
        if (model.right())
            refreshable.refresh();
    }

    int verticalPosition() {
        return model.verticalPosition();
    }

    int horizontalPosition() {
        return model.horizontalPosition();
    }

    boolean finished() {
        return model.finished();
    }
}
