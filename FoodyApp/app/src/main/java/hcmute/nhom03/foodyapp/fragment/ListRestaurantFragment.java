package hcmute.nhom03.foodyapp.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;

import hcmute.nhom03.foodyapp.R;
import hcmute.nhom03.foodyapp.adapter.RestaurantAdapter;
import hcmute.nhom03.foodyapp.dao.RestaurantDao;
import hcmute.nhom03.foodyapp.model.Restaurant;
import hcmute.nhom03.foodyapp.utils.GridSpacingItemDecoration;

public class ListRestaurantFragment extends Fragment {
    private View view;
    private RecyclerView recyclerviewRestaurant;
    private RestaurantDao restaurantDao;
    private LinkedList<Restaurant> restaurants;
    private RestaurantAdapter restaurantAdapter;
    long delay = 1000; // 1 seconds after user stops typing
    long last_text_edit = 0;
    private EditText searchEdt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_list_restaurant, container, false);
        Binding();
        Init();
        GetData();

        Handler handler = new Handler();
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
                    GetData();
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private Runnable input_finish_checker = new Runnable() {
        public void run() {
            if (System.currentTimeMillis() > (last_text_edit + delay - 500)) {
                String kw = searchEdt.getText().toString().trim();
                GetData(kw);
            }
        }
    };

    public void Init() {
        restaurantDao = new RestaurantDao(getContext());
        restaurants = new LinkedList<>();
        restaurantAdapter = new RestaurantAdapter(getContext(), restaurants);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerviewRestaurant.setLayoutManager(gridLayoutManager);
        int spanCount = 2;
        int spacing = 12;
        boolean includeEdge = true;
        recyclerviewRestaurant.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        recyclerviewRestaurant.setAdapter(restaurantAdapter);
    }

    public void GetData() {
        restaurants.clear();
        Cursor cursor = restaurantDao.getRestaurants();
        while (cursor.moveToNext()) {
            Restaurant restaurant = new Restaurant(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3) != 0,
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getString(6));
            restaurants.add(restaurant);
        }
        restaurantAdapter.notifyDataSetChanged();
    }

    public void GetData(String kw) {
        restaurants.clear();
        Cursor cursor = restaurantDao.getRestaurants(kw);
        while (cursor.moveToNext()) {
            Restaurant restaurant = new Restaurant(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3) != 0,
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getString(6));
            restaurants.add(restaurant);
        }
        restaurantAdapter.notifyDataSetChanged();
    }

    public void Binding() {
        recyclerviewRestaurant = view.findViewById(R.id.recyclerviewRestaurant);
        searchEdt = view.findViewById(R.id.searchEdt);
    }
}