package com.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Tutorial - https://guides.codepath.com/android/using-the-recyclerview#using-the-recyclerview
 *
 * Created by Pankaj on 24/05/18.
 */

public class StocksAdapter extends RecyclerView.Adapter<StocksAdapter.StocksViewHolder>{

    List<StocksActivity.Stock> mDataset;
    Context context;



    public StocksAdapter(List<StocksActivity.Stock> mDataset, Context context) {
        this.mDataset = mDataset;
        this.context = context;
    }

    @Override
    public StocksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater li = LayoutInflater.from(context);

        StocksViewHolder stockVH = new StocksViewHolder(li.inflate(R.layout.row_stocks, parent, false));

        return stockVH;

    }

    @Override
    public void onBindViewHolder(StocksViewHolder holder, int position) {

        final StocksActivity.Stock currStock = mDataset.get(position);

        if(currStock!=null) {

            holder.getTvCountry().setText(currStock.getCountry());
            holder.getTvName().setText(currStock.getName());
            holder.getBtnPrice().setText(String.valueOf(currStock.getPrice()));
            holder.getBtnPrice().setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Stock: "+currStock.toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return mDataset==null?0:mDataset.size();
    }

    public class StocksViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCountry;
        private TextView tvName;
        private Button btnPrice;

        public StocksViewHolder(View itemView) {
            super(itemView);
            tvCountry = (TextView) itemView.findViewById(R.id.row_stocks_country);
            tvName = (TextView) itemView.findViewById(R.id.row_stocks_name);
            btnPrice = (Button) itemView.findViewById(R.id.row_stocs_price);
        }

        public TextView getTvCountry() {
            return tvCountry;
        }

        public void setTvCountry(TextView tvCountry) {
            this.tvCountry = tvCountry;
        }

        public TextView getTvName() {
            return tvName;
        }

        public void setTvName(TextView tvName) {
            this.tvName = tvName;
        }

        public Button getBtnPrice() {
            return btnPrice;
        }

        public void setBtnPrice(Button btnPrice) {
            this.btnPrice = btnPrice;
        }
    }

}
