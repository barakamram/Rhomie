package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhomie.Controller.RequestController;
import com.example.rhomie.R;

import java.util.ArrayList;

public class SwitcherRequestActivity extends AppCompatActivity implements IRequestView {
    private RequestController controller;
    private TextView cityText, checkInText, checkOutText, guestNumberText, flagsText;
    private Spinner spinner;
    private EditText descriptionText;
    private String item_id, user_id;
    private boolean switcher;
    private String selectedText;
    private ArrayAdapter<String> adapter;
    private ApartmentListActivity view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switcher_request);

        controller = new RequestController(this);

        spinner = findViewById(R.id.itemsSpinner);
//        adapter = ArrayAdapter.createFromResource(this,myItems,R.layout.spinner_layout);

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
            ArrayList<String> myItems = intent.getStringArrayListExtra("items");

            cityText.setText(city);
            checkInText.setText(check_in);
            checkOutText.setText(check_out);
            guestNumberText.setText(guest_number);
            flagsText.setText(flags);


            adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,myItems);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedText = spinner.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    public void askForItem(View view) {
        addRequest();
    }


    public void addRequest() {
        String message = descriptionText.getText().toString();
        controller.getSwitcherDetails(item_id,user_id,message,selectedText);
    }

    public void OnSuccess(String massage) {

        Toast.makeText(SwitcherRequestActivity.this, massage, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SwitcherRequestActivity.this, HomeActivity.class));
    }

    public void OnError(String massage) {
        Toast.makeText(SwitcherRequestActivity.this, massage, Toast.LENGTH_SHORT).show();

    }
}