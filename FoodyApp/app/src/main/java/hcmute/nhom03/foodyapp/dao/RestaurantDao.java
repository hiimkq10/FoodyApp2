package hcmute.nhom03.foodyapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import hcmute.nhom03.foodyapp.Database.DatabaseHelper;
import hcmute.nhom03.foodyapp.model.Restaurant;

public class RestaurantDao {
    DatabaseHelper databaseHelper;

    public RestaurantDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void insertRestaurant(Restaurant restaurant) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues restaurantValues = new ContentValues();
        restaurantValues.put("Name", restaurant.getName());
        restaurantValues.put("Description", restaurant.getDescription());
        restaurantValues.put("Delivery", restaurant.getDelivery());
        restaurantValues.put("Image", restaurant.getImage());
        restaurantValues.put("OpenHours", restaurant.getOpenHours());
        restaurantValues.put("Address", restaurant.getAddress());
        db.insert("Restaurant", null, restaurantValues);
    }

    public void updateRestaurant(Restaurant restaurant) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues restaurantValues = new ContentValues();
        restaurantValues.put("Name", restaurant.getName());
        restaurantValues.put("Description", restaurant.getDescription());
        restaurantValues.put("Delivery", restaurant.getDelivery());
        restaurantValues.put("Image", restaurant.getImage());
        restaurantValues.put("OpenHours", restaurant.getOpenHours());
        restaurantValues.put("Address", restaurant.getAddress());
        db.update("Restaurant", restaurantValues, "ID = ?", new String[] {String.valueOf(restaurant.getId())});
    }

    public void deleteRestaurant(Restaurant restaurant) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete("Restaurant", "ID = ?", new String[] {String.valueOf(restaurant.getId())});
    }

    public Cursor getRestaurants() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        return db.query("Restaurant",
                new String[] {"ID", "Name", "Description", "Delivery", "Image", "OpenHours", "Address"},
                null, null, null, null, null);
    }

    public Cursor getRestaurants(String kw) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        return db.query("Restaurant",
                new String[] {"ID", "Name", "Description", "Delivery", "Image", "OpenHours", "Address"},
                "Name LIKE ?", new String[] {"%" + kw + "%"}, null, null, null);
    }
}
