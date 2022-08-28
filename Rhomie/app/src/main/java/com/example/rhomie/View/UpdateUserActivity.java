package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rhomie.Controller.UpdateUserController;
import com.example.rhomie.R;

public class UpdateUserActivity extends AppCompatActivity {
    private UpdateUserController controller;
    private EditText first_name, last_name, id, phone_number, email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        controller = new UpdateUserController(this);

        first_name = findViewById(R.id.firstName);
        last_name = findViewById(R.id.lastName);
        id = findViewById(R.id.id);
        phone_number = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        Intent intent = this.getIntent();
        if(intent != null) {
            email.setText(intent.getStringExtra("email"));
            first_name.setText(intent.getStringExtra("first_name"));
            last_name.setText(intent.getStringExtra("last_name"));
            phone_number.setText(intent.getStringExtra("phone_number"));
            id.setText(intent.getStringExtra("id"));
        }
    }

    public void updateUser(View view) {
        String fn = first_name.getText().toString();
        String ln = last_name.getText().toString();
        String i = id.getText().toString();
        String pn =  phone_number.getText().toString();
        String e = email.getText().toString();
        String p = password.getText().toString();
        controller.updateUser(fn,ln,i,pn,e,p);
    }
    public void OnSuccess(String message){
        Toast.makeText(UpdateUserActivity.this, message, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(UpdateUserActivity.this,SettingActivity.class));
    }

    public void OnError(String message) {
        Toast.makeText(UpdateUserActivity.this, message, Toast.LENGTH_SHORT).show();
    }

}