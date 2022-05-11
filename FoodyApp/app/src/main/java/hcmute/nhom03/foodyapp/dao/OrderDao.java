package hcmute.nhom03.foodyapp.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.nhom03.foodyapp.Database.DatabaseHelper;
import hcmute.nhom03.foodyapp.model.Order;

public class OrderDao {
    public void InsertOrder(Context context, Order order) {
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("OrderID", order.getId());
        values.put("UserID", order.getUserID());
        values.put("Address", order.getAddress());
        values.put("Total", order.getTotal());

        db.insert("Orders", null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<Order> getAllUser(Context context, String userID) {
        // array of columns to fetch
        String[] columns = {
                "OrderID","UserID","Address","Total"
        };

        List<Order> orderList = new ArrayList<Order>();

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.query("Orders", //Table to query
                columns,    //columns to return
                "UserID = ?",  new String[] { String.valueOf(userID) },       //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Order order = new Order();
                order.setId(cursor.getString(cursor.getColumnIndex("ID")));
                order.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
                order.setTotal(cursor.getDouble(cursor.getColumnIndex("Total")));
                // Adding user record to list
                orderList.add(order);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return orderList;
    }
}
