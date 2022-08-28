package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhomie.Controller.RequestController;
import com.example.rhomie.R;

public class RequestActivity extends AppCompatActivity implements IRequestView {
    private RequestController controller;
    private TextView cityText, checkInText, checkOutText, guestNumberText, flagsText;
    private Spinner spinner;
    private EditText descriptionText;
    private String item_id, user_id;
    private boolean switcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        controller = new RequestController(this);

        cityText = findViewById(R.id.city);
        checkInText = findViewById(R.id.checkIn);
        checkOutText = findViewById(R.id.checkOut);
        guestNumberText = findViewById(R.id.guestNumber);
        flagsText = findViewById(R.id.flags);
        descriptionText = findViewById(R.id.MultiLine);

        Intent intent = this.getIntent();
        if(intent != null){
            item_id = intent.getStringExtra("item_id");
            user_id = intent.getStringExtra("user_id");
            String city = intent.getStringExtra("city");
            String check_in = intent.getStringExtra("check_in");
            String check_out = intent.getStringExtra("check_out");
            String guest_number = intent.getStringExtra("guest_number");
            String flags = intent.getStringExtra("flags");
            switcher = intent.getBooleanExtra("switcher",false);

            cityText.setText(city);
            checkInText.setText(check_in);
            checkOutText.setText(check_out);
            guestNumberText.setText(guest_number);
            flagsText.setText(flags);
        }
    }

    public void askForItem(View view) {
        addRequest();
    }

    @Override
    public void addRequest() {
        String message = descriptionText.getText().toString();
        controller.getDetails(item_id,user_id,message);
    }

    @Override
    public void OnSuccess(String massage) {

        Toast.makeText(RequestActivity.this, massage, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RequestActivity.this, HomeActivity.class));
    }

    @Override
    public void OnError(String massage) {
        Toast.makeText(RequestActivity.this, massage, Toast.LENGTH_SHORT).show();

    }
}