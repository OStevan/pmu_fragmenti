package com.example.os130004.fragmenti;

import java.io.Serializable;


class Controller implements Serializable {

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
