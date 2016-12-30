package com.mavpokit.transitionsapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by Alex on 27.12.2016.
 */

public class Fragment1 extends Fragment{
    private static final String TAG = "-----Fragment1-----";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View fragmentContainer = inflater.inflate(R.layout.fragment1,container,false);
        return fragmentContainer;
    }
}
