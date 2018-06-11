package com.example.myapplication.week4.localdb;

import android.content.Context;
import android.os.AsyncTask;

import com.example.myapplication.week4.Restaurant;

import java.util.List;

/**
 * Created by Pankaj on 11/06/18.
 */

public class RestaurantsDbAsyncTask extends AsyncTask<Restaurant, Integer, Object> {

    private Context mContext;
    private enum Mode {GETALL, INSERTALL}
    private Mode mode;
    private static final Integer PROGRESS_MAX = 100;

    private DBOperationListener_GetAll mListenerGetAll;
    private DBOperationListener_InsertAll mListenerInsertAll;

    private interface DBOperationListener {

        void onProgressUpdate(Integer progress, Integer progressMax);

    }

    public interface DBOperationListener_GetAll extends DBOperationListener {

        void onListOfRestaurantsRetrieved(List<Restaurant> restaurantList);

    }

    public interface DBOperationListener_InsertAll extends DBOperationListener {

        void onListOfRestaurantsInserted(boolean isSuccess);

    }

    private RestaurantsDbAsyncTask(Context mContext, Mode mode,
                                   DBOperationListener_GetAll mListenerGetAll,
                                   DBOperationListener_InsertAll mListenerInsertAll) {

        if(mContext==null)
            throw new RuntimeException("Null context");
        this.mContext = mContext;
        this.mode = mode;
        this.mListenerInsertAll = mListenerInsertAll;
        this.mListenerGetAll = mListenerGetAll;

    }

    public static RestaurantsDbAsyncTask createInstanceGetAllRestaurants(Context mContext, DBOperationListener_GetAll mListenerGetAll){

        return new RestaurantsDbAsyncTask(mContext, Mode.GETALL, mListenerGetAll, null);

    }

    public static RestaurantsDbAsyncTask createInstanceInsertAllRestaurants(Context mContext, DBOperationListener_InsertAll mListenerInsertAll){

        return new RestaurantsDbAsyncTask(mContext, Mode.INSERTALL, null, mListenerInsertAll);

    }


    @Override
    protected Object doInBackground(Restaurant... restaurants) {

        if(mode==Mode.GETALL) {

            DBHelper dbHelper = DBHelper.getInstance(mContext);
            int progress = 1;
            publishProgress(progress);
            List<Restaurant> restaurantList = dbHelper.getAllRestaurants();
            progress = 100;
            publishProgress(progress);
            return restaurantList;

        } else if(mode==Mode.INSERTALL) {

            if(restaurants==null)
                return null;

            DBHelper dbHelper = DBHelper.getInstance(mContext);
            int progress = 1;

            publishProgress(progress);

            int size = restaurants.length;

            for(Restaurant res : restaurants) {

                dbHelper.insertOrUpdateRestaurant(res);
                publishProgress(PROGRESS_MAX/size);

            }

            return true;


        } else {

            throw new RuntimeException("Invalid action for Restaurants Async Task");

        }

    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        if(mode==Mode.GETALL)
            mListenerGetAll.onProgressUpdate(values[0], PROGRESS_MAX);
        else if(mode==Mode.INSERTALL)
            mListenerInsertAll.onProgressUpdate(values[0], PROGRESS_MAX);

    }

    @Override
    protected void onPostExecute(Object result) {

        if(mode==Mode.GETALL)
            mListenerGetAll.onListOfRestaurantsRetrieved((List<Restaurant>) result );
        else if(mode==Mode.INSERTALL)
            mListenerInsertAll.onListOfRestaurantsInserted((boolean) result);

    }
}

