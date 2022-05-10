package hcmute.nhom03.foodyapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;

import hcmute.nhom03.foodyapp.Database.DatabaseHelper;
import hcmute.nhom03.foodyapp.model.Cart;
import hcmute.nhom03.foodyapp.model.Food;
import hcmute.nhom03.foodyapp.utils.Constants;
import hcmute.nhom03.foodyapp.utils.PreferenceManager;

public class CartDao {
    DatabaseHelper databaseHelper;
    PreferenceManager preferenceManager;
    FoodDao foodDao;

    public CartDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
        preferenceManager = new PreferenceManager(context);
        foodDao = new FoodDao(context);
    }

    public void insertCart(Cart cart) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues cartValues = new ContentValues();
        cartValues.put("CustumerID", cart.getUserID());
        cartValues.put("FoodID", cart.getFood().getId());
        cartValues.put("Quantity", cart.getQuantity());
        db.insert("Cart", null, cartValues);
    }

    public void updateCart(Cart cart) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues cartValues = new ContentValues();
        cartValues.put("CustumerID", cart.getUserID());
        cartValues.put("FoodID", cart.getFood().getId());
        cartValues.put("Quantity", cart.getQuantity());
        db.update("Cart", cartValues, "ID = ?", new String[] {String.valueOf(cart.getId())});
    }

    public void deleteCart(Cart cart) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete("Cart", "ID = ?", new String[] {String.valueOf(cart.getId())});
    }

    public LinkedList<Cart> getCarts() {
        LinkedList<Cart> carts = new LinkedList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        int userID = Integer.parseInt(preferenceManager.getString(Constants.KEY_USER_ID));
        Cursor cartCursor = db.query("Cart",
                new String[] {"ID", "CustumerID", "FoodID", "Quantity"},
                "CustumerID = ?",
                new String[] {String.valueOf(userID)},
                null, null, null);
        while (cartCursor.moveToNext()) {
            Cart cart = new Cart();
            cart.setId(cartCursor.getInt(0));
            cart.setUserID(userID);
            cart.setQuantity(cartCursor.getInt(3));
            Cursor foodCursor = foodDao.getFoodByID(cartCursor.getInt(2));
            if (foodCursor.moveToFirst()) {
                Food food = new Food(foodCursor.getInt(0), foodCursor.getInt(1), foodCursor.getString(2), foodCursor.getInt(3), foodCursor.getInt(4), foodCursor.getString(5), false);
                cart.setFood(food);
            }
            carts.add(cart);
        }
        return carts;
    }

    public int countCartItems() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String userID = preferenceManager.getString(Constants.KEY_USER_ID);
        Cursor cursor = db.query("Cart", new String[] {"SUM(Quantity) AS Count"},
                "CustumerID = ?", new String[] {userID},"CustumerID", null, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }

    public int CalculateCartTotalPrice() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        LinkedList<Cart> carts = getCarts();
        int totalPrice = 0;
        for (Cart c : carts) {
            totalPrice = totalPrice + c.getQuantity() * c.getFood().getPrice();
        }
        return totalPrice;
    }

    public Cart getCartByFood(Food food) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String userID = preferenceManager.getString(Constants.KEY_USER_ID);
        Cursor cursor = db.query("Cart",
                new String[] {"ID", "CustumerID", "FoodID", "Quantity"},
                "CustumerID = ? AND FoodID = ?",
                new String[] {userID, String.valueOf(food.getId())},
                null, null, null);
        if (cursor.moveToFirst()) {
            return new Cart(cursor.getInt(0), cursor.getInt(1), food, cursor.getInt(3));
        }
        else {
            return null;
        }
    }
}
