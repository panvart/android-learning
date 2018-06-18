package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.restaurants.datatypes.Restaurant;
import com.example.myapplication.restaurants.datatypes.RestaurantHeader;
import com.example.myapplication.restaurants.adapters.RestaurantsListAdapter;
import com.example.myapplication.restaurants.localdb.DBHelper;
import com.example.myapplication.restaurants.localdb.RestaurantsDbAsyncTask;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.GET;
import static com.example.myapplication.restaurants.localdb.DBHelper.CREATE_TABLE_RESTAURANTS_V10;

/**
 * Created by Pankaj on 24/05/18.
 */

public class StocksActivity extends AppCompatActivity {

    private static final String TAG = "StocksAct";

    TextView tvTitle;
    RecyclerView rvStocks;
    StocksAdapter stocksAdapter;
    Button btnRemoveStock;

    private List<Stock> mStocks;
    List<Object> mDataset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocks);

        tvTitle = findViewById(R.id.stocks_title);
        rvStocks = findViewById(R.id.stocks_recyclerview);

        Log.d(TAG, "SQL: "+CREATE_TABLE_RESTAURANTS_V10);

        //demoDatabase();
        demoDatabase_Async();
//      Flexible layout
//        GridLayoutManager gridLayout = new GridLayoutManager(StocksActivity.this,
//                RestaurantListSpan.MAXSPAN,
//                LinearLayoutManager.VERTICAL,
//                false);
//
//        gridLayout.setSpanSizeLookup(new RestaurantListSpan());
//        rvStocks.setLayoutManager(gridLayout);

