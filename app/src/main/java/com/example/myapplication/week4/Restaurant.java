package com.example.myapplication.week4;

import android.widget.ArrayAdapter;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Pankaj on 01/06/18.
 */

public class Restaurant {

    private String id;
    private String name;
    private URL imgUrl;
    private String place;
    private int rating;
    private int ratingMax;
    private boolean vegOnly;

    public Restaurant(String id, String name, String imgUrl, String place, int rating, int ratingMax) {

        this.id = id;
        this.name = name;
        this.place = place;
        this.rating = rating;
        this.ratingMax = ratingMax;
        try{
            this.imgUrl = new URL(imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
            this.imgUrl = null;
        }

    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(URL imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRatingMax() {
        return ratingMax;
    }

    public void setRatingMax(int ratingMax) {
        this.ratingMax = ratingMax;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", imgUrl=" + imgUrl +
                ", place='" + place + '\'' +
                ", rating=" + rating +
                ", ratingMax=" + ratingMax +
                '}';
    }

    public static ArrayList<Restaurant> dummyList(){

        ArrayList<Restaurant> list = new ArrayList<>();

        list.add(new Restaurant(
                "res_a",
                "Tansen",
                "https://im1.dineout.co.in/images/uploads/restaurant/sharpen/4/r/o/p4258-14640955255744532521c70.jpg?w=400",
                "Necklace Road, Secunderabad",
                4,
                5
        ));

        list.add(new Restaurant(
                "res_b",
                "Jiva Imperia",
                "https://im1.dineout.co.in/images/uploads/restaurant/sharpen/9/s/t/p9245-147693867158084bafc99c8.jpg?w=400",
                "Begumpet, Secunderabad",
                3,
                5
        ));

        list.add(new Restaurant(
                "res_c",
                "Headquarters",
                "https://im1.dineout.co.in/images/uploads/restaurant/sharpen/3/y/j/p34752-15259529465af431b2160e6.jpg?w=400",
                "Somajiguda, Central East Hyderabad",
                1,
                5
        ));

        list.add(new Restaurant(
                "res_d",
                "Paradise",
                "abc",
                "Paradise Circle, Hyderabad",
                4,
                5
        ));

        return list;

    }

}
