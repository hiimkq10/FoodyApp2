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
import androidx.recyclerview.widget.RecyclerView;

import java.util.UUID;

import hcmute.nhom03.foodyapp.dao.OrderDao;
import hcmute.nhom03.foodyapp.dao.UserDao;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_order);

        AnhXa();

        userLocalStore = new UserLocalStore(this);
        User user = userLocalStore.getLoggedInUser();
        edtAddress.setText(user.getAddress());

        orderDao = new OrderDao();
        userDao = new UserDao();
        User u = userDao.getUser(getApplicationContext(), user.getPhone());

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
    public void checkOut(Integer userId, String phone){

        if (edtAddress.getText().toString().isEmpty()) {
            edtAddress.setError("address can not be empty.");
        }
        if(userDao.checkUserExist(getApplicationContext(), phone)){
            String id = UUID.randomUUID().toString();
            order.setId(id);
            order.setUserID(userId);
            order.setAddress(edtAddress.getText().toString().trim());
            //order.setTotal();

            orderDao.InsertOrder(getApplicationContext(),order);

            // Snack Bar to show success message that record saved successfully
            Toast.makeText(getApplicationContext(),"Checkout successfully.",Toast.LENGTH_LONG).show();
            startActivity(new Intent(OrderActivity.this, AccountSettingActivity.class));

        } else {
            // Snack Bar to show error message that record already exists
            Toast.makeText(getApplicationContext(),"Checkout was unsuccessfull..",Toast.LENGTH_LONG).show();
        }
    }
}
