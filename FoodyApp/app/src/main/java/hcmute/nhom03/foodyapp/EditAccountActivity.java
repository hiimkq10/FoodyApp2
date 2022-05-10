package hcmute.nhom03.foodyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rengwuxian.materialedittext.MaterialEditText;

import hcmute.nhom03.foodyapp.dao.UserDao;
import hcmute.nhom03.foodyapp.model.User;

public class EditAccountActivity extends AppCompatActivity {
    MaterialEditText edtEditName, edtEditAddress;
    Button btnUpdate;
    UserLocalStore userLocalStore;
    private UserDao userDao;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit_account);

        AnhXa();
        userDao = new UserDao();
        user = new User();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        userLocalStore = new UserLocalStore(this);
        User user = userLocalStore.getLoggedInUser();
        edtEditName.setText(user.getName());
        edtEditAddress.setText(user.getAddress());

    }

    public void AnhXa(){
        edtEditName = findViewById(R.id.edtEditName);
        edtEditAddress = findViewById(R.id.edtEditAddress);
        btnUpdate = findViewById(R.id.btnUpdate);
    }
    public void Update(){
        userLocalStore = new UserLocalStore(this);
        User user = userLocalStore.getLoggedInUser();
        String phone = user.getPhone();

        if (edtEditName.getText().toString().isEmpty()) {
            edtEditName.setError("Name can not be empty.");
        }
        if (edtEditAddress.getText().toString().isEmpty()) {
            edtEditAddress.setError("Address field can not be empty.");
        }
        if (userDao.checkUserExist(getApplicationContext(),phone)) {

            user.setName(edtEditName.getText().toString().trim());
            user.setAddress(edtEditAddress.getText().toString().trim());

            userDao.updateUser(getApplicationContext(),user);
            saveUser(phone);

            // Snack Bar to show success message that record saved successfully
            Toast.makeText(getApplicationContext(),"Update successfully.",Toast.LENGTH_LONG).show();
            startActivity(new Intent(EditAccountActivity.this, AccountSettingActivity.class));

        } else {
            // Snack Bar to show error message that record already exists
            Toast.makeText(getApplicationContext(),"Update was unsuccessfull..",Toast.LENGTH_LONG).show();
        }

    }
    public void saveUser(String phone){
        User user = new User();
        user = userDao.getUser(getApplicationContext(), phone);
        userLocalStore.storeUserData(user);
        userLocalStore.setUserLoggedIn(true);
    }
}
