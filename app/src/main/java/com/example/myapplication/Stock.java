package com.example.myapplication;

import java.util.Date;

/**
 * Created by Pankaj on 25/05/18.
 */
class Stock {

    String name;
    String country;
    double price;

    private Date object;

    public Stock(String name, String country, double price) {
        this.name = name;
        this.country = country;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", price=" + price +
                '}';
    }
}
