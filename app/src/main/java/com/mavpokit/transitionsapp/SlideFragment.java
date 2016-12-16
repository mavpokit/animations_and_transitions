package com.mavpokit.transitionsapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Alex on 14.12.2016.
 */

public class SlideFragment extends Fragment {

    private static int create_index=0;
    private int index=0;

    public static Fragment getInstance(){
        return new SlideFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        index=create_index++;
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slide,container,false);
        TextView textView = (TextView)view.findViewById(R.id.slide_fragment_index_text_view);
        textView.setText("Slide Fragment #"+index);
        return view;
    }
}
