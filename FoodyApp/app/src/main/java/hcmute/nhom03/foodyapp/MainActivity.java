package hcmute.nhom03.foodyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import hcmute.nhom03.foodyapp.fragment.ListRestaurantFragment;
import hcmute.nhom03.foodyapp.model.User;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    Fragment fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Binding();
        Init();
        SetUpBottomNavigation();

    }

    public void SetUpBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.home:
                    {
                        if (!(fragmentContainer instanceof ListRestaurantFragment)) {
                            DisplayRestaurants();
                        }
                        break;
                    }
                    case R.id.account:
                    {
                        Intent account = new Intent(MainActivity.this, AccountSettingActivity.class);
                        startActivity(account);
                    }
                    default:
                        return false;
                }
                return false;
            }
        });
    }

    public void Init() {
        this.fragmentManager = getSupportFragmentManager();
        fragmentContainer = fragmentManager.findFragmentById(R.id.fragment_container);
        DisplayRestaurants();
    }

    public void DisplayRestaurants() {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ListRestaurantFragment.class, null)
                .setReorderingAllowed(true)
                .commit();
    }
    public void Binding() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }
}