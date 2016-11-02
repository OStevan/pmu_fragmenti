package com.example.os130004.fragmenti;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Controller.Refreshable, DirectionFragment.DirectionCommunicator, RotationFragment.RotationCommunicator {
    private static final String KEY_CONTROLLER = "com.example.os130004.fragmenti.Controller";

    private ImageView[][] matrix;
    private Controller controller;
    private static final int M = 10;
    private static final int N = 10;

    private  RadioButton directionButton;
    private  RadioButton rotationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matrix = new ImageView[N][M];

        if(savedInstanceState != null) {
            controller = (Controller) savedInstanceState.get(KEY_CONTROLLER);
            assert controller != null;
            controller.setRefreshable(this);
        } else {
            controller = new Controller(this);
        }

        LinearLayout matrix = (LinearLayout) findViewById(R.id.matrix);

        for (int i = 0; i < N; i++) {

            LinearLayout row = new LinearLayout(this);
            ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            row.setOrientation(LinearLayout.HORIZONTAL);


            for (int j = 0; j < M; j++) {
                this.matrix[i][j] = new ImageView(this);
                ViewGroup.LayoutParams columnParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                row.addView(this.matrix[i][j], columnParams);
            }

            matrix.addView(row, params);
        }


        directionButton = (RadioButton) findViewById(R.id.radio_direction);
        rotationButton = (RadioButton) findViewById(R.id.radio_rotation);


        refresh();
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
                    matrix[i][j].setBackgroundColor(Color.BLUE);
                } else {
                    matrix[i][j].setBackgroundColor(Color.RED);
                }
            }
        }
        matrix[controller.verticalPosition()][controller.horizontalPosition()].setBackgroundColor(Color.GRAY);
        if (controller.finished()) {
            Toast toast = Toast.makeText(this, "No more stars.", Toast.LENGTH_SHORT);
            toast.show();
        }


        if(controller.changeFragment())
            changeFragment();

    }

    private void changeFragment() {
        if (!controller.fragmentChoice()){
            DirectionFragment directionFragment = new DirectionFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, directionFragment).commit();
            directionButton.setChecked(true);
            rotationButton.setChecked(false);
        } else {
            RotationFragment rotationFragment = new RotationFragment();
            Bundle bundle = new Bundle();
            bundle.putString(RotationFragment.DIRECTION_KEY, controller.direction());
            rotationFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, rotationFragment).commit();
            directionButton.setChecked(false);
            rotationButton.setChecked(true);
        }
        controller.fragmentChanged();
    }

    @Override
    public void moveLeft() {
        controller.left();
    }

    @Override
    public void moveRight() {
        controller.right();
    }

    @Override
    public void moveDown() {
        controller.down();
    }

    @Override
    public void moveUp() {
        controller.up();
    }

    @Override
    public void rotateRight() {
        controller.rotateRight();
    }

    @Override
    public void rotateLeft() {
        controller.rotateLeft();
    }

    @Override
    public void move() {
        controller.move();
    }

    public void switchFragment(View view) {
        controller.switchFragment();
    }
}