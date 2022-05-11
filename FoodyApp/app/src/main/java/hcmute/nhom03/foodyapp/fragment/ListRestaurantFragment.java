package hcmute.nhom03.foodyapp.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_list_restaurant, container, false);
        Binding();
        Init();
        GetData();
        // Inflate the layout for this fragment
        return view;
    }

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
    }
}