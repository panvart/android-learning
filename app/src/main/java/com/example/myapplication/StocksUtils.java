package com.example.myapplication;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Created by Pankaj on 25/05/18.
 */

public class StocksUtils {

    private static final String TAG = "StocksUtil";

    private static String STOCK_PNB = "{\"id\":6755,\"dataset_code\":\"PNB\",\"database_code\":\"NSE\",\"name\":\"Punjab National Bank\",\"description\":\"Historical prices for Punjab National Bank\\u003cbr\\u003e\\u003cbr\\u003eNational Stock Exchange of India\\u003cbr\\u003e\\u003cbr\\u003eTicker: PNB\\u003cbr\\u003e\\u003cbr\\u003eISIN: INE160A01022\"," +
            "\"data\":[ 171.5,175.2,168.8,170.9,171.35,15313331.0,175.2]}";

    private static String STOCK_PNB_EXTENDED = "{\"dataset\":{\"id\":6755,\"dataset_code\":\"PNB\",\"database_code\":\"NSE\",\"name\":\"Punjab National Bank\",\"description\":\"Historical prices for Punjab National Bank\\u003cbr\\u003e\\u003cbr\\u003eNational Stock Exchange of India\\u003cbr\\u003e\\u003cbr\\u003eTicker: PNB\\u003cbr\\u003e\\u003cbr\\u003eISIN: INE160A01022\",\"data\":[[\"2018-01-31\",171.5,175.2,168.8,170.9,171.35,15313331.0,26320.88],[\"2018-01-30\",173.3,175.9,171.4,172.25,172.65,16914910.0,29341.51],[\"2018-01-29\",181.7,182.35,172.6,173.0,173.95,18481283.0,32703.52],[\"2018-01-25\",197.0,197.0,180.0,181.4,180.9,45808402.0,84712.34],[\"2018-01-24\",185.25,196.35,182.1,195.45,194.65,50753260.0,96318.97],[\"2018-01-23\",176.05,187.8,175.25,186.0,185.8,28176767.0,51344.08],[\"2018-01-22\",176.1,177.3,172.2,176.2,176.0,11370450.0,19896.68],[\"2018-01-19\",170.8,178.0,169.35,176.9,176.4,21519974.0,37574.06],[\"2018-01-18\",180.7,182.75,168.65,170.35,170.5,32923946.0,58071.0],[\"2018-01-17\",165.5,177.0,162.65,176.5,175.55,25299752.0,43070.74],[\"2018-01-16\",172.25,172.95,165.1,165.7,165.55,11916843.0,20066.79],[\"2018-01-15\",174.95,175.85,171.85,172.05,172.15,7104648.0,12340.18],[\"2018-01-12\",173.5,174.9,171.45,174.5,174.2,11223630.0,19450.16],[\"2018-01-11\",170.3,173.6,169.55,173.3,172.75,8683986.0,14922.25],[\"2018-01-10\",174.45,174.9,170.0,170.1,170.65,9021131.0,15507.28],[\"2018-01-09\",175.3,177.9,174.05,174.55,174.65,11152776.0,19639.04],[\"2018-01-08\",177.05,178.15,175.1,175.65,175.7,7302294.0,12881.7],[\"2018-01-05\",180.4,180.5,175.4,176.05,176.0,15500426.0,27508.99],[\"2018-01-04\",167.35,179.7,165.4,178.0,176.45,31613344.0,54653.03],[\"2018-01-03\",168.0,169.3,166.2,166.5,166.6,8534233.0,14308.85],[\"2018-01-02\",170.5,170.9,165.4,166.15,166.4,13729132.0,22963.83],[\"2018-01-01\",172.95,173.4,168.9,169.7,169.75,7869149.0,13480.32]],\"collapse\":null,\"order\":null,\"database_id\":33}}";

    /**
     * Return Stocks list.
     * @return
     */
    public static List<Stock> getStockDataset() {

        Log.d(TAG, "INPUT - "+STOCK_PNB);

        Gson gson = new GsonBuilder().create();
        StockDataset temp = gson.fromJson(STOCK_PNB, StockDataset.class);
        return temp.getStocksList();

    }

}
