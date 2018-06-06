package com.example.myapplication.week4;

import com.example.myapplication.R;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Pankaj on 01/06/18.
 */

public class RestaurantHeader {

    private String heading;
    private int numRestaurants;
    private int icResId;

    private RestaurantHeader(String heading, int numRestaurants, int icResId) {
        this.heading = heading;
        this.numRestaurants = numRestaurants;
        this.icResId = icResId;
    }

    public static RestaurantHeader createHeaderForVegOnly(String heading, int numRestaurants){

        return new RestaurantHeader(heading, numRestaurants, R.drawable.ic_leaf);

    }

    public static RestaurantHeader createHeaderForNovVeg(String heading, int numRestaurants){

        return new RestaurantHeader(heading, numRestaurants, R.drawable.ic_chicken);

    }


    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public int getNumRestaurants() {
        return numRestaurants;
    }

    public void setNumRestaurants(int numRestaurants) {
        this.numRestaurants = numRestaurants;
    }

    public int getIcResId() {
        return icResId;
    }

    public void setIcResId(int icResId) {
        this.icResId = icResId;
    }
}
