package hcmute.nhom03.foodyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import hcmute.nhom03.foodyapp.dao.CartDao;
import hcmute.nhom03.foodyapp.model.Cart;
import hcmute.nhom03.foodyapp.model.Food;
import hcmute.nhom03.foodyapp.model.Restaurant;
import hcmute.nhom03.foodyapp.utils.Constants;
import hcmute.nhom03.foodyapp.utils.PreferenceManager;

public class FoodInfoActivity extends AppCompatActivity {
    private ImageView foodImage;
    private TextView foodName, foodPrice, foodDescription, quantity;
    private Button addToCartButton;
    private ImageButton addButton, removeButton;
    private Toolbar toolbar;
    private Food food;
    private RelativeLayout relativeLayout;
    private PreferenceManager preferenceManager;
    private CartDao cartDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);

        Binding();
        Init();

        SetUpToolbar();
        SetListener();
        LoadFoodData();
    }

    public void SetListener() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl = Integer.parseInt(quantity.getText().toString().trim());
                sl = sl + 1;
                quantity.setText(String.valueOf(sl));
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl = Integer.parseInt(quantity.getText().toString().trim());
                if (sl > 1) {
                    sl = sl - 1;
                    quantity.setText(String.valueOf(sl));
                }
            }
        });

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl = Integer.parseInt(quantity.getText().toString().trim());
                int userID = Integer.parseInt(preferenceManager.getString(Constants.KEY_USER_ID));
                Cart cart = cartDao.getCartByFood(food);
                if (cart != null) {
                    cart.setQuantity(cart.getQuantity() + sl);
                    cartDao.updateCart(cart);
                }
                else {
                    cart = new Cart(userID, food, sl);
                    cartDao.insertCart(cart);
                }
                Toast.makeText(FoodInfoActivity.this, "Đã thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void LoadFoodData() {
        Intent intent = getIntent();
        this.food = (Food) intent.getSerializableExtra("Food");
        Glide.with(FoodInfoActivity.this).load(food.getImage()).into(foodImage);
        foodName.setText(food.getName());
        foodPrice.setText("Giá: " + String.valueOf(food.getPrice()) + "đ");
        foodDescription.setText("Mô tả: " + String.valueOf(food.getDescription()));
    }

    public void SetUpToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void Init() {
        cartDao = new CartDao(FoodInfoActivity.this);
    }

    public void Binding() {
        foodImage = findViewById(R.id.foodImage);
        foodName = findViewById(R.id.foodName);
        foodPrice = findViewById(R.id.foodPrice);
        foodDescription = findViewById(R.id.foodDescription);
        quantity = findViewById(R.id.quantity);
        addToCartButton = findViewById(R.id.addToCartButton);
        addButton = findViewById(R.id.addButton);
        removeButton = findViewById(R.id.removeButton);
        toolbar = findViewById(R.id.toolbar);
        relativeLayout = findViewById(R.id.relativeLayout);

        preferenceManager = new PreferenceManager(getApplicationContext());
        if (preferenceManager.getString(Constants.KEY_USER_ID) == null) {
            relativeLayout.setVisibility(View.GONE);
        }
    }
}