package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;
import static com.example.myapplication.StocksUtils.getStockDataset;

/**
 * Created by Pankaj on 24/05/18.
 */

public class StocksActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rvStocks;
    StocksAdapter stocksAdapter;
    Button btnRemoveStock;

    private List<Stock> mStocks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocks);

        tvTitle = findViewById(R.id.stocks_title);
        rvStocks = findViewById(R.id.stocks_recyclerview);

        //loadDummyStocks();
        mStocks = getStockDataset();

        stocksAdapter = new StocksAdapter(mStocks, StocksActivity.this);
        rvStocks.setAdapter(stocksAdapter);
        rvStocks.setLayoutManager(new LinearLayoutManager(StocksActivity.this, LinearLayoutManager.VERTICAL, false));

        btnRemoveStock = findViewById(R.id.stocks_btn_remove_stock);
        btnRemoveStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int max = mStocks.size();

                Random random = new Random();
                int indexToRemove = random.nextInt(max);

//                Stock stock = mStocks.remove(indexToRemove);
                Stock stock = mStocks.get(indexToRemove);
                stock.setPrice(100.0);
                Toast.makeText(StocksActivity.this, "Removing row "+(indexToRemove+1)+" with stock: "+stock.toString(), Toast.LENGTH_LONG).show();

                //stocksAdapter.notifyItemRemoved(indexToRemove);
                stocksAdapter.notifyItemChanged(indexToRemove);

                if(mStocks.size()<1) {

                    btnRemoveStock.setEnabled(false);

                }

            }
        });

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
