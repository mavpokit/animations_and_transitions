package com.mavpokit.transitionsapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by Alex on 12.12.2016.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "-----PagerAdapter-----";

    final int PAGE_COUNT=3;
    private String[] TAB_NAMES = new String[]{"Layout Transitions","Transitions Framework","Window Transitions"};

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Log.d(TAG,"getItem "+position);

        switch (position){
            case 0: return new FragmentLT();
            case 1: return new FragmentTF();
            case 2: return new FragmentWT();
            default:return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_NAMES[position];
    }
}
