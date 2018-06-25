package com.example.myapplication.restaurants;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.restaurants.adapters.RestaurantTabsPagerAdapter;
import com.example.myapplication.restaurants.datatypes.Restaurant;
import com.example.myapplication.restaurants.fragments.UpdateRestaurantFragment;

/**
 * Created by Pankaj on 13/06/18.
 */

public class RestaurantsActivity extends AppCompatActivity implements UpdateRestaurantFragment.OnRestaurantUpdatedListener{

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private static final String TAG = "RestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_viewpager);

        mViewPager = findViewById(R.id.activity_restaurants_vp_viewpager);
        mTabLayout = findViewById(R.id.activity_restaurants_tl_tabs);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                FragmentManager fm = getSupportFragmentManager();
                Log.d(TAG, "FRAGMENT STACK: "+fm.getFragments().toString());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setAdapter(new RestaurantTabsPagerAdapter(getSupportFragmentManager(), this));
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onRestaurantUpdated(Restaurant old, Restaurant updated) {
        Log.d(TAG, "Restaurant updated activity handled");
    }


//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_restaurants);
//
//        FragmentManager fm = getSupportFragmentManager();
//        RestaurantsFragment restFrag = new RestaurantsFragment();
//        FragmentTransaction ftrans = fm.beginTransaction();
//        ftrans.replace(R.id.activity_restaurants_fl_container, restFrag);
//        ftrans.commit();
//
//        FragmentManager fm = getSupportFragmentManager();
//        NewRestaurantFragment restFrag = new NewRestaurantFragment();
//        FragmentTransaction ftrans = fm.beginTransaction();
//        ftrans.replace(R.id.activity_restaurants_fl_container, restFrag);
//        ftrans.commit();
//
//    }
}
