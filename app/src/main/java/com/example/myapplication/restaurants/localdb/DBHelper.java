package com.example.myapplication.restaurants.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.restaurants.datatypes.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * SQLite Database helper class
 *
 * Created by Pankaj on 08/06/18.
 */

public final class DBHelper extends SQLiteOpenHelper {

    private static DBHelper dbHelper;
    private static final String TAG = "DBHelper";

    private static final int DATABASE_VERSION = 12;
    private static final String DATABASE_NAME = "restaurants_db";

    private static final String TABLE_RESTAURANTS = "restaurants";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_URL_IMG = "url_img";
    private static final String COLUMN_PLACE = "place";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_RATING_MAX = "rating_max";
    private static final String COLUMN_VEGONLY = "veg_only";

    public static final String CREATE_TABLE_RESTAURANTS_V10 = "" +
            "CREATE TABLE " + TABLE_RESTAURANTS +
            "   ( " + COLUMN_ID + " TEXT PRIMARY KEY, "+
            "   " + COLUMN_NAME + " TEXT, "+
            "   " + COLUMN_URL_IMG + " TEXT, "+
            "   " + COLUMN_PLACE + " TEXT, "+
            "   " + COLUMN_RATING + " NUMBER, "+
            "   " + COLUMN_RATING_MAX + " NUMBER, "+
            "   " + COLUMN_VEGONLY + " SHORT )";

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Return class variable DBHelper
     * @param context
     * @return
     */
    public static DBHelper getInstance(Context context){

        if(dbHelper==null)
            dbHelper = new DBHelper(context);
        return dbHelper;

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_RESTAURANTS_V10);
        Log.d(TAG, "SUCCESS: onCreate "+db.getVersion());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_RESTAURANTS);
        db.execSQL(CREATE_TABLE_RESTAURANTS_V10);
        Log.d(TAG, "SUCCESS: onUpgrade to "+newVersion);

    }

    /**
     * Insert or update given restaurant in local database
     * @param restaurant
     * @return true if inserted
     */
    public boolean insertOrUpdateRestaurant(Restaurant restaurant){

        boolean isSuccess = false;

        if(restaurant==null
                || restaurant.getId()==null
                || restaurant.getId().length()<1) {
            Log.d(TAG, "Invalid restaurant to insert");
            return isSuccess;
        }

        SQLiteDatabase db = getWritableDatabase();

        try {

            db.beginTransaction();

            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, restaurant.getId());
            values.put(COLUMN_NAME, restaurant.getId());
            values.put(COLUMN_URL_IMG, restaurant.getId());
            values.put(COLUMN_PLACE, restaurant.getId());
            values.put(COLUMN_RATING, restaurant.getId());
            values.put(COLUMN_RATING_MAX, restaurant.getId());
            values.put(COLUMN_VEGONLY, restaurant.getId());

            int numRows = db.update(TABLE_RESTAURANTS, values, COLUMN_ID+" = ?", new String[]{restaurant.getId()});

            if(numRows<1) {

                // There is no restaurant record with given id to update. So insert it.
                long rowIdInserted = db.insertOrThrow(TABLE_RESTAURANTS, null, values);
                if(rowIdInserted==-1) {
                    Log.d(TAG, "Restaurant insertion failed in db - "+restaurant.toString());
                    isSuccess = false;
                } else {
                    Log.d(TAG, "Restaurant inserted in db - " + restaurant.toString());
                    isSuccess = true;
                }


            } else {

                Log.d(TAG, "Restaurant updated in db - "+restaurant.toString());
                isSuccess = true;


            }

            if(isSuccess)
                db.setTransactionSuccessful();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }


        return isSuccess;

    }

    /**
     * Insert multiple restaurants in one transaction
     * @param restaurantList
     * @return
     */
    public boolean insertRestaurants(List<Restaurant> restaurantList) {

        boolean isSuccess = false;

        if(restaurantList==null
                || restaurantList.size()<1) {
            Log.d(TAG, "Invalid restaurants list to insert.");
            return isSuccess;
        }

        SQLiteDatabase db = getWritableDatabase();

        try {

            db.beginTransaction();

            Log.d(TAG, String.format("To insert %d restaurants in table", restaurantList.size()));

            for (Restaurant restaurant : restaurantList) {

                Log.d(TAG, "INSERT: "+restaurant.toString());

                ContentValues values = new ContentValues();
                values.put(COLUMN_ID, restaurant.getId());
                values.put(COLUMN_NAME, restaurant.getName());
                values.put(COLUMN_URL_IMG, restaurant.getImgUrl()!=null?restaurant.getImgUrl().toString():null);
                values.put(COLUMN_PLACE, restaurant.getPlace());
                values.put(COLUMN_RATING, restaurant.getRating());
                values.put(COLUMN_RATING_MAX, restaurant.getRatingMax());
                values.put(COLUMN_VEGONLY, 0);

                int numRows = db.update(TABLE_RESTAURANTS, values, COLUMN_ID+" = ?", new String[]{restaurant.getId()});

                if(numRows<1) {

                    // There is no restaurant record with given id to update. So insert it.
                    long rowIdInserted = db.insertOrThrow(TABLE_RESTAURANTS, null, values);
                    if(rowIdInserted==-1) {
                        Log.d(TAG, "Restaurant insertion failed in db - "+restaurant.toString());
                        isSuccess = false;
                        break;
                    } else {
                        Log.d(TAG, "Restaurant inserted in db - " + restaurant.toString());
                        isSuccess = true;
                    }


                } else {

                    Log.d(TAG, "Restaurant updated in db - "+restaurant.toString());
                    isSuccess = true;


                }

            }

            if(isSuccess)
                db.setTransactionSuccessful();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

        return isSuccess;
    }

    /**
     * Delete the given restaurant from database
     * @param restaurant
     * @return
     */
    public boolean deleteRestaurant(Restaurant restaurant) {

        if(restaurant==null
                || restaurant.getId()==null
                || restaurant.getId().length()<1) {

            Log.d(TAG, "Invalid restaurant id to delete");
            return false;

        }

        return deleteRestaurants(COLUMN_ID+" = ?", new String[]{restaurant.getId()});

    }

    /**
     * Deletes all restaurants from database
     * @return
     */
    public boolean deleteAllRestaurants() {

        return deleteRestaurants(null, null);
    }

    /**
     * Deletes all restaurants from database
     * @return
     */
    private boolean deleteRestaurants(String whereClause, String[] whereArgs) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        boolean isSuccess = false;
        if(db!=null) {

            try{

                db.beginTransaction();
                db.delete(TABLE_RESTAURANTS, whereClause, whereArgs);
                db.setTransactionSuccessful();
                isSuccess = true;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.endTransaction();
            }

        }

        return isSuccess;

    }

    /**
     * Return all restaurants in db
     * @return
     */
    public List<Restaurant> getAllRestaurants(){

        List<Restaurant> listRestaurants = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = null;
        try {


            cursor = db.query(
                    TABLE_RESTAURANTS,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            while (cursor.moveToNext()) {

                Restaurant curr = new Restaurant(
                        cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_URL_IMG)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_PLACE)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_RATING)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_RATING_MAX)),
                        cursor.getShort(cursor.getColumnIndex(COLUMN_VEGONLY))==1
                );

                Log.d(TAG, "GET: "+curr.toString());
                listRestaurants.add(curr);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(cursor!=null && !cursor.isClosed())
                cursor.close();
        }

        return listRestaurants;


    }

}
