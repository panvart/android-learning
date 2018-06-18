package com.example.myapplication.restaurants;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.restaurants.adapters.RestaurantsListAdapter;
import com.example.myapplication.restaurants.datatypes.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.android.volley.Request.Method.GET;
import static com.example.myapplication.restaurants.utils.URLs.GET_ALL;
import static com.example.myapplication.restaurants.utils.VolleyTags.TAG_RESTAURANTS_GET_ALL;

/**
 *
 * Resize image - https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html
 *
 * Created by Pankaj on 01/06/18.
 */

public class RestaurantsFragment extends Fragment{

    private RequestQueue netRequests;

    private RestaurantsListAdapter mAdapter;
    private RecyclerView rvRestaurantsList;
    private List<Object> mRestaurantsDataset;

    private static final String TAG = "RestFrag";

    public RestaurantsFragment() {
    }

    /**
     * The onCreateView method is called when Fragment should create its View object hierarchy,
     * either dynamically or via XML layout inflation.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_restaurants, container, false);

    }

    /**
     * This event is triggered soon after onCreateView().
     * Any view setup should occur here.  E.g., view lookups and attaching view listeners.
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvRestaurantsList = view.findViewById(R.id.frag_restaurants_rv_list);
        netRequests = Volley.newRequestQueue(getContext());
        makeHttpGetRequestForRestaurants();
        Log.d(TAG, "END of onViewCreated");
    }

    /**
     * Make HTTP Request to API
     * Parse Response as JSON Object
     * Update Dataset
     */
    private void makeHttpGetRequestForRestaurants(){


        String URL = GET_ALL;

        Log.d(TAG, "URL: "+URL);

        StringRequest strRequest = new StringRequest(GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response==null) {
                            Log.d(TAG, "REST - GET ALL - RESPONSE: NULL");
                            return;
                        }

                        Log.d(TAG, "RESPONSE: "+response);

                        List<Restaurant> restaurantsList = null;

                        try{

                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Restaurant>>(){}.getType();

                            restaurantsList = gson.fromJson(response, type);

                            Log.d(TAG, "Restaurant 0A - "+restaurantsList.get(0)+" instance of "+restaurantsList.get(0).getClass());

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {

                            Log.d(TAG, "Restaurant 0B - "+restaurantsList.get(0)+" instance of "+restaurantsList.get(0).getClass());

                            if(mRestaurantsDataset==null)
                                mRestaurantsDataset = new ArrayList<>();
                            else
                                mRestaurantsDataset.clear();

                            mRestaurantsDataset.addAll(restaurantsList);

                            if(mAdapter==null) {
                                mAdapter = new RestaurantsListAdapter(mRestaurantsDataset, getContext());
                                rvRestaurantsList.setAdapter(mAdapter);
                                rvRestaurantsList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                            }
                            else {
                                mAdapter.notifyDataSetChanged();
                            }

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d(TAG, "ERROR: "+error.getMessage());

                    }
                });


        strRequest.setTag(TAG_RESTAURANTS_GET_ALL);
        netRequests.add(strRequest);

    }

}
