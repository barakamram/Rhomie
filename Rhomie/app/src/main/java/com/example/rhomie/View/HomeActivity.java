package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.rhomie.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void goToMyAccount(View view) {
        startActivity(new Intent(this, MyAccountActivity.class));
    }

    public void goToApartmentsList(View view) {
        startActivity(new Intent(this, ApartmentListActivity.class));
    }

    public void goToUpdates(View view) {
        startActivity(new Intent(this, MyRequestsActivity.class));
    }

    public void goToSetting(View view) {
        startActivity(new Intent(this, SettingActivity.class));

    }
}