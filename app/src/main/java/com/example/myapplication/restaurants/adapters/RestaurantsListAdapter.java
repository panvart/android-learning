package com.example.myapplication.restaurants.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.restaurants.datatypes.Restaurant;
import com.example.myapplication.restaurants.datatypes.RestaurantHeader;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Pankaj on 06/06/18.
 */

public class RestaurantsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEWTYPE_RESTAURANT_HEADER = 100;
    private static final int VIEWTYPE_RESTAURANT_ROW = 300;

    private static final String TAG = "RestAdapter";

    private List<Object> mDataset;
    private Context mContext;

    public RestaurantsListAdapter(List<Object> mDataset, Context mContext) {
        this.mDataset = mDataset;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        switch (viewType) {

            case VIEWTYPE_RESTAURANT_HEADER:

                return new RestaurantHeaderViewHolder(inflater.inflate(R.layout.row_restaurants_list_header, parent, false));

            case VIEWTYPE_RESTAURANT_ROW:

                return new RestaurantRowViewHolder(inflater.inflate(R.layout.row_restaurants_list, parent, false));

            default:
                throw new RuntimeException("Invalid view type");

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int viewType = holder.getItemViewType();
        switch (viewType) {

            case VIEWTYPE_RESTAURANT_HEADER:

                onBind(holder ,mDataset.get(position), mContext);

                break;
            case VIEWTYPE_RESTAURANT_ROW:

                TextView tvRName = ((RestaurantRowViewHolder) holder).getTvName();
                TextView tvRPlace = ((RestaurantRowViewHolder) holder).getTvPlace();
                ImageView ivRIcon = ((RestaurantRowViewHolder) holder).getIvIcon();

                Restaurant row = (Restaurant) mDataset.get(position);
                Log.d(TAG, "ONBIND: "+row.toString());

                tvRName.setText(row.getName());
                tvRPlace.setText(row.getPlace());

                if(row.getImgUrl()!=null)
                    Picasso.get().
                            load(row.getImgUrl().toString())
                            .resizeDimen(R.dimen.dimen_img_restaurant_row, R.dimen.dimen_img_restaurant_row)
                            .placeholder(R.drawable.ic_restaurant_default)
                            .into(ivRIcon);
                else
                    ivRIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_restaurant_default));

                break;
            default:
                throw new RuntimeException("Invalid view type");
        }

    }

    @Override
    public int getItemCount() {
        return mDataset!=null?mDataset.size():0;
    }

    @Override
    public int getItemViewType(int position) {

        Object obj = mDataset.get(position);

        if(obj==null)
            throw new RuntimeException("Dataset has null data");

        if(obj instanceof RestaurantHeader)
            return VIEWTYPE_RESTAURANT_HEADER;
        else if(obj instanceof Restaurant)
            return VIEWTYPE_RESTAURANT_ROW;
        else
            throw new RuntimeException("Datatype not supported");

    }

    private void onBind(RecyclerView.ViewHolder holder, Object data, Context mContext){

        RestaurantHeaderViewHolder restHeaderVH = (RestaurantHeaderViewHolder) holder;

        TextView tvName = restHeaderVH.getTvName();
        TextView tvLabel = restHeaderVH.getTvLabel();
        ImageView ivIcon = restHeaderVH.getIvIcon();

        RestaurantHeader header = (RestaurantHeader) data;
        Log.d(TAG, "ONBIND: "+header.toString());

        ivIcon.setImageDrawable(ContextCompat.getDrawable(mContext, header.getIcResId()));

        tvName.setText(header.getHeading());
        tvLabel.setText(header.getNumRestaurants()+" restaurant(s)");

    }

    class RestaurantRowViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvPlace;
        ImageView ivIcon;
        RatingBar rbRating;

        public RestaurantRowViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.row_restaurants_tv_name);
            tvPlace = itemView.findViewById(R.id.row_restaurants_tv_place);
            ivIcon = itemView.findViewById(R.id.row_restaurants_iv_img);
            rbRating = itemView.findViewById(R.id.row_restaurants_rb_restau_rating);
        }

        public TextView getTvName() {
            return tvName;
        }

        public void setTvName(TextView tvName) {
            this.tvName = tvName;
        }

        public TextView getTvPlace() {
            return tvPlace;
        }

        public void setTvPlace(TextView tvPlace) {
            this.tvPlace = tvPlace;
        }

        public ImageView getIvIcon() {
            return ivIcon;
        }

        public void setIvIcon(ImageView ivIcon) {
            this.ivIcon = ivIcon;
        }

        public RatingBar getRbRating() {
            return rbRating;
        }

        public void setRbRating(RatingBar rbRating) {
            this.rbRating = rbRating;
        }
    }

    class RestaurantHeaderViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvLabel;
        ImageView ivIcon;

        public RestaurantHeaderViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.row_restaurants_list_header_tv_header);
            tvLabel = itemView.findViewById(R.id.row_restaurants_list_header_tv_label);
            ivIcon = itemView.findViewById(R.id.row_restaurants_list_header_iv_img);
        }

        public TextView getTvName() {
            return tvName;
        }

        public void setTvName(TextView tvName) {
            this.tvName = tvName;
        }

        public TextView getTvLabel() {
            return tvLabel;
        }

        public void setTvLabel(TextView tvLabel) {
            this.tvLabel = tvLabel;
        }

        public ImageView getIvIcon() {
            return ivIcon;
        }

        public void setIvIcon(ImageView ivIcon) {
            this.ivIcon = ivIcon;
        }


    }

}
