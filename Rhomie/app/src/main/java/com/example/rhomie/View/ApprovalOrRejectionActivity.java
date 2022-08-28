package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhomie.Controller.ApprovalOrRejectionController;
import com.example.rhomie.Controller.RequestController;
import com.example.rhomie.R;

public class ApprovalOrRejectionActivity extends AppCompatActivity {
    private TextView fullNameText, phoneNumberText, messageText,itemToChangeText;
    private String reqID,itemID;
    private ApprovalOrRejectionController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_or_rejection);

        controller = new ApprovalOrRejectionController(this);

        fullNameText = findViewById(R.id.fullName);
        phoneNumberText = findViewById(R.id.phoneNumber);
        messageText = findViewById(R.id.message);
        itemToChangeText = findViewById(R.id.itemToChange);
        Intent intent = this.getIntent();
        if(intent != null) {
            reqID = intent.getStringExtra("req_id");
            itemID = intent.getStringExtra("item_id");
            String fullName = intent.getStringExtra("full_name");
            String phoneNumber = intent.getStringExtra("phone_number");
            String message = intent.getStringExtra("message");
            String itemToChange = intent.getStringExtra("itemToChange");
            fullNameText.setText(fullName);
            phoneNumberText.setText(phoneNumber);
            messageText.setText(message);
            if(itemToChange!= null)
                itemToChangeText.setText(itemToChange);
        }
    }

    public void ApproveRequest(View view) {
        controller.ApproveRequest(reqID,itemID);
    }

    public void RejectRequest(View view) {
        controller.RejectRequest(reqID,itemID);
    }

    public void OnSuccessA(String massage){
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MyAccountActivity.class));
    }
    public void OnSuccessR(String massage){
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MyAccountActivity.class));
    }


    public void OnError(String massage){
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show();
    }
}