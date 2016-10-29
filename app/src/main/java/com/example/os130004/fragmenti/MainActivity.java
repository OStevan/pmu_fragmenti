package com.example.os130004.fragmenti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Controller.Refreshable{
    private static final String KEY_CONTROLLER = "com.example.os130004.fragmenti.Controller";

    private TextView[][] matrix;
    private Controller controller;
    private static final int M = 10;
    private static final int N = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matrix = new TextView[N][M];

        if(savedInstanceState != null) {
            controller = (Controller) savedInstanceState.get(KEY_CONTROLLER);
            assert controller != null;
            controller.setRefreshable(this);
        } else {
            controller = new Controller(this);
        }

        LinearLayout topLevelLayout = (LinearLayout) findViewById(R.id.top_level_layout);

        for (int i = 0; i < N; i++) {

            LinearLayout row = new LinearLayout(this);
            ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            row.setOrientation(LinearLayout.HORIZONTAL);


            for (int j = 0; j < M; j++) {
                matrix[i][j] = new TextView(this);
                matrix[i][j].setText(controller.valueAtPosition(i, j) ? "*" : "");
                ViewGroup.LayoutParams columnParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                row.addView(matrix[i][j], columnParams);
            }

            topLevelLayout.addView(row, params);
        }
    }

    public void up(View view) {
        controller.up();
    }

    public void down(View view) {
        controller.down();
    }

    public void left(View view) {
        controller.left();
    }

    public void right(View view) {
        controller.right();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_CONTROLLER, controller);
    }

    public void refresh() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(controller.valueAtPosition(i, j)) {
                    matrix[i][j].setText("*");
                } else {
                    matrix[i][j].setText("");
                }
            }
        }
        matrix[controller.verticalPosition()][controller.horizontalPosition()].setText("+");
        if (controller.finished()) {
            Toast toast = Toast.makeText(this, "No more stars.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}