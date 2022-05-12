package hcmute.nhom03.foodyapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.UUID;

import hcmute.nhom03.foodyapp.adapter.CartAdapter;
import hcmute.nhom03.foodyapp.adapter.FoodOrderAdapter;
import hcmute.nhom03.foodyapp.dao.CartDao;
import hcmute.nhom03.foodyapp.dao.OrderDao;
import hcmute.nhom03.foodyapp.dao.UserDao;
import hcmute.nhom03.foodyapp.model.Cart;
import hcmute.nhom03.foodyapp.model.Order;
import hcmute.nhom03.foodyapp.model.User;

public class OrderActivity extends AppCompatActivity {
    EditText edtAddress;
    TextView total, ship, lastTotal;
    Button btnOrder;
    RecyclerView recyclerView;
    UserLocalStore userLocalStore;
    UserDao userDao;
    OrderDao orderDao;
    Order order;

    CartDao cartDao;
    FoodOrderAdapter foodOrderAdapter;
    LinkedList<Cart> carts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_order);

        AnhXa();

        //ds mon an trong gio
        cartDao = new CartDao(getApplicationContext());
        userDao = new UserDao(getApplicationContext());
        carts = cartDao.getCarts();
        foodOrderAdapter = new FoodOrderAdapter(this, carts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(foodOrderAdapter);

        //dia chi
        userLocalStore = new UserLocalStore(this);
        User user = userLocalStore.getLoggedInUser();
        edtAddress.setText(user.getAddress());

        orderDao = new OrderDao(getApplicationContext());

        int tong = cartDao.CalculateCartTotalPrice();
        total.setText("Tổng: " + String.valueOf(tong) + "VND");
        ship.setText("Ship: 20000 VND");
        double total = cartDao.CalculateCartTotalPrice() + 20000;
        lastTotal.setText("Tổng đơn hàng: " + String.valueOf(total) + "VND");

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkOut(total);
            }
        });
    }
    public void AnhXa()
    {
        recyclerView = findViewById(R.id.foodsRecyclerView);
        edtAddress = findViewById(R.id.edtOrderAddress);
        total = findViewById(R.id.total);
        ship = findViewById(R.id.ship);
        lastTotal = findViewById(R.id.lastTotal);
        btnOrder = findViewById(R.id.btnOrder);

    }
    public void checkOut( Double total){
        User user = userLocalStore.getLoggedInUser();
        int userID = user.getId();
        String phone = user.getPhone();

        if (edtAddress.getText().toString().isEmpty()) {
            edtAddress.setError("address can not be empty.");
        }
        if(userDao.checkUserExist(phone)){

            String orderID = UUID.randomUUID().toString();
            order = new Order();
            order.setId(orderID);
            order.setUserID(userID);
            order.setAddress(edtAddress.getText().toString().trim());
            order.setTotal(total);

            orderDao.InsertOrder(order);
            cartDao.addOrderDetail(userID, orderID);
            cartDao.clear(carts);
            // Snack Bar to show success message that record saved successfully
            Toast.makeText(getApplicationContext(),"Checkout successfully.",Toast.LENGTH_LONG).show();
            startActivity(new Intent(OrderActivity.this, MainActivity.class));

        } else {
            // Snack Bar to show error message that record already exists
            Toast.makeText(getApplicationContext(),"Checkout was unsuccessfull..",Toast.LENGTH_LONG).show();
        }
    }
}
