package hcmute.nhom03.foodyapp;

import android.content.Context;
import android.content.SharedPreferences;

import hcmute.nhom03.foodyapp.model.User;

public class UserLocalStore {
    public static final String SP_NAME = "userDetails";

    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString("name", user.getName());
        userLocalDatabaseEditor.putString("phone", user.getPhone());
        userLocalDatabaseEditor.putString("address", user.getAddress());
        userLocalDatabaseEditor.commit();
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }

    public User getLoggedInUser() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }
        String name = userLocalDatabase.getString("name", "");
        String phone = userLocalDatabase.getString("phone", "");
        String address = userLocalDatabase.getString("address", "");

        User user = new User(null,name, phone,null, address );
        return user;
    }
}
