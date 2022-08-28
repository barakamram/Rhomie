package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhomie.Controller.UpdateItemController;
import com.example.rhomie.Controller.UpdateUserController;
import com.example.rhomie.Objects.Address;
import com.example.rhomie.Objects.Flags;
import com.example.rhomie.R;

import java.util.Calendar;

public class UpdateItemActivity extends AppCompatActivity {
    private UpdateItemController controller;
    private EditText guest_number, city, street, street_number, floor, apartment_number;
    private CheckBox kosher, animals, accessibility, parking,smoking,wi_fi;
    private TextView check_in, check_out;
    String item_id;
    DatePickerDialog.OnDateSetListener dateListenerIn,dateListenerOut;
    final Calendar c = Calendar.getInstance();
    int day = c.get(Calendar.DAY_OF_MONTH);
    int month = c.get(Calendar.MONTH);
    int year = c.get(Calendar.YEAR);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        controller = new UpdateItemController(this);

        guest_number = findViewById(R.id.GuestNumberEditText);
        check_in = findViewById(R.id.checkInText);
        check_out = findViewById(R.id.checkOutText);
        setDateIn(check_in);
        setDateOut(check_out);

        city = findViewById(R.id.CityEditText);
        street = findViewById(R.id.StreetEditText);
        street_number = findViewById(R.id.StreetNumberEditText);
        floor = findViewById(R.id.FloorEditText);
        apartment_number = findViewById(R.id.ApartmentNumberEditText);

        //Flags
        kosher = findViewById(R.id.kosherCheckBox);
        animals = findViewById(R.id.animalCheckBox);
        accessibility = findViewById(R.id.accessibilityCheckBox);
        parking = findViewById(R.id.parkingCheckBox);
        smoking = findViewById(R.id.smokingCheckBox);
        wi_fi = findViewById(R.id.WiFiCheckBox);


        Intent intent = this.getIntent();
        if(intent != null) {
            item_id = intent.getStringExtra("item_id");

            check_in.setText(intent.getStringExtra("check_in"));
            check_out.setText(intent.getStringExtra("check_out"));
            guest_number.setText(intent.getStringExtra("guest_number"));
            city.setText(intent.getStringExtra("city"));
            street.setText(intent.getStringExtra("street"));
            street_number.setText(intent.getStringExtra("street_number"));
            floor.setText(intent.getStringExtra("floor"));
            apartment_number.setText(intent.getStringExtra("apartment_number"));
            kosher.setChecked(intent.getBooleanExtra("kosher",false));
            animals.setChecked(intent.getBooleanExtra("animals", false));
            accessibility.setChecked(intent.getBooleanExtra("accessibility", false));
            parking.setChecked(intent.getBooleanExtra("parking", false));
            smoking.setChecked(intent.getBooleanExtra("smoking", false));
            wi_fi.setChecked(intent.getBooleanExtra("wi_fi", false));
        }

    }

    public void updateItem(View view) {
        String CheckIn = check_in.getText().toString();
        String CheckOut = check_out.getText().toString();
        String GuestNumber = guest_number.getText().toString();

        String City = city.getText().toString();
        String Street = street.getText().toString();
        String StreetNumber = street_number.getText().toString();
        String Floor = floor.getText().toString();
        String ApartmentNumber = apartment_number.getText().toString();
        Address address = new Address(City, Street, StreetNumber , Floor, ApartmentNumber);

        boolean Kosher = kosher.isChecked();
        boolean Animals = animals.isChecked();
        boolean Accessibility = accessibility.isChecked();
        boolean Parking = parking.isChecked();
        boolean Smoking = smoking.isChecked();
        boolean WiFi = wi_fi.isChecked();
        Flags flags = new Flags(Kosher, Animals, Accessibility, Parking, Smoking, WiFi);


        controller.updateItem(item_id, address, flags, CheckIn, CheckOut, GuestNumber);

    }


    public void OnSuccess(String message){
        Toast.makeText(UpdateItemActivity.this, message, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(UpdateItemActivity.this,MyAccountActivity.class));
    }

    public void OnError(String message) {
        Toast.makeText(UpdateItemActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void setDateIn(TextView date){
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UpdateItemActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateListenerIn, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        dateListenerIn = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String dateToStr = dayOfMonth + "/" + month + "/" + year;
                date.setText(dateToStr);
            }
        };
    }

    private void setDateOut(TextView date) {
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UpdateItemActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateListenerOut, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        dateListenerOut = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String dateToStr = dayOfMonth + "/" + month + "/" + year;
                date.setText(dateToStr);
            }
        };
    }
}