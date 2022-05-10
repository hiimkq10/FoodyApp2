package hcmute.nhom03.foodyapp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import hcmute.nhom03.foodyapp.model.Restaurant;

public class RestaurantDao {

    public void insertRestaurant(SQLiteDatabase db, Restaurant restaurant) {
        ContentValues restaurantValues = new ContentValues();
        restaurantValues.put("Name", restaurant.getName());
        restaurantValues.put("Description", restaurant.getDescription());
        restaurantValues.put("Delivery", restaurant.getDelivery());
        restaurantValues.put("Image", restaurant.getImage());
        restaurantValues.put("OpenHours", restaurant.getOpenHours());
        restaurantValues.put("Address", restaurant.getAddress());
        db.insert("Restaurant", null, restaurantValues);
    }

    public void updateRestaurant(SQLiteDatabase db, Restaurant restaurant) {
        ContentValues restaurantValues = new ContentValues();
        restaurantValues.put("Name", restaurant.getName());
        restaurantValues.put("Description", restaurant.getDescription());
        restaurantValues.put("Delivery", restaurant.getDelivery());
        restaurantValues.put("Image", restaurant.getImage());
        restaurantValues.put("OpenHours", restaurant.getOpenHours());
        restaurantValues.put("Address", restaurant.getAddress());
        db.update("Restaurant", restaurantValues, "ID = ?", new String[] {String.valueOf(restaurant.getId())});
    }

    public void deleteRestaurant(SQLiteDatabase db, Restaurant restaurant) {
        db.delete("Restaurant", "ID = ?", new String[] {String.valueOf(restaurant.getId())});
    }

    public Cursor getRestaurants(SQLiteDatabase db) {
        return db.query("Restaurant",
                new String[] {"ID", "Name", "Description", "Delivery", "Image", "OpenHours", "Address"},
                null, null, null, null, null);
    }
}
