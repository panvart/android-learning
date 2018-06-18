package com.example.myapplication.restaurants;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.restaurants.datatypes.Restaurant;
import com.example.myapplication.restaurants.utils.URLs;
import com.google.gson.Gson;

import static com.android.volley.Request.Method.POST;

/**
 * Created by Pankaj on 18/06/18.
 */

public class NewRestaurantFragment extends Fragment {

    private RequestQueue netRequests;
    private static final String TAG = "NewRestFrag";

    private EditText etName;
    private EditText etPlace;
    private EditText etUrl;
    private RatingBar rbRating;
    private ToggleButton tbVegOnly;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_newrestaurant, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etName = view.findViewById(R.id.frag_newrestaurant_et_name);
        etPlace = view.findViewById(R.id.frag_newrestaurant_et_place);
        etUrl = view.findViewById(R.id.frag_newrestaurant_et_link);
        rbRating = view.findViewById(R.id.frag_newrestaurant_rb_rating);
        tbVegOnly = view.findViewById(R.id.frag_newrestaurant_tb_veg_nonveg);

        netRequests = Volley.newRequestQueue(getContext());

    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_new_restaurant, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_save:
                createRestaurant();
                return true;
            case R.id.menu_discard:
                Toast.makeText(getContext(), "Discard data.", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;

        }


    }

    /**
     *
     * @return true if new restaurant created
     */
    private boolean createRestaurant(){


        String name = etName.getText().toString();
        String place = etPlace.getText().toString();
        String url = etUrl.getText().toString();
        int rating = (int) rbRating.getRating();

        if(name.trim().length()<1
                || place.trim().length()<1
                || url.trim().length()<1
                || rating<1
                || rating>5)
            return false;

        Restaurant curr = new Restaurant(null, name, place, url, rating, getResources().getInteger(R.integer.rating_max), tbVegOnly.isPressed());
        Gson gson = new Gson();
        final String body = gson.toJson(curr);

        String URL = URLs.POST_ONE;

        StringRequest postRestaurant = new StringRequest(POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG, "Restaurant Added - "+response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Restaurant Add - Error");
                    }
                }) {

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {

                return body.getBytes();

            }
        };

        netRequests.add(postRestaurant);
        return true;

    }

}
