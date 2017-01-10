package com.mavpokit.transitionsapp.fragments;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mavpokit.transitionsapp.R;

/**
 * Created by Alex on 12.12.2016.
 */

public class FragmentLT extends Fragment {

    private static final String TAG = "-----FragmentLT-----";

    Button buttonAdd;
    CheckBox checkBoxLT;
    TextView textView;

    private static int counter=0;

    private static FragmentLT instance = null;

    ViewGroup viewGroup;


    public static synchronized Fragment getInstance(){
        if (instance==null) instance = new FragmentLT();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View fragmentContainer = inflater.inflate(R.layout.fragment_lt,container,false);

        textView=(TextView)fragmentContainer.findViewById(R.id.textView);

        viewGroup = (ViewGroup) fragmentContainer.findViewById(R.id.container);

        checkBoxLT=(CheckBox)fragmentContainer.findViewById(R.id.checkBoxLT);

        checkBoxLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLayoutTransitions();
            }
        });

        buttonAdd=(Button)fragmentContainer.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Button button = new Button(getContext());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewGroup.removeView(button);
                    }
                });
                button.setText("dynamic button #"+(++counter));
                viewGroup.addView(button);
                if (textView.getVisibility()!=View.VISIBLE) textView.setVisibility(View.VISIBLE);
            }
        });

        return fragmentContainer;
    }

    private void setLayoutTransitions() {
        if (checkBoxLT.isChecked()){
            LayoutTransition l = new LayoutTransition();
            l.enableTransitionType(LayoutTransition.CHANGING);
            viewGroup.setLayoutTransition(l);
        }
        else viewGroup.setLayoutTransition(null);
    }


    @Override
    public void onResume() {
        setLayoutTransitions();
        super.onResume();
    }
}
