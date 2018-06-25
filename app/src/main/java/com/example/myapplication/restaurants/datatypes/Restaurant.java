package com.example.myapplication.restaurants.datatypes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Pankaj on 01/06/18.
 */

public class Restaurant implements Parcelable {

    private String id;
    private String name;
    private String imgUrl;
    private String place;
    private int rating;
    private int ratingMax;
    private boolean vegOnly;

    public Restaurant() {
    }

    public Restaurant(String id, String name, String imgUrl, String place, int rating, int ratingMax, boolean vegOnly) {

        this.id = id;
        this.name = name;
        this.place = place;
        this.rating = rating;
        this.ratingMax = ratingMax;
        this.imgUrl = imgUrl;
        this.vegOnly = vegOnly;

    }

    protected Restaurant(Parcel in) {
        id = in.readString();
        name = in.readString();
        imgUrl = in.readString();
        place = in.readString();
        rating = in.readInt();
        ratingMax = in.readInt();
        vegOnly = in.readByte() != 0;
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

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

        try{
            return new URL(imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getUrl() {

       return imgUrl;

    }

    public boolean isVegOnly() {
        return vegOnly;
    }

    public void setVegOnly(boolean vegOnly) {
        this.vegOnly = vegOnly;
    }

    public void setImgUrl(String imgUrl) {
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
                5,
                false
        ));

        list.add(new Restaurant(
                "res_b",
                "Jiva Imperia",
                "https://im1.dineout.co.in/images/uploads/restaurant/sharpen/9/s/t/p9245-147693867158084bafc99c8.jpg?w=400",
                "Begumpet, Secunderabad",
                3,
                5,
                true
        ));

        list.add(new Restaurant(
                "res_c",
                "Headquarters",
                "https://im1.dineout.co.in/images/uploads/restaurant/sharpen/3/y/j/p34752-15259529465af431b2160e6.jpg?w=400",
                "Somajiguda, Central East Hyderabad",
                1,
                5,
                false
        ));

        list.add(new Restaurant(
                "res_d",
                "Paradise",
                "abc",
                "Paradise Circle, Hyderabad",
                4,
                5,
                true
        ));

        return list;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(imgUrl);
        dest.writeString(place);
        dest.writeInt(rating);
        dest.writeInt(ratingMax);
        dest.writeByte((byte) (vegOnly ? 1 : 0));
    }
}
