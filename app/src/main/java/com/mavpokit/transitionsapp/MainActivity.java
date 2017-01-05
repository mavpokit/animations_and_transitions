package com.mavpokit.transitionsapp;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
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

        setupTabs();

        setUpDrawer(myToolbar);

//        myToolbar.setNavigationIcon(R.drawable.ic_apps_white_24dp);
//        getSupportActionBar().setIcon(R.drawable.radioactive);

    }

    private void setUpDrawer(Toolbar myToolbar) {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        setupNavigatonViewClicks(navigationView);
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    private void setupTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(getPagerAdapter());

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.WHITE,Color.YELLOW);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.indicator));

        int[]TAB_IMAGES = new int[]{
                R.drawable.ic_favorite_black_24dp,
                R.drawable.ic_favorite_black_24dp,
                R.drawable.ic_favorite_black_24dp,
                R.drawable.ic_favorite_black_24dp,
                R.drawable.ic_favorite_black_24dp,
                R.drawable.ic_favorite_black_24dp };

        final String[] TAB_NAMES = new String[]{
                "Animated\nmarkers",
                "Window\nTransitions",
                "Transitions\nFramework",
                "Property\nanimation",
                "Layout\nTransitions",
                "Gif\nanimation" };

        for (int i=0;i<tabLayout.getTabCount();i++)
            tabLayout.getTabAt(i).setIcon(R.drawable.ic_favorite_black_24dp);

        //mark first tab icon with color at startup
        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        setTitle(TAB_NAMES[0]);

        //for toolabar title change when scrolling tabs
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                setTitle(TAB_NAMES[position]);
            }
            @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override public void onPageScrollStateChanged(int state) {}
        });

        //for tab icon color change
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                setTitle(tab.getText()); //comment if tab has only icon
                tab.getIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.inactiveTab), PorterDuff.Mode.SRC_IN);
            }

            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void setupNavigatonViewClicks(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = 0;
                switch (item.getItemId()){
                    case R.id.menu_1: position=0;break;
                    case R.id.menu_2: position=1;break;
                    case R.id.menu_3: position=2;break;
                    case R.id.menu_4: position=3;break;
                    case R.id.menu_5: position=4;break;
                    case R.id.menu_6: position=5;break;
                }
                tabLayout.getTabAt(position).select();
                drawerLayout.closeDrawer(GravityCompat.START);
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
//            private String[] TAB_NAMES = new String[]{
//                    "Animated\nmarkers",
//                    "Window\nTransitions",
//                    "Transitions\nFramework",
//                    "Property\nanimation",
//                    "Layout\nTransitions",
//                    "Gif\nanimation"};

            @Override
            public Fragment getItem(int position) {
                Log.d(TAG, "getItem " + position);
                return fragments[position];
            }

            @Override
            public int getCount() {
                return PAGE_COUNT;
            }

//            @Override
//            public CharSequence getPageTitle(int position) {
//                return TAB_NAMES[position];
//                Drawable image = ContextCompat.getDrawable(MainActivity.this, TAB_IMAGES[position]);
//                image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
//                SpannableString sb = new SpannableString("AA ");
//                ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//                sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                return sb;
//            }

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
