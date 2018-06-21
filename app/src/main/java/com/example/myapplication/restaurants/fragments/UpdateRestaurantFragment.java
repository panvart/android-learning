package com.example.myapplication.restaurants.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.restaurants.datatypes.Restaurant;
import com.example.myapplication.restaurants.utils.URLs;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import static com.example.myapplication.restaurants.utils.URLs.GET_ALL;
import static com.example.myapplication.restaurants.utils.URLs.PUT_ONE;

/**
 * Created by Pankaj on 20/06/18.
 */

public class UpdateRestaurantFragment extends Fragment {

    private static final String BASE_URL = URLs.BASE_URL;
    private Retrofit retrofit;
    private EditText etRestaurantId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_updaterestaurant, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etRestaurantId = view.findViewById(R.id.frag_updaterestaurant_et_id);
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
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
        Call<Restaurant> updateRestaurant(@Path("id") String restaurantId);

    }

}
