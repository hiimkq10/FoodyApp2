package hcmute.nhom03.foodyapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import hcmute.nhom03.foodyapp.Database.DatabaseHelper;
import hcmute.nhom03.foodyapp.UserLocalStore;
import hcmute.nhom03.foodyapp.model.User;

public class UserDao {
    DatabaseHelper helper;
    UserLocalStore userLocalStore;
    public UserDao(Context context) {
        helper = new DatabaseHelper(context);
        userLocalStore = new UserLocalStore(context);
    }
    public void addUser(User user) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Name", user.getName());
        values.put("Phone", user.getPhone());
        values.put("Pass", user.getPass());
        values.put("Address", user.getAddress());

        db.insert("User", null, values);
        db.close();
    }
    public boolean UpdateUserPass( String phone, String pass) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("UPDATE "+ "User" +" SET Pass = " + "'" + pass + "'" + "WHERE Phone = " + "'" + phone + "'");
        return true;
    }
    public void updateUser( User user) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Name", user.getName());
        values.put("Address", user.getAddress());

        // updating row
        db.update("User", values, "Phone = ?",
                new String[]{String.valueOf(user.getPhone())});
        db.close();
    }
    public User getUser( String phone) {
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.query("User", null, "Phone = ?", new String[] { String.valueOf(phone) },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        User user = new User(cursor.getInt(0) , cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return user;
    }

    public boolean checkUser( String phone, String password) {

        // array of columns to fetch
        String[] columns = {
                "ID"
        };
        SQLiteDatabase db = helper.getWritableDatabase();
        // selection criteria
        String selection = "Phone = ?" + " AND " + "Pass = ?";

        // selection arguments
        String[] selectionArgs = {phone, password};

        // query user table with conditions
        Cursor cursor = db.query("User", columns, selection, selectionArgs, null, null, null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
    public boolean checkUserExist(String phone) {

        // array of columns to fetch
        String[] columns = {
                "ID"
        };
        SQLiteDatabase db = helper.getWritableDatabase();

        // selection criteria
        String selection = "Phone = ?";

        // selection argument
        String[] selectionArgs = {phone};

        // query user table with condition
        Cursor cursor = db.query("User", //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
