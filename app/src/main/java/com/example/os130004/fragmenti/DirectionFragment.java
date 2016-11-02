package com.example.os130004.fragmenti;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class DirectionFragment extends Fragment {

    private DirectionCommunicator mListener;

    public DirectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_direction, container, false);

        Button button = (Button) view.findViewById(R.id.down);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.moveDown();
            }
        });
        button = (Button) view.findViewById(R.id.up);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.moveUp();
            }
        });
        button = (Button) view.findViewById(R.id.left);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.moveLeft();
            }
        });
        button = (Button) view.findViewById(R.id.right);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.moveRight();
            }
        });
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DirectionCommunicator) {
            mListener = (DirectionCommunicator) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DirectionCommunicator");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface DirectionCommunicator {

        void moveLeft();
        void moveRight();
        void moveDown();
        void moveUp();
    }
}
