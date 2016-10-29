package com.example.os130004.fragmenti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_MODEL = "com.example.os130004.fragmenti.Model";

    private TextView[][] matrix;
    private Model model;
    private static final int M = 10;
    private static final int N = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matrix = new TextView[N][M];

        if(savedInstanceState != null) {
            model = (Model) savedInstanceState.get(KEY_MODEL);
        } else {
            model = new Model(N, M, 10);
        }

        LinearLayout topLevelLayout = (LinearLayout) findViewById(R.id.top_level_layout);

        for (int i = 0; i < N; i++) {

            LinearLayout row = new LinearLayout(this);
            ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            row.setOrientation(LinearLayout.HORIZONTAL);


            for (int j = 0; j < M; j++) {
                matrix[i][j] = new TextView(this);
                matrix[i][j].setText(model.getValue(i, j) ? "*" : "");
                ViewGroup.LayoutParams columnParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                row.addView(matrix[i][j], columnParams);
            }

            topLevelLayout.addView(row, params);
        }
    }

    public void up(View view) {
        if (model.up()) {
            refreshView();
        }
    }

    public void down(View view) {
        if  (model.down()) {
            refreshView();
        }
    }

    public void left(View view) {
        if (model.left()) {
            refreshView();
        }
    }

    public void right(View view) {

        if (model.right()) {
            refreshView();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_MODEL, model);
    }

    private void refreshView() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(model.getValue(i, j)) {
                    matrix[i][j].setText("*");
                } else {
                    matrix[i][j].setText("");
                }
            }
        }
        matrix[model.getVerticalPosition()][model.getHorizontalPosition()].setText("+");
    }
}