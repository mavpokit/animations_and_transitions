package com.mavpokit.transitionsapp.fragments;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mavpokit.transitionsapp.R;

/**
 * Created by Alex on 21.12.2016.
 */

public class FragmentPA extends Fragment{
    private static final String TAG = "-----FragmentPA-----";

    TextView textView;
    ImageView imageView;
    RelativeLayout paContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View fragmentContainer = inflater.inflate(R.layout.fragment_pa,container,false);

        textView=(TextView)fragmentContainer.findViewById(R.id.emptyTextView);
        imageView=(ImageView) fragmentContainer.findViewById(R.id.arrowImageView);
        paContainer=(RelativeLayout) fragmentContainer.findViewById(R.id.pa_container);

        return fragmentContainer;
    }

    @Override
    public void onResume() {
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imageView,View.ALPHA,0f,1f);
//        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imageView,"alpha",0f,1f);
        final int duration=1300;
        fadeIn.setDuration(duration);
        fadeIn.setRepeatCount(1);
//        fadeIn.setStartDelay(500);

//        fadeIn.setRepeatCount(ValueAnimator.INFINITE);
        fadeIn.setRepeatMode(ValueAnimator.REVERSE);
//        fadeIn.start();
//        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(imageView,"alpha",1f,0f);
//        fadeOut.setDuration(duration);
//        fadeOut.setStartDelay(duration);
//        fadeOut.start();

        //final position of textView shiold be in the center of display
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        textView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int textViewWidth = textView.getMeasuredWidth();
        int x = display.getWidth()/2 - textViewWidth/2;
        ObjectAnimator moveAnim = ObjectAnimator.ofFloat(textView,View.X,0,x);
        moveAnim.setInterpolator(new BounceInterpolator());
        moveAnim.setDuration(700);
//        moveAnim.start();

        ObjectAnimator backgroundChange;
// if using ofInt for color change, color changes by steps, so we need to use ofObject with argEvaluator
//        backgroundChange = ObjectAnimator.ofInt(paContainer,"backgroundColor", 0xffffffff, 0x55ff0000,0x5500ff00,0x550000ff,0xffffffff);
        backgroundChange = ObjectAnimator.ofObject(paContainer,"backgroundColor", new ArgbEvaluator(),
                0xffffffff,getResources().getColor(R.color.colorPrimary),0xffffffff);
        backgroundChange.setDuration(3000);

        textView.animate().alpha(0).setStartDelay(2000).setDuration(1000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(moveAnim,fadeIn,backgroundChange);
        animatorSet.start();


        super.onResume();
    }
}
