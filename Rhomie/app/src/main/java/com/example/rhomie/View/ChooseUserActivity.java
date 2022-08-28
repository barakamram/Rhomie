package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rhomie.R;

public class ChooseUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
    }

    public void goToSignUp(View view) {
        Intent intent = new Intent(ChooseUserActivity.this,SignUpActivity.class);
        intent.putExtra("user",-1); // "Users"
        startActivity(intent);
    }

    public void goToSignUpSwitcher(View view) {
        Intent intent = new Intent(ChooseUserActivity.this,SignUpActivity.class);
        intent.putExtra("user",1); // "SwitcherUsers"
        startActivity(intent);
    }

}