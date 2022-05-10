package hcmute.nhom03.foodyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.nhom03.foodyapp.model.User;

public class InfoAccountActivity extends AppCompatActivity {
    TextView txtName, txtPhone, txtAddress;
    Button btnEdit;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info_account);

        AnhXa();

        userLocalStore = new UserLocalStore(this);
        User user = userLocalStore.getLoggedInUser();
        txtName.setText(user.getName());
        txtAddress.setText(user.getAddress());
        txtPhone.setText(user.getPhone());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(InfoAccountActivity.this, EditAccountActivity.class);
                startActivity(edit);
            }
        });

    }
    public void AnhXa(){
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtAddress = findViewById(R.id.txtAddress);
        btnEdit = findViewById(R.id.btnEdit);
    }
}
