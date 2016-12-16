package com.mavpokit.transitionsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailActivity2 extends AppCompatActivity {

    public static final String ANIM_MODE="ANIM_MODE";
    public static final int ANIM_ALPHA=1;
    public static final int ANIM_SCALE=2;
    public static final int ANIM_ROTATE=3;
    public static final int ANIM_COMBINED=4;

    int animMode=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        switch (getIntent().getIntExtra(ANIM_MODE,0)) {
            case ANIM_ALPHA: overridePendingTransition(R.anim.fade_in,R.anim.fade_out);break;
            case ANIM_SCALE: overridePendingTransition(R.anim.scale_in,R.anim.scale_out);break;
            case ANIM_ROTATE: overridePendingTransition(R.anim.rotate_in,R.anim.rotate_out);break;
            case ANIM_COMBINED: overridePendingTransition(R.anim.combined_in,R.anim.combined_out);break;
            default:break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        outState.putInt(ANIM_MODE,animMode);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void finish() {
        super.finish();

        switch (getIntent().getIntExtra(ANIM_MODE,0)) {
            case ANIM_ALPHA: overridePendingTransition(R.anim.fade_in,R.anim.fade_out);break;
            case ANIM_SCALE: overridePendingTransition(R.anim.scale_in,R.anim.scale_out);break;
            case ANIM_ROTATE: overridePendingTransition(R.anim.rotate_in,R.anim.rotate_out);break;
            case ANIM_COMBINED: overridePendingTransition(R.anim.combined_in,R.anim.combined_out);break;
            default:break;
        }
    }
}
