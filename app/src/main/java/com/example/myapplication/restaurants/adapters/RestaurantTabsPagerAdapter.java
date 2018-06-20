package com.example.myapplication.restaurants.adapters;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myapplication.restaurants.fragments.NewRestaurantFragment;
import com.example.myapplication.restaurants.fragments.RestaurantsFragment;

/**
 * Created by Pankaj on 20/06/18.
 */

public class RestaurantTabsPagerAdapter extends FragmentPagerAdapter{

    private final String[] TITLE_TABS = new String[]{"Near by", "Add New", "Update"};
    private final int COUNT_TABS = TITLE_TABS.length;

    private Context mContext;
    private FragmentManager fm;

    public RestaurantTabsPagerAdapter(FragmentManager fm, Context mContext){

        super(fm);
        this.fm = fm;
        this.mContext = mContext;

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                return new RestaurantsFragment();

            case 1:
                return new NewRestaurantFragment();

            case 2:
                return new NewRestaurantFragment();

            default:
                throw new RuntimeException("Invalid number of items in frag adapter");

        }

    }

    @Override
    public int getCount() {
        return COUNT_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLE_TABS[position];
    }
}
