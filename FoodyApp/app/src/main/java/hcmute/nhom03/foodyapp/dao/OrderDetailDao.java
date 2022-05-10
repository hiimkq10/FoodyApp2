package hcmute.nhom03.foodyapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import hcmute.nhom03.foodyapp.Database.DatabaseHelper;
import hcmute.nhom03.foodyapp.model.OrderDetail;

public class OrderDetailDao {
    public void InsertOrderDetail(Context context, OrderDetail orderDetail) {
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("OrderID", orderDetail.getOrderID());
        values.put("FoodID", orderDetail.getFoodID());
        values.put("Quantity", orderDetail.getQuantity());

        db.insert("OrderDetail", null, values);
        db.close();
    }
}
