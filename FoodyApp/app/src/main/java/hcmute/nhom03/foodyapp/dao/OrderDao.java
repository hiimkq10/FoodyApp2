package hcmute.nhom03.foodyapp.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import hcmute.nhom03.foodyapp.Database.DatabaseHelper;
import hcmute.nhom03.foodyapp.UserLocalStore;
import hcmute.nhom03.foodyapp.model.Order;
import hcmute.nhom03.foodyapp.model.User;

public class OrderDao {
    DatabaseHelper helper;
    UserLocalStore userLocalStore;
    public OrderDao(Context context) {
        helper = new DatabaseHelper(context);
        userLocalStore = new UserLocalStore(context);
    }
    public void InsertOrder( Order order) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("OrderID", order.getId());
        values.put("UserID", order.getUserID());
        values.put("Address", order.getAddress());
        values.put("Total", order.getTotal());

        db.insert("Orders", null, values);
        db.close();
    }

    public LinkedList<Order> getOrders() {
        LinkedList<Order> orders = new LinkedList<>();
        SQLiteDatabase db = helper.getReadableDatabase();

        User user = userLocalStore.getLoggedInUser();
        int userID = Integer.parseInt(user.getId().toString());
        Cursor Cursor = db.query("Orders",
                new String[] {"ID", "Address", "Total"},
                "UserID = ?",
                new String[] {String.valueOf(userID)},
                null, null, null);
        while (Cursor.moveToNext()) {
            Order order = new Order();
            order.setId(Cursor.getString(0));
            order.setUserID(userID);
            order.setAddress(Cursor.getString(2));
            order.setTotal(Cursor.getDouble(3));

            orders.add(order);
        }
        return orders;
    }
}
