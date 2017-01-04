package com.mavpokit.transitionsapp;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;

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
        tabLayout.setTabTextColors(Color.WHITE,Color.YELLOW);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.indicator));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        setupNavigatonViewClicks(navigationView);

//        myToolbar.setNavigationIcon(R.drawable.ic_apps_white_24dp);
//        getSupportActionBar().setIcon(R.drawable.radioactive);

    }

    private void setupNavigatonViewClicks(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return true;
            }
        });
    }

    private FragmentPagerAdapter getPagerAdapter() {
        return new FragmentPagerAdapter(getSupportFragmentManager()) {

            private Fragment[] fragments = new Fragment[]{
                    new FragmentAM(),
                    new FragmentWT(),
                    new FragmentTF(),
                    new FragmentPA(),
                    new FragmentLT(),
                    new FragmentGif()};

            private static final String TAG = "-----PagerAdapter-----";

            final int PAGE_COUNT = 6;
            private String[] TAB_NAMES = new String[]{
                    "Animated\nmarkers",
                    "Window\nTransitions",
                    "Transitions\nFramework",
                    "Property\nanimation",
                    "Layout\nTransitions",
                    "Gif\nanimation"};

            @Override
            public Fragment getItem(int position) {
                Log.d(TAG, "getItem " + position);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
