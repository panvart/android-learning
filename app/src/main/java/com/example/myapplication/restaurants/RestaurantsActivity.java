package com.example.myapplication.restaurants;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.myapplication.R;

/**
 * Created by Pankaj on 13/06/18.
 */

public class RestaurantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

//        FragmentManager fm = getSupportFragmentManager();
//        RestaurantsFragment restFrag = new RestaurantsFragment();
//        FragmentTransaction ftrans = fm.beginTransaction();
//        ftrans.replace(R.id.activity_restaurants_fl_container, restFrag);
//        ftrans.commit();

        FragmentManager fm = getSupportFragmentManager();
        NewRestaurantFragment restFrag = new NewRestaurantFragment();
        FragmentTransaction ftrans = fm.beginTransaction();
        ftrans.replace(R.id.activity_restaurants_fl_container, restFrag);
        ftrans.commit();


    }
}
