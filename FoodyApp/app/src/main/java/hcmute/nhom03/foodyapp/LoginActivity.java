package hcmute.nhom03.foodyapp;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.nhom03.foodyapp.Database.DatabaseHelper;
import hcmute.nhom03.foodyapp.dao.UserDao;
import hcmute.nhom03.foodyapp.model.User;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtPhone, edtPass;
    TextView forgetPass;
    UserDao userDao;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        AnhXa();

        userDao = new UserDao();
        userLocalStore = new UserLocalStore(getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, FogotPassActivity.class);
                startActivity(intent);
            }
        });
    }
    public void AnhXa(){
        btnLogin = findViewById(R.id.btnLogin);
        edtPhone = findViewById(R.id.edtPhoneLogin);
        edtPass = findViewById(R.id.edtPassLogin);
        forgetPass = findViewById(R.id.forgetPass);
    }
    private void login() {
        if (edtPhone.getText().toString().isEmpty()) {
            edtPhone.setError("Username can not be empty.");
        }
        if (edtPass.getText().toString().isEmpty()) {
            edtPass.setError("Password field can not be empty.");
        }
        if (userDao.checkUser(getApplicationContext(),edtPhone.getText().toString()
                , edtPass.getText().toString())) {
            //Save
            User user = new User();
            user = userDao.getUser(getApplicationContext(), edtPhone.getText().toString());
            userLocalStore.storeUserData(user);
            userLocalStore.setUserLoggedIn(true);

            //End
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);


        } else {
            // toast to show success message that record is wrong
            Toast.makeText(getApplicationContext(),"Username and password do not match.",Toast.LENGTH_LONG).show();
        }
    }
}