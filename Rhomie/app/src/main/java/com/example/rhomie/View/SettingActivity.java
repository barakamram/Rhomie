package com.example.rhomie.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.example.rhomie.Controller.SettingController;
import com.example.rhomie.R;

public class SettingActivity extends AppCompatActivity {
    private TextView greetingTextView,emailTextView,fullNameTextView, phoneNumberTextView, idTextView;
    private String email,firstName,lastName,phoneNumber,id;
    private SettingController controller;

    private ImageView profileImage, setImageIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        greetingTextView = findViewById(R.id.welcome);
        emailTextView = findViewById(R.id.email);
        fullNameTextView = findViewById(R.id.fullName);
        phoneNumberTextView = findViewById(R.id.phoneNumber);
        idTextView = findViewById(R.id.id);
        controller = new SettingController(this);

        controller.getDetails();

//_____________________________________________________________________//
//                  Image profile

        profileImage = findViewById(R.id.profileImage);
        setImageIcon = findViewById(R.id.setImageView);

        setImageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open gallery
                Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGallery, 1000);
            }
        });

        controller.getProfileImage();

//_____________________________________________________________________//
    }



    //for profile image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000){
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                profileImage.setImageURI(imageUri);

                //upload the image in firebase
                controller.setProfileImage(imageUri);
            }
        }

    }

    public void setProfileImage(Uri image){
        Picasso.get().load(image).into(profileImage);
    }

    public void OnSetProfileImageError(String message){
        Toast.makeText(SettingActivity.this, message, Toast.LENGTH_SHORT).show();
    }





    public void getDetails(String greeting,String email,String firstName,String lastName,String phoneNumber,String id){
        this.greetingTextView.setText(greeting);
        this.emailTextView.setText(email);
        this.fullNameTextView.setText(firstName+" "+lastName);
        this.phoneNumberTextView.setText(phoneNumber);
        this.idTextView.setText(id);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public void Logout(View view){
        controller.Logout();
        startActivity(new Intent(SettingActivity.this, MainActivity.class));
    }

    public void goToHomePage(View view) {
        startActivity(new Intent(SettingActivity.this, HomeActivity.class));
    }

    public void goToChangeInfo(View view) {
        Intent i = new Intent(SettingActivity.this, UpdateUserActivity.class);
        i.putExtra("email",email);
        i.putExtra("first_name",firstName);
        i.putExtra("last_name",lastName);
        i.putExtra("id",id);
        i.putExtra("phone_number",phoneNumber);
        startActivity(i);
    }

}