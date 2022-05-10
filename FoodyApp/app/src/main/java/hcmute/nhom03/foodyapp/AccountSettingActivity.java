package hcmute.nhom03.foodyapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import hcmute.nhom03.foodyapp.model.User;

public class AccountSettingActivity extends AppCompatActivity {
    TextView Info, Edit, Receipt, Logout, txtUsername;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        AnhXa();
        userLocalStore = new UserLocalStore(this);
        User user = userLocalStore.getLoggedInUser();
        txtUsername.setText(user.getName());

        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountSettingActivity.this, InfoAccountActivity.class);
                startActivity(intent);
            }
        });
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountSettingActivity.this, EditAccountActivity.class);
                startActivity(intent);
            }
        });
        Receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }
    public void AnhXa()
    {
        Info = findViewById(R.id.infoAccount);
        Edit = findViewById(R.id.editAccount);
        Receipt = findViewById(R.id.receipts);
        Logout = findViewById(R.id.logout);
        txtUsername = findViewById(R.id.txtUsername);
    }
    public void logout(){
        new AlertDialog.Builder(AccountSettingActivity.this)
                .setMessage("Bạn muốn đăng xuất?")
                .setCancelable(false)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        userLocalStore.clearUserData();
                        Intent intent = new Intent(AccountSettingActivity.this, WelcomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Không", null)
                .show();
    }

}
