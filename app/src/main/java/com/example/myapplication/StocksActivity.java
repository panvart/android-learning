package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;
import static android.support.v7.widget.RecyclerView.VERTICAL;

/**
 * Created by Pankaj on 24/05/18.
 */

public class StocksActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rvStocks;
    StocksAdapter stocksAdapter;

    class Stock {

        String name;
        String country;
        double price;

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

    private List<Stock> mStocks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocks);

        tvTitle = findViewById(R.id.stocks_title);
        rvStocks = findViewById(R.id.stocks_recyclerview);

        loadDummyStocks();

        stocksAdapter = new StocksAdapter(mStocks, StocksActivity.this);
        rvStocks.setAdapter(stocksAdapter);
        rvStocks.setLayoutManager(new LinearLayoutManager(StocksActivity.this, HORIZONTAL, false));

    }

    /**
     * Clear existing data and load dummy data
     */
    private void loadDummyStocks(){

        if(mStocks==null)
            mStocks = new ArrayList<>();
        else
            mStocks.clear();

        for(int i=0; i<100; i++) {

            String country = (i%2==0 ? "India":"USA");

            Stock curr = new Stock("Name "+i, country, i*2);
            mStocks.add(curr);

        }

    }

}
