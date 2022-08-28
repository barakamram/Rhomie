package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.rhomie.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToSignInPage(View view) {
        startActivity(new Intent(this, SignInActivity.class));
    }

    public void goToSignUpPage(View view) {
        startActivity(new Intent(this, ChooseUserActivity.class));
    }

}


//TODO sigh up - Authentication database.   done.


//TODO log in

//TODO update user

//TODO ADD ITEM all processes.      working about that.
//


//TODO view items as a list