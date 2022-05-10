package hcmute.nhom03.foodyapp.fragment;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.LinkedList;

import hcmute.nhom03.foodyapp.R;
import hcmute.nhom03.foodyapp.RestaurantInfoActivity;
import hcmute.nhom03.foodyapp.adapter.FoodAdapter;
import hcmute.nhom03.foodyapp.dao.FoodDao;
import hcmute.nhom03.foodyapp.model.Food;
import hcmute.nhom03.foodyapp.model.Restaurant;

public class ListFoodFragment extends Fragment {
    private View view;
    private LinkedList<Food> foods;
    private FoodAdapter foodAdapter;
    private RecyclerView recyclerViewFoods;
    private FoodDao foodDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_list_food, container, false);
        Binding();
        Init();
        // Inflate the layout for this fragment
        return view;
    }

    public void GetData(Restaurant restaurant) {
        Cursor cursor = foodDao.getFoods(restaurant);
        while (cursor.moveToNext()) {
            Food food = new Food(cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    restaurant.getDelivery());
            foods.add(food);
        }
        foodAdapter.notifyDataSetChanged();

        if (restaurant.getDelivery()) {

        }
    }

    public void Init() {
        foodDao = new FoodDao(getContext());
        foods = new LinkedList<>();
        foodAdapter = new FoodAdapter(getContext(), foods);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewFoods.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewFoods.getContext(), linearLayoutManager.getOrientation());
        Drawable mDivider = ContextCompat.getDrawable(getContext(), R.drawable.divider);
        dividerItemDecoration.setDrawable(mDivider);
        recyclerViewFoods.addItemDecoration(dividerItemDecoration);
        recyclerViewFoods.setAdapter(foodAdapter);
    }

    public void Binding() {
        recyclerViewFoods = view.findViewById(R.id.recyclerviewFoods);
    }
}