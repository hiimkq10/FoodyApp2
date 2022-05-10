package hcmute.nhom03.foodyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rengwuxian.materialedittext.MaterialEditText;

import hcmute.nhom03.foodyapp.Database.DatabaseHelper;
import hcmute.nhom03.foodyapp.dao.UserDao;
import hcmute.nhom03.foodyapp.model.User;

public class RegisterActivity extends AppCompatActivity {

    MaterialEditText edtName, edtPhone, edtPass, edtAddress;
    Button btnReg;

    private UserDao userDao;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        AnhXa();

        userDao = new UserDao();
        user = new User();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Regis();
            }
        });
    }
    private void AnhXa(){
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtPass = findViewById(R.id.edtPass);
        edtAddress = findViewById(R.id.edtAddress);
        btnReg = findViewById(R.id.btnReg);
    }
    private void Regis() {
        if (edtName.getText().toString().isEmpty()) {
            edtName.setError("Name can not be empty.");
        }
        if (edtPhone.getText().toString().isEmpty()) {
            edtPhone.setError("Phone field can not be empty.");
        }
        if (edtPass.getText().toString().isEmpty()) {
            edtPass.setError("Password can not be empty.");
        }
        if (edtAddress.getText().toString().isEmpty()) {
            edtAddress.setError("Address can not be empty.");
        }

        if (!userDao.checkUserExist(getApplicationContext(),edtPhone.getText().toString().trim())) {

            user.setName(edtName.getText().toString().trim());
            user.setPhone(edtPhone.getText().toString().trim());
            user.setPass(edtPass.getText().toString().trim());
            user.setAddress(edtAddress.getText().toString().trim());

            userDao.addUser(getApplicationContext(),user);

            // Snack Bar to show success message that record saved successfully
            Toast.makeText(getApplicationContext(),"Registered successfully.",Toast.LENGTH_LONG).show();
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

        } else {
            // Snack Bar to show error message that record already exists
            Toast.makeText(getApplicationContext(),"Registration was unsuccessfull..",Toast.LENGTH_LONG).show();
        }


    }
}
