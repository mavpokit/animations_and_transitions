package com.mavpokit.transitionsapp.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.mavpokit.transitionsapp.activities.DetailActivity;
import com.mavpokit.transitionsapp.R;

/**
 * Created by Alex on 13.12.2016.
 */

public class FragmentWT extends Fragment {

    private static final String TAG = "-----FragmentWT-----";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_wt,container,false);

        placeFragment(false,false);

        Button buttonDefault = (Button)view.findViewById(R.id.buttonDefault);
        Button buttonAlpha = (Button)view.findViewById(R.id.buttonAlpha);
        Button buttonScale = (Button)view.findViewById(R.id.buttonScale);
        Button buttonRotate = (Button)view.findViewById(R.id.buttonRotate);
        Button buttonCombined = (Button)view.findViewById(R.id.buttonCombined);
        Button buttonSlide = (Button)view.findViewById(R.id.buttonSlide);
        final ImageView imageViewFish = (ImageView) view.findViewById(R.id.imageviewFish);
        final ImageView imageViewFishSharedElement = (ImageView) view.findViewById(R.id.imageviewFishSharedElement);

        buttonDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),DetailActivity.class);
                intent.putExtra("title","Default Transition");
                startActivity(intent);
            }
        });
//        if (Build.VERSION.SDK_INT>=21)
//            buttonDefault.setTranslationZ(50);

        buttonAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),DetailActivity.class);
                intent.putExtra(DetailActivity.ANIM_MODE, DetailActivity.ANIM_ALPHA);
                intent.putExtra("title","Alpha Transition");
                startActivity(intent);
            }
        });

        buttonScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),DetailActivity.class);
                intent.putExtra(DetailActivity.ANIM_MODE, DetailActivity.ANIM_SCALE);
                intent.putExtra("title","Scale Transition");
                startActivity(intent);
            }
        });

        buttonRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),DetailActivity.class);
                intent.putExtra(DetailActivity.ANIM_MODE, DetailActivity.ANIM_ROTATE);
                intent.putExtra("title","Rotate Transition");
                startActivity(intent);
            }
        });

        buttonCombined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),DetailActivity.class);
                intent.putExtra(DetailActivity.ANIM_MODE, DetailActivity.ANIM_COMBINED);
                intent.putExtra("title","Combined Transition");
                startActivity(intent);
            }
        });

        buttonSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeFragment(true,true);
            }
        });

        imageViewFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),DetailActivity.class);
                intent.putExtra("title","Thumbnail Scale Up Animation");
//                Bitmap bitmap = ((BitmapDrawable)imageViewFish.getDrawable()).getBitmap();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.fish_small);
                Bundle bundle = ActivityOptions.makeThumbnailScaleUpAnimation(imageViewFish,bitmap,0,0).toBundle();
                startActivity(intent,bundle);
            }
        });

        imageViewFishSharedElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),DetailActivity.class);
                intent.putExtra("title","Shared Element Transition");
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),(View)imageViewFishSharedElement,"sharedFish");
                startActivity(intent,options.toBundle());
            }
        });

        return view;
    }

    private void placeFragment(boolean animate, boolean addoBackStack) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                .beginTransaction();
        if (animate) fragmentTransaction
                .setCustomAnimations(R.anim.slide_up, R.anim.slide_up_exit, R.anim.slide_down_popenter, R.anim.slide_down);
        fragmentTransaction
                .replace(R.id.fragment_wt_container, SlideFragment.getInstance(), "FRAGMENT_TAG");
        if (addoBackStack) fragmentTransaction
                .addToBackStack(null);

        fragmentTransaction.commit();
    }
}
