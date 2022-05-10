package hcmute.nhom03.foodyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;

import hcmute.nhom03.foodyapp.adapter.RestaurantAdapter;
import hcmute.nhom03.foodyapp.dao.FoodDao;
import hcmute.nhom03.foodyapp.dao.RestaurantDao;
import hcmute.nhom03.foodyapp.fragment.ListRestaurantFragment;
import hcmute.nhom03.foodyapp.model.Restaurant;
import hcmute.nhom03.foodyapp.utils.Constants;
import hcmute.nhom03.foodyapp.utils.CreateData;
import hcmute.nhom03.foodyapp.utils.PreferenceManager;

public class RestaurantsActivity extends AppCompatActivity {
    long delay = 1000; // 1 seconds after user stops typing
    long last_text_edit = 0;
    EditText searchEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        PreferenceManager preferenceManager = new PreferenceManager(this);
//        preferenceManager.clear();
//        preferenceManager.putString(Constants.KEY_USER_ID, "1");
//        preferenceManager.putString(Constants.KEY_USER_ID, "2");

//        Uncomment this to create data in database
//        RestaurantDao restaurantDao = new RestaurantDao(this);
//        FoodDao foodDao = new FoodDao(this);
//        CreateData.CreateRestaurantData(restaurantDao);
//        CreateData.CreateFoodData(restaurantDao, foodDao);

        Handler handler = new Handler();

        searchEdt = findViewById(R.id.searchEdt);
        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                handler.removeCallbacks(input_finish_checker);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    last_text_edit = System.currentTimeMillis();
                    handler.postDelayed(input_finish_checker, delay);
                } else {
                    ((ListRestaurantFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container)).GetData();
                }
            }
        });
    }

    private Runnable input_finish_checker = new Runnable() {
        public void run() {
            if (System.currentTimeMillis() > (last_text_edit + delay - 500)) {
                String kw = searchEdt.getText().toString().trim();
                if (kw.equals("")) {
                    ((ListRestaurantFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container)).GetData();
                }
                else {
                    ((ListRestaurantFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container)).GetData(kw);
                }
            }
        }
    };

}