package hcmute.nhom03.foodyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.LinkedList;

import hcmute.nhom03.foodyapp.adapter.FoodAdapter;
import hcmute.nhom03.foodyapp.dao.CartDao;
import hcmute.nhom03.foodyapp.fragment.ListFoodFragment;
import hcmute.nhom03.foodyapp.model.Food;
import hcmute.nhom03.foodyapp.model.Restaurant;
import hcmute.nhom03.foodyapp.utils.Constants;
import hcmute.nhom03.foodyapp.utils.PreferenceManager;

public class RestaurantInfoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView restaurantImage;
    private TextView restaurantName, restaurantOpenHours, restaurantAddress, restaurantDescription, badge, totalPrice;
    private RelativeLayout cartLayout;
    private Button orderButton;
    Restaurant restaurant;
    private CartDao cartDao;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_info);
        Binding();
        SetUpToolBar();
        Init();
        SetListeners();
        LoadRestaurantInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ((ListFoodFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_container)).GetData(restaurant);
        UpdateCartBadge();
    }

    public void UpdateCartBadge() {
        if (preferenceManager.getString(Constants.KEY_USER_ID) != null) {
            int count = cartDao.countCartItems();
            int sum = cartDao.CalculateCartTotalPrice();
            badge.setText(String.valueOf(count));
            totalPrice.setText(String.valueOf(sum) + "đ");
        }
    }

    public void LoadRestaurantInfo() {
        Intent intent = getIntent();
        this.restaurant = (Restaurant) intent.getSerializableExtra("Restaurant");
        getSupportActionBar().setTitle(restaurant.getName());
        Glide.with(getApplicationContext()).load(restaurant.getImage()).into(restaurantImage);
        restaurantName.setText(restaurant.getName());
        restaurantOpenHours.setText(restaurant.getOpenHours());
        restaurantAddress.setText(restaurant.getAddress());
        restaurantDescription.setText(restaurant.getDescription());
        if (!this.restaurant.getDelivery()){
            cartLayout.setVisibility(View.GONE);
        }
        preferenceManager.putString(Constants.KEY_Restaurant_ID, String.valueOf(restaurant.getId()));
    }

    public void SetListeners() {
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferenceManager.getString(Constants.KEY_USER_ID) != null) {
                    Intent intent = new Intent(RestaurantInfoActivity.this, CartActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RestaurantInfoActivity.this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Init() {
        cartDao = new CartDao(getApplicationContext());
        preferenceManager = new PreferenceManager(getApplicationContext());
    }

    public void SetUpToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void Binding() {
        toolbar = findViewById(R.id.toolbar);
        restaurantImage = findViewById(R.id.restaurantImage);
        restaurantName = findViewById(R.id.restaurantName);
        restaurantOpenHours = findViewById(R.id.restaurantOpenHours);
        restaurantAddress = findViewById(R.id.restaurantAddress);
        restaurantDescription = findViewById(R.id.restaurantDescription);
        cartLayout = findViewById(R.id.cartLayout);
        badge = findViewById(R.id.badge);
        totalPrice = findViewById(R.id.totalPrice);
        orderButton = findViewById(R.id.order);
    }
}