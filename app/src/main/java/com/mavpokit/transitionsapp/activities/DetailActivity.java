package com.mavpokit.transitionsapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.mavpokit.transitionsapp.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView imageView = (ImageView)findViewById(R.id.imageviewFishSharedElement);
//        imageView.setImageDrawable(getResources().getDrawable(R.drawable.fish));

    }
}
