package com.mavpokit.transitionsapp;

import android.animation.LayoutTransition;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        setSupportActionBar(myToolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(getPagerAdapter());

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private FragmentPagerAdapter getPagerAdapter() {
        return new FragmentPagerAdapter(getSupportFragmentManager()) {

            private Fragment[] fragments = new Fragment[]{new FragmentLT(),new FragmentTF(),new FragmentWT()};

            private static final String TAG = "-----PagerAdapter-----";

            final int PAGE_COUNT=3;
            private String[] TAB_NAMES = new String[]{"Layout Transitions","Transitions Framework","Window Transitions"};

            @Override
            public Fragment getItem(int position) {
                Log.d(TAG,"getItem "+position);
                return fragments[position];
            }

            @Override
            public int getCount() {
                return PAGE_COUNT;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return TAB_NAMES[position];
            }

        };
    }
}
