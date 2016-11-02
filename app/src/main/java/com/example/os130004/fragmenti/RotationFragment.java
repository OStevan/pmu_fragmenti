package com.example.os130004.fragmenti;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RotationCommunicator} interface
 * to handle interaction events.
 */
public class RotationFragment extends Fragment {
    public static final String DIRECTION_KEY = "DIRECTION";
    private RotationCommunicator mListener;

    public RotationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rotation, container, false);
        Button button = (Button) view.findViewById(R.id.move);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.move();
            }
        });
        button = (Button) view.findViewById(R.id.rotate_left);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.rotateLeft();
            }
        });
        button = (Button) view.findViewById(R.id.rotate_right);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.rotateRight();
            }
        });

        button = (Button) view.findViewById(R.id.direction);
        button.setText(getArguments().getString(DIRECTION_KEY));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RotationCommunicator) {
            mListener = (RotationCommunicator) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement RotationCommunicator");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

        public interface RotationCommunicator {
        void rotateRight();
        void rotateLeft();
        void move();
    }
}
