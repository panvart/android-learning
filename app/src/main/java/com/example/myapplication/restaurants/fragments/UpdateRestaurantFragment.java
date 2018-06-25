package com.example.myapplication.restaurants.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.myapplication.R;
import com.example.myapplication.restaurants.datatypes.Restaurant;
import com.example.myapplication.restaurants.utils.URLs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import static com.example.myapplication.restaurants.utils.URLs.GET_ALL;
import static com.example.myapplication.restaurants.utils.URLs.PUT_ONE;

/**
 * Created by Pankaj on 20/06/18.
 */

public class UpdateRestaurantFragment extends Fragment {

    private static final String TAG = "UpdateResFrag";
    private static final String BASE_URL = URLs.BASE_URL+"/";
    private Retrofit retrofit;

    private EditText etName;
    private EditText etPlace;
    private EditText etUrl;
    private RatingBar rbRating;
    private ToggleButton tbVegOnly;
    private LinearLayout llProgressBarContainer;
    private Button btnUpdate;
    private OnRestaurantUpdatedListener mUpdateListener;

    private Restaurant oldRestaurant;
    private static final String KEY_ARG_OLDRESTAURANT = "arg_update_frag";

    public interface OnRestaurantUpdatedListener {

        void onRestaurantUpdated(Restaurant old, Restaurant updated);

    }

    public static UpdateRestaurantFragment newInstance(Restaurant oldRestaurant){

        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_ARG_OLDRESTAURANT, oldRestaurant);
        UpdateRestaurantFragment frag = new UpdateRestaurantFragment();
        frag.setArguments(bundle);
        return frag;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oldRestaurant = getArguments().getParcelable(KEY_ARG_OLDRESTAURANT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_updaterestaurant, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etName = view.findViewById(R.id.frag_updaterestaurant_et_name);
        etPlace = view.findViewById(R.id.frag_updaterestaurant_et_place);
        etUrl = view.findViewById(R.id.frag_updaterestaurant_et_link);
        rbRating = view.findViewById(R.id.frag_updaterestaurant_rb_rating);
        tbVegOnly = view.findViewById(R.id.frag_updaterestaurant_tb_veg_nonveg);
        llProgressBarContainer = view.findViewById(R.id.frag_ll_progressbar);
        btnUpdate = view.findViewById(R.id.frag_updaterestaurant_btn_upload);

        etName.setText(oldRestaurant.getName());
        etPlace.setText(oldRestaurant.getPlace());
        etUrl.setText(oldRestaurant.getUrl());
        rbRating.setNumStars(oldRestaurant.getRating());
        rbRating.setMax(oldRestaurant.getRatingMax());
        tbVegOnly.setChecked(oldRestaurant.isVegOnly());

        btnUpdate.setOnClickListener((View v) -> updateRestaurant());

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public interface RestaurantsEndpoints {

        @GET(GET_ALL)
        Call<List<Restaurant>> getRestaurants();

        @PUT(PUT_ONE)
        Call<Restaurant> updateRestaurant(@Path("id") String restaurantId, @Body Restaurant restaurantToUpdate);

    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        mUpdateListener = (OnRestaurantUpdatedListener) context;

        if(mUpdateListener == null)
            throw new RuntimeException("Invalid Update Listener in calling host");

    }

    /**
     * Validate restaurant and make HTTP request to update it
     */
    private void updateRestaurant(){

        final Restaurant restaurantToUpdate = isDataValid();

        if(restaurantToUpdate!=null) {

            RestaurantsEndpoints apiService =
                    retrofit.create(RestaurantsEndpoints.class);

            Call<Restaurant> call = apiService.updateRestaurant(restaurantToUpdate.getId(), restaurantToUpdate);

            call.enqueue(new Callback<Restaurant>() {
                @Override
                public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {

                    Restaurant updatedRestaurant = response.body();

                    if(updatedRestaurant!=null) {
                        mUpdateListener.onRestaurantUpdated(restaurantToUpdate, updatedRestaurant);
                        Toast.makeText(getContext(), "Restaurant updated successfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Log.d(TAG, "Update request unsuccessful");
                }

                @Override
                public void onFailure(Call<Restaurant> call, Throwable t) {
                    Log.d(TAG, "Update request failed: "+t.getMessage());
                }
            });


        } else {
            Toast.makeText(getContext(), "Restaurant updated failed due to null object", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     *
     * @return null is data is invalid
     */
    private Restaurant isDataValid(){

        String id = oldRestaurant.getId();
        String name = etName.getText().toString();
        String place = etPlace.getText().toString();
        String url = etUrl.getText().toString();
        int rating = (int) rbRating.getRating();
        boolean vegOnly = tbVegOnly.isChecked();

        if(name.trim().length()<1
                || place.trim().length()<1
                || url.trim().length()<1
                || rating<1
                || rating>5) {
            Toast.makeText(getContext(), "Trying to update invalid restaurant. Please check!", Toast.LENGTH_SHORT).show();
            return null;
        }

        return new Restaurant(id, name, url, place, rating, getResources().getInteger(R.integer.rating_max), vegOnly);

    }
}
