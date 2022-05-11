package hcmute.nhom03.foodyapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import hcmute.nhom03.foodyapp.Database.DatabaseHelper;
import hcmute.nhom03.foodyapp.model.Food;
import hcmute.nhom03.foodyapp.model.Restaurant;

public class FoodDao {
    DatabaseHelper databaseHelper;

    public FoodDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void insertFood(Food food) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues foodValues = new ContentValues();
        foodValues.put("ResID", food.getResID());
        foodValues.put("Name", food.getName());
        foodValues.put("Price", food.getPrice());
        foodValues.put("Image", food.getImage());
        foodValues.put("Description", food.getDescription());
        db.insert("Food", null, foodValues);
    }

    public void updateFood(Food food) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues foodValues = new ContentValues();
        foodValues.put("ResID", food.getResID());
        foodValues.put("Name", food.getName());
        foodValues.put("Price", food.getPrice());
        foodValues.put("Image", food.getImage());
        foodValues.put("Description", food.getDescription());
        db.update("Food", foodValues, "ID = ?", new String[] {String.valueOf(food.getId())});
    }

    public void deleteFood(Food food) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete("Food", "ID = ?", new String[] {String.valueOf(food.getId())});
    }

    public Cursor getFoods(Restaurant restaurant) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        return db.query("Food", new String[] {"ID", "ResID", "Name", "Price", "Image", "Description"},
                "ResID = ?", new String[] {String.valueOf(restaurant.getId())},
                null, null, null);
    }

    public Cursor getFoodByID(int id) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        return db.query("Food",
                new String[] {"ID", "ResID", "Name", "Price", "Image", "Description"},
                "ID = ?", new String[] {String.valueOf(id)},
                null, null, null);
    }
}
