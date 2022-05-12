package hcmute.nhom03.foodyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rengwuxian.materialedittext.MaterialEditText;

import hcmute.nhom03.foodyapp.Database.DatabaseHelper;
import hcmute.nhom03.foodyapp.dao.UserDao;
import hcmute.nhom03.foodyapp.model.User;

public class ForgotPassActivity extends AppCompatActivity {

    MaterialEditText edtPhone, edtNewPass, edtReNewPass;
    Button btnChange, btnClose;

    private DatabaseHelper databaseHelper;
    private User user;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fogot_pass);

        AnhXa();

        databaseHelper = new DatabaseHelper(getApplicationContext());
        user = new User();
        userDao = new UserDao(getApplicationContext());

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdatePass();
            }
        });
    }
    public void AnhXa (){
        edtPhone = findViewById(R.id.edtPhoneForget);
        edtNewPass = findViewById(R.id.edtNewPass);
        edtReNewPass = findViewById(R.id.edtReNewPass);
        btnChange = findViewById(R.id.btnChange);
        btnClose = findViewById(R.id.btnClose);
    }

    public void UpdatePass(){
        if (edtPhone.getText().toString().isEmpty()) {
            edtPhone.setError("Phone can not be empty.");
        }
        if (edtNewPass.getText().toString().isEmpty()) {
            edtNewPass.setError("Password can not be empty.");
        }
        if (!edtNewPass.getText().toString().equals(edtReNewPass.getText().toString())) {
            edtReNewPass.setError("Password don't match.");
        }
        if(userDao.checkUserExist(edtPhone.getText().toString())){
            if(userDao.UpdateUserPass( edtPhone.getText().toString(), edtNewPass.getText().toString())){
                Toast.makeText(getApplicationContext(),"Update password successfully.",Toast.LENGTH_LONG).show();
                startActivity(new Intent(ForgotPassActivity.this, LoginActivity.class));
            }
            else{
                Toast.makeText(getApplicationContext(),"Update password unsuccessfully.",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Phone number don't exist.",Toast.LENGTH_LONG).show();
        }

    }
}