//        //loadDummyStocks();
//        mStocks = getStockDataset();
//
//        stocksAdapter = new StocksAdapter(mStocks, StocksActivity.this);
//        rvStocks.setAdapter(stocksAdapter);
//        rvStocks.setLayoutManager(new LinearLayoutManager(StocksActivity.this, LinearLayoutManager.VERTICAL, false));
//
//        btnRemoveStock = findViewById(R.id.stocks_btn_remove_stock);
//        btnRemoveStock.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(mStocks.size()<1) {
//
//                    //btnRemoveStock.setEnabled(false);
//                    loadDataFromNetwork();
//
//                } else {
//
//                    int max = mStocks.size();
//
//                    Random random = new Random();
//                    int indexToRemove = random.nextInt(max);
//
//                    Stock stock = mStocks.remove(indexToRemove);
////                Stock stock = mStocks.get(indexToRemove);
////                stock.setPrice(100.0);
//                    Toast.makeText(StocksActivity.this, "Removing row " + (indexToRemove + 1) + " with stock: " + stock.toString(), Toast.LENGTH_LONG).show();
//
//                    stocksAdapter.notifyItemRemoved(indexToRemove);
////                stocksAdapter.notifyItemChanged(indexToRemove);
//                    stocksAdapter.notifyDataSetChanged();
//                }
//
//            }
//        });

    }

    /**
     * Class used to set column spans of items in recyclerview
     */
    public class RestaurantListSpan extends GridLayoutManager.SpanSizeLookup {

        public static final int MAXSPAN = 2;

        public RestaurantListSpan() {
            super();
        }

        @Override
        public int getSpanSize(int position) {

            Object object = mDataset.get(position);
            if(object instanceof RestaurantHeader)
                return MAXSPAN;

            return MAXSPAN/2;
        }

    }

    /**
     * Make HTTP Request to API
     * Parse Response as JSON Object
     * Update Dataset
     */
    private void loadDataFromNetwork(){

        RequestQueue netRequests = Volley.newRequestQueue(StocksActivity.this);
        String URL = "https://www.quandl.com/api/v3/datasets/WIKI/AAPL.json?start_date=1985-05-01&end_date=1997-07-01&order=asc&column_index=4&collapse=quarterly&transformation=rdiff";

        StringRequest strRequest = new StringRequest(GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response==null) {
                            Log.d(TAG, "RESPONSE: NULL");
                            return;
                        }

                        Log.d(TAG, "RESPONSE: "+response);

                        //TODO: 1. CREATE POJO for
                        //"{\"dataset\":{\"id\":9775409,\"dataset_code\":\"AAPL\",\"database_code\":\"WIKI\",\"name\":\"Apple Inc (AAPL) Prices, Dividends, Splits and Trading Volume\",\"description\":\"End of day open, high, low, close and volume, dividends and splits, and split/dividend adjusted open, high, low close and volume for Apple Inc. (AAPL). Ex-Dividend is non-zero on ex-dividend dates. Split Ratio is 1 on non-split dates. Adjusted prices are calculated per CRSP (\\u003ca href=\\\"http://www.crsp.com/products/documentation/crsp-calculations\\\" rel=\\\"nofollow\\\" target=\\\"blank\\\"\\u003ewww.crsp.com/products/documentation/crsp-calculations\\u003c/a\\u003e)\\r\\n\\r\\n\\u003cp\\u003eThis data is in the public domain. You may copy, distribute, disseminate or include the data in other products for commercial and/or noncommercial purposes.\\u003c/p\\u003e\\r\\n\\u003cp\\u003eThis data is part of Quandl's Wiki initiative to get financial data permanently into the public domain. Quandl relies on users like you to flag errors and provide data where data is wrong or missing. Get involved: \\u003ca href=\\\"mailto:connect@quandl.com\\\" rel=\\\"nofollow\\\" target=\\\"blank\\\"\\u003econnect@quandl.com\\u003c/a\\u003e\",\"refreshed_at\":\"2018-03-27T21:46:10.101Z\",\"newest_available_date\":\"2018-03-27\",\"oldest_available_date\":\"1980-12-12\",\"column_names\":[\"Date\",\"Close\"],\"frequency\":\"daily\",\"type\":\"Time Series\",\"premium\":false,\"limit\":null,\"transform\":null,\"column_index\":4,\"start_date\":\"1985-05-01\",\"end_date\":\"1997-07-01\",\"data\":[[\"1985-06-30\",18.0],[\"1985-09-30\",15.75],[\"1985-12-31\",22.0],[\"1986-03-31\",28.25],[\"1986-06-30\",35.88],[\"1986-09-30\",33.5],[\"1986-12-31\",40.5],[\"1987-03-31\",64.5],[\"1987-06-30\",40.5],[\"1987-09-30\",56.5],[\"1987-12-31\",42.0],[\"1988-03-31\",40.0],[\"1988-06-30\",46.25],[\"1988-09-30\",43.25],[\"1988-12-31\",40.25],[\"1989-03-31\",35.63],[\"1989-06-30\",41.25],[\"1989-09-30\",44.5],[\"1989-12-31\",35.25],[\"1990-03-31\",40.25],[\"1990-06-30\",44.75],[\"1990-09-30\",29.0],[\"1990-12-31\",43.0],[\"1991-03-31\",68.0],[\"1991-06-30\",41.5],[\"1991-09-30\",49.5],[\"1991-12-31\",56.38],[\"1992-03-31\",58.25],[\"1992-06-30\",48.0],[\"1992-09-30\",45.13],[\"1992-12-31\",59.75],[\"1993-03-31\",51.5],[\"1993-06-30\",39.5],[\"1993-09-30\",23.37],[\"1993-12-31\",29.25],[\"1994-03-31\",33.25],[\"1994-06-30\",26.5],[\"1994-09-30\",33.69],[\"1994-12-31\",39.0],[\"1995-03-31\",35.25],[\"1995-06-30\",46.44],[\"1995-09-30\",37.25],[\"1995-12-31\",31.87],[\"1996-03-31\",24.56],[\"1996-06-30\",21.0],[\"1996-09-30\",22.19],[\"1996-12-31\",20.87],[\"1997-03-31\",18.25],[\"1997-06-30\",14.25],[\"1997-09-30\",13.19]],\"collapse\":\"quarterly\",\"order\":\"asc\",\"database_id\":4922}}"
                        //TODO: 2. Update UI List Items by adding to stocks list and notifyDatasetUpdated;

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d(TAG, "ERROR: "+error.getMessage());

                    }
                });


        strRequest.setTag("FIRSTREQUEST");
        netRequests.add(strRequest);

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

    /**
     * DB methods for demo
     */
    public void demoDatabase(){

        DBHelper dbHelper = DBHelper.getInstance(StocksActivity.this);

        dbHelper.insertRestaurants(Restaurant.dummyList());

        // Async Task
        mDataset = new ArrayList<>();
        mDataset.add(RestaurantHeader.createHeaderForNovVeg("Non-Veg", 10));
        mDataset.addAll(dbHelper.getAllRestaurants());
        mDataset.add(RestaurantHeader.createHeaderForVegOnly("Veg", 12));
        mDataset.addAll(dbHelper.getAllRestaurants());

        rvStocks.setAdapter(new RestaurantsListAdapter(mDataset, StocksActivity.this));

        // Horizontally/Vertically aligned one item
        rvStocks.setLayoutManager(new LinearLayoutManager(StocksActivity.this, LinearLayoutManager.VERTICAL, false));

    }

    /**
     * DB methods for demo
     */
    public void demoDatabase_Async(){

        RestaurantsDbAsyncTask restaurantsDbAsyncTask = RestaurantsDbAsyncTask.createInstanceGetAllRestaurants(
                StocksActivity.this,
                new RestaurantsDbAsyncTask.DBOperationListener_GetAll() {

                    @Override
                    public void onListOfRestaurantsRetrieved(List<Restaurant> restaurantList) {
                        mDataset = new ArrayList<>();
                        mDataset.add(RestaurantHeader.createHeaderForNovVeg("Non-Veg", 10));
                        mDataset.addAll(restaurantList);
                        rvStocks.setAdapter(new RestaurantsListAdapter(mDataset, StocksActivity.this));

                        // Horizontally/Vertically aligned one item
                        rvStocks.setLayoutManager(new LinearLayoutManager(StocksActivity.this, LinearLayoutManager.VERTICAL, false));

                    }

                    @Override
                    public void onProgressUpdate(Integer progress, Integer progressMax) {

                        Log.d(TAG, "On Progress Update - GET ALL - "+progress);

                    }
                }
        );

        restaurantsDbAsyncTask.execute();




    }

}
