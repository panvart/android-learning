package com.example.myapplication.restaurants;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.restaurants.adapters.RestaurantTabsPagerAdapter;

/**
 * Created by Pankaj on 13/06/18.
 */

public class RestaurantsActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_viewpager);

        mViewPager = findViewById(R.id.activity_restaurants_vp_viewpager);
        mTabLayout = findViewById(R.id.activity_restaurants_tl_tabs);

        mViewPager.setAdapter(new RestaurantTabsPagerAdapter(getSupportFragmentManager(), this));
        mTabLayout.setupWithViewPager(mViewPager);

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
