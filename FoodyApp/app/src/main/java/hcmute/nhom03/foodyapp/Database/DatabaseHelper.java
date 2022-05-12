package hcmute.nhom03.foodyapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import hcmute.nhom03.foodyapp.utils.CreateData;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Foody.db";

    /**
     * table Restaurant - ID: int autoincrement primary key, Name: string, Description: string,
     * Delivery: boolean, Image: int, OpenHours: string, Address: string
     */
    public static final String CREATE_TABLE_RESTAURANT =
            "CREATE TABLE Restaurant (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "Name VARCHAR(255) NOT NULL," +
                    "Description VARCHAR(200), " +
                    "Delivery BOOLEAN," +
                    "Image INTEGER," +
                    "OpenHours VARCHAR(200)," +
                    "Address VARCHAR(200)" +
                    ")";

    /**
     * table Food - ID: int autoincrement primary key, ResID: int foreign key Restaurant(ID),
     * Name: string, Price: int. Image: int, Description: string
     */
    public static final String CREATE_TABLE_FOOD=
            "CREATE TABLE Food (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "ResID INTEGER," +
                    "Name VARCHAR(200), " +
                    "Price INTEGER," +
                    "Image INTEGER," +
                    "Description VARCHAR(200),"+
                    "FOREIGN KEY(ResID) REFERENCES Restaurant(ID)" +
                    ")";

    /**
     * User: ID: int autoincrement primary key, Name: string, Phone: string, Pass: string, Address: String
     */
    public static final String CREATE_TABLE_User=
            "CREATE TABLE User (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "Name VARCHAR(200), " +
                    "Phone VARCHAR(200)," +
                    "Pass VARCHAR(200)," +
                    "Address VARCHAR(200)"+
                    ")";

    /**
     * Cart - ID: int autoincrement primary key, CustumerID: int foreign key User(ID),
     * FoodID: int foreign key Food(ID), Quantity: int
     */
    public static final String CREATE_TABLE_CART=
            "CREATE TABLE Cart (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "UserID INTEGER," +
                    "FoodID INTEGER," +
                    "Quantity INTEGER," +
                    "FOREIGN KEY(FoodID) REFERENCES Food(ID)," +
                    "FOREIGN KEY(UserID) REFERENCES User(ID)" +
                    ")";

    /**
     * Orders: ID: string (UUID) primary key, UserID: int foreign key User(ID),  Address: string, Total: double
     */
    public static final String CREATE_TABLE_ORDER=
            "CREATE TABLE Orders (" +
                    "ID UUID PRIMARY KEY NOT NULL, " +
                    "UserID INTEGER," +
                    "Address VARCHAR(200)," +
                    "Total DOUBLE," +
                    "FOREIGN KEY(UserID) REFERENCES User(ID)" +
                    ")";

    /**
     * OrderDetail: ID: int autoincrement primary key, OrderID: string foreign key Order(ID),
     * FoodID: int foreign key Food(ID), Quantity: int
     */
    public static final String CREATE_TABLE_ORDER_DETAIL=
            "CREATE TABLE OrderDetail (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "OrderID VARCHAR(200)," +
                    "FoodID INTEGER," +
                    "Quantity INTEGER," +
                    "FOREIGN KEY(OrderID) REFERENCES Orders(ID)," +
                    "FOREIGN KEY(FoodID) REFERENCES Food(ID)"+
                    ")";

    /**
     * value for update database
     */
    public static final int DATA_VERSION = 1;

    /**
     * Sqlite database
     */
    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATA_VERSION);
    }

    /**
     * create db when app start, and only call when database don't create
     * When database created, it will not call
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_RESTAURANT);
            db.execSQL(CREATE_TABLE_FOOD);
            db.execSQL(CREATE_TABLE_User);
            db.execSQL(CREATE_TABLE_CART);
            db.execSQL(CREATE_TABLE_ORDER);
            db.execSQL(CREATE_TABLE_ORDER_DETAIL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * call when change DATA_VERSION
     * help we update database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * open database
     */
    public void open() {
        try {
            db = getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * close database
     */
    public void close() {
        if (db != null && db.isOpen()) {
            try {
                db.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Truy van khong ket qua: CRUD
    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //Truy van cos ket qua: Select
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

}
