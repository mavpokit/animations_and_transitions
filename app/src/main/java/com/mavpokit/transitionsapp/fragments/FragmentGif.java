package com.mavpokit.transitionsapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mavpokit.transitionsapp.R;

/**
 * Created by Alex on 23.12.2016.
 */

public class FragmentGif extends Fragment {
    private static final String TAG = "-----FragmentGif-----";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View fragmentContainer = inflater.inflate(R.layout.fragment_gif,container,false);

        ImageView imageView = (ImageView)fragmentContainer.findViewById(R.id.imageViewGif);
        Glide.with(this).load(R.drawable.cat).asGif().into(imageView);

        return fragmentContainer;
    }
}
