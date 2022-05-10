package hcmute.nhom03.foodyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;

import hcmute.nhom03.foodyapp.adapter.CartAdapter;
import hcmute.nhom03.foodyapp.dao.CartDao;
import hcmute.nhom03.foodyapp.model.Cart;
import hcmute.nhom03.foodyapp.model.Restaurant;

public class CartActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerViewCart;
    private TextView badge, totalPrice;
    private Button orderButton;
    private CartDao cartDao;
    private CartAdapter cartAdapter;
    private LinkedList<Cart> carts;
    private Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Binding();
        SetUpToolBar();
        Init();
        SetListener();
        GetData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        UpdateCartBadge();
    }

    public void UpdateCartBadge() {
        int count = cartDao.countCartItems();
        int sum = cartDao.CalculateCartTotalPrice();
        badge.setText(String.valueOf(count));
        totalPrice.setText(String.valueOf(sum) + "đ");
    }

    public void GetData() {
        carts.addAll(cartDao.getCarts());
        cartAdapter.notifyDataSetChanged();
    }

    public void Init() {
        cartDao = new CartDao(getApplicationContext());
        carts = new LinkedList<>();
        cartAdapter = new CartAdapter(CartActivity.this, carts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewCart.setLayoutManager(linearLayoutManager);
        recyclerViewCart.setAdapter(cartAdapter);
    }

    public void SetUpToolBar() {
        Intent intent = getIntent();
        this.restaurant = (Restaurant) intent.getSerializableExtra("Restaurant");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cart");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(CartActivity.this, RestaurantInfoActivity.class);
                intent1.putExtra("Restaurant", restaurant);
                startActivity(intent1);
            }
        });
    }

    public void SetListener() {
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Binding() {
        toolbar = findViewById(R.id.toolbar);
        recyclerViewCart = findViewById(R.id.recyclerviewCart);
        badge = findViewById(R.id.badge);
        totalPrice = findViewById(R.id.totalPrice);
        orderButton = findViewById(R.id.orderButton);
    }
}