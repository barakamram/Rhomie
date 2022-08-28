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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rhomie.Controller.AddItemController;
import com.example.rhomie.Objects.Address;
import com.example.rhomie.Objects.Flags;
import com.example.rhomie.R;
import java.util.Calendar;

public class AddItemActivity extends AppCompatActivity {
    private EditText guest_number, city, street, street_number, floor, apartment_number;
    private TextView check_in, check_out;
    private CheckBox kosher, animals, accessibility, parking, smoking, wi_fi;
    private ProgressBar progressBar;
    private AddItemController controller;
    DatePickerDialog.OnDateSetListener dateListenerIn,dateListenerOut;
    final Calendar c = Calendar.getInstance();
    int day = c.get(Calendar.DAY_OF_MONTH);
    int month = c.get(Calendar.MONTH);
    int year = c.get(Calendar.YEAR);
//    private Spinner spinner;
//    private String selectedText;
//    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        controller = new AddItemController(this);

        guest_number = findViewById(R.id.GuestNumberEditText);
        check_in = findViewById(R.id.checkInText);
        check_out = findViewById(R.id.checkOutText);
        setDateIn(check_in);
        setDateOut(check_out);

        //Address
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

//        spinner = findViewById(R.id.cities_spinner);
//        adapter = ArrayAdapter.createFromResource(this,R.array.city_array,R.layout.cities_spinner_layout);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                selectedText = spinner.getSelectedItem().toString();
////                city.setText(selectedText);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void ClickAddItem(View view){

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

        progressBar.setVisibility(View.VISIBLE);
        controller.OnAddItem(address, flags, CheckIn, CheckOut, GuestNumber);
    }

    public void AddItemSuccess(String massage){
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MyAccountActivity.class));

    }

    public void AddItemError(String massage){
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(AddItemActivity.this, massage, Toast.LENGTH_SHORT).show();
    }

    private void setDateIn(TextView date){
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddItemActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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
                        AddItemActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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


    public void goToHomePage(View view) {
        startActivity(new Intent(AddItemActivity.this, MyAccountActivity.class));

    }
}