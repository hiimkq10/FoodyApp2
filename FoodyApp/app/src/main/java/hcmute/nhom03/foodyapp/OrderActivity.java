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
    CartAdapter cartAdapter;
    LinkedList<Cart> carts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_order);

        AnhXa();

        //ds mon an trong gio
        cartDao = new CartDao(getApplicationContext());
        carts = new LinkedList<>();
        cartAdapter = new CartAdapter(this, carts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cartAdapter);

        //dia chi
        userLocalStore = new UserLocalStore(this);
        User user = userLocalStore.getLoggedInUser();
        edtAddress.setText(user.getAddress());

        orderDao = new OrderDao();
        userDao = new UserDao();
        User u = userDao.getUser(getApplicationContext(), user.getPhone());

        int tong = cartDao.CalculateCartTotalPrice();
        total.setText(String.valueOf(tong) + "VND");
        ship.setText("20000 VND");
        lastTotal.setText(cartDao.CalculateCartTotalPrice() + 20000 + "VND");

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
    public void checkOut(Integer userId, String phone, Double total){

        if (edtAddress.getText().toString().isEmpty()) {
            edtAddress.setError("address can not be empty.");
        }
        if(userDao.checkUserExist(getApplicationContext(), phone)){

            String orderID = UUID.randomUUID().toString();
            order.setId(orderID);
            order.setUserID(userId);
            order.setAddress(edtAddress.getText().toString().trim());
            order.setTotal(total);

            orderDao.InsertOrder(getApplicationContext(),order);
            cartDao.addOrderDetail(getApplicationContext(),userId, orderID);

            // Snack Bar to show success message that record saved successfully
            Toast.makeText(getApplicationContext(),"Checkout successfully.",Toast.LENGTH_LONG).show();
            startActivity(new Intent(OrderActivity.this, AccountSettingActivity.class));

        } else {
            // Snack Bar to show error message that record already exists
            Toast.makeText(getApplicationContext(),"Checkout was unsuccessfull..",Toast.LENGTH_LONG).show();
        }
    }
}
