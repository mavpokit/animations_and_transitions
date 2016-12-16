package com.mavpokit.transitionsapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.Scene;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

/**
 * Created by Alex on 12.12.2016.
 */

public class FragmentTF extends Fragment
        implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "-----FragmentTF-----";

    private Scene scene1;
    private Scene scene2;
    private Scene scene3;
    private ViewGroup sceneRoot;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_tf, container, false);

        sceneRoot = (ViewGroup) view.findViewById(R.id.scene_container);
        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene_1, getContext());
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene_2, getContext());
        scene3 = Scene.getSceneForLayout(sceneRoot, R.layout.scene_3, getContext());
//        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene_1a, getContext());
//        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene_2a, getContext());
//        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene1, getContext());
//        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, getContext());

        RadioGroup radioGroup = (RadioGroup)view.findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(this);

        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.radioButton1:
                Log.d(TAG, "radioButton1");
                TransitionManager.go(scene1);
                break;
            case R.id.radioButton2:
                Log.d(TAG, "radioButton2");
                TransitionManager.go(scene2);
                break;
            case R.id.radioButton3:
                Log.d(TAG, "radioButton3");
                TransitionManager.go(scene3);
                break;
            default:break;
        }
    }
}
