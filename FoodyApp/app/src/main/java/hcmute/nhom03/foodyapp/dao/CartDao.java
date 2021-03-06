package hcmute.nhom03.foodyapp.dao;

import android.annotation.SuppressLint;
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
        cartValues.put("UserID", cart.getUserID());
        cartValues.put("FoodID", cart.getFood().getId());
        cartValues.put("Quantity", cart.getQuantity());
        db.insert("Cart", null, cartValues);
    }

    public void updateCart(Cart cart) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues cartValues = new ContentValues();
        cartValues.put("UserID", cart.getUserID());
        cartValues.put("FoodID", cart.getFood().getId());
        cartValues.put("Quantity", cart.getQuantity());
        db.update("Cart", cartValues, "ID = ?", new String[] {String.valueOf(cart.getId())});
    }

    public void deleteCart(Cart cart) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete("Cart", "ID = ?", new String[] {String.valueOf(cart.getId())});
    }

    public LinkedList<Cart> getCarts() {
        int restaurantID = Integer.parseInt(preferenceManager.getString(Constants.KEY_Restaurant_ID));
        LinkedList<Cart> carts = new LinkedList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        int userID = Integer.parseInt(preferenceManager.getString(Constants.KEY_USER_ID));
        String query = "SELECT Cart.ID, Cart.UserID, Cart.FoodID, Cart.Quantity FROM Cart INNER JOIN Food ON Cart.FoodID = Food.ID WHERE Food.ResID = ? AND Cart.UserID = ?";
//        Cursor cartCursor = db.query("Cart",
//                new String[] {"ID", "UserID", "FoodID", "Quantity"},
//                "UserID = ?",
//                new String[] {String.valueOf(userID)},
//                null, null, null);
        Cursor cartCursor = db.rawQuery(query, new String[] {String.valueOf(restaurantID), String.valueOf(userID)});
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
        int restaurantID = Integer.parseInt(preferenceManager.getString(Constants.KEY_Restaurant_ID));
        String query = "SELECT Sum(Cart.Quantity) AS Count FROM Cart INNER JOIN Food ON Cart.FoodID = Food.ID WHERE Food.ResID = ? AND Cart.UserID = ? GROUP BY Cart.UserID";
        Cursor cursor = db.rawQuery(query, new String[] {String.valueOf(restaurantID), userID});
//        Cursor cursor = db.query("Cart", new String[] {"SUM(Quantity) AS Count"},
//                "UserID = ?", new String[] {userID},"UserID", null, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }

    public int CalculateCartTotalPrice() {
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
                new String[] {"ID", "UserID", "FoodID", "Quantity"},
                "UserID = ? AND FoodID = ?",
                new String[] {userID, String.valueOf(food.getId())},
                null, null, null);
        if (cursor.moveToFirst()) {
            return new Cart(cursor.getInt(0), cursor.getInt(1), food, cursor.getInt(3));
        }
        else {
            return null;
        }
    }

    public void clear(LinkedList<Cart> carts) {
        for (Cart c : carts) {
            deleteCart(c);
        }
    }

    @SuppressLint("Range")
    public void addOrderDetail( Integer userID, String orderID) {
        int restaurantID = Integer.parseInt(preferenceManager.getString(Constants.KEY_Restaurant_ID));
//        String[] columns = {
//                "FoodID", "Quantity"
//        };
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

//        Cursor cursor = db.query("Cart", columns, "UserID = ?", new String[]{String.valueOf(userID)},       //columns for the WHERE clause
//                null,        //The values for the WHERE clause
//                null,       //group the rows
//                null,       //filter by row groups
//                null); //The sort order
        String query = "SELECT Cart.ID, Cart.UserID, Cart.FoodID, Cart.Quantity FROM Cart INNER JOIN Food ON Cart.FoodID = Food.ID WHERE Food.ResID = ? AND Cart.UserID = ?";
        Cursor cursor = db.rawQuery(query, new String[] {String.valueOf(restaurantID), String.valueOf(userID)});
        if (cursor.moveToFirst()) {
            do {
                ContentValues values = new ContentValues();
                values.put("OrderID", orderID);
                values.put("FoodID", Integer.parseInt(cursor.getString(cursor.getColumnIndex("FoodID"))));
                values.put("Quantity", Integer.parseInt(cursor.getString(cursor.getColumnIndex("Quantity"))));

                db.insert("OrderDetail", null, values);
            } while (cursor.moveToNext());
        }
//        db.delete("Cart","UserID = ?", new String[]{String.valueOf(userID)});
    }
}
