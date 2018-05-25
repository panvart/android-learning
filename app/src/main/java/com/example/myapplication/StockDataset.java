package com.example.myapplication;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Pankaj on 25/05/18.
 */

public class StockDataset {

    int id;
    String dataset_code;
    String database_code;
    String name;
    double[] data;

    public List<Stock> getStocksList(){

        List<Stock> stocks = new ArrayList<>();

        for(int i=0; i<data.length; i++) {

            Log.d("SDT", i+" Data Length: "+data[i]);
            Stock temp = new Stock(name, database_code, data[i]);
            stocks.add(temp);

        }

        return stocks;

    }

}