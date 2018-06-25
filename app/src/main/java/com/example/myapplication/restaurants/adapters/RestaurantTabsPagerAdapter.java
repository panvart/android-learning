package com.example.myapplication.restaurants.adapters;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.myapplication.restaurants.datatypes.Restaurant;
import com.example.myapplication.restaurants.fragments.NewRestaurantFragment;
import com.example.myapplication.restaurants.fragments.RestaurantsFragment;
import com.example.myapplication.restaurants.fragments.UpdateRestaurantFragment;

/**
 * Created by Pankaj on 20/06/18.
 */

public class RestaurantTabsPagerAdapter extends FragmentPagerAdapter implements UpdateRestaurantFragment.OnRestaurantUpdatedListener{

    private final String[] TITLE_TABS = new String[]{"Near by", "Add New", "Update"};
    private final int COUNT_TABS = TITLE_TABS.length;

    private Context mContext;
    private FragmentManager fm;
    private static final String TAG = "ResPagerAdap";

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
                return UpdateRestaurantFragment.newInstance(new Restaurant(
                        "res_a",
                        "Tansen",
                        "https://im1.dineout.co.in/images/uploads/restaurant/sharpen/4/r/o/p4258-14640955255744532521c70.jpg?w=400",
                        "Necklace Road, Secunderabad",
                        4,
                        5,
                        false
                ));

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

    @Override
    public void onRestaurantUpdated(Restaurant old, Restaurant updated) {
        Log.d(TAG, "Updated in pager adapter");
    }
}
