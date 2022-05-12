package hcmute.nhom03.foodyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.nhom03.foodyapp.model.User;

public class WelcomeActivity extends AppCompatActivity {

    Button btnLogin, btnRegis;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_welcome);

        Intent intent = new Intent(WelcomeActivity.this, RestaurantsActivity.class);
        startActivity(intent);

        btnLogin = findViewById(R.id.btnLoginWelcome);
        btnRegis = findViewById(R.id.btnRegisterWelcome);

        checkExist();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regis = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(regis);
            }
        });
    }
    private void checkExist(){
        userLocalStore = new UserLocalStore(this);
        User user = userLocalStore.getLoggedInUser();
        if( user != null){
            Intent home = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(home);
        }
    }
}