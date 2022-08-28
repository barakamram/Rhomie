package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rhomie.Controller.MyItemController;
import com.example.rhomie.Objects.IRequest;
import com.example.rhomie.Objects.Request;
import com.example.rhomie.R;

import java.util.ArrayList;

public class MyItemActivity extends AppCompatActivity {
    private MyItemController controller;
    private TextView addressText, checkInText, checkOutText, guestNumberText, flagsText;
    private String item_id,check_in,check_out,guest_number,address,flags;
    private String city,street,street_number,floor,apartment_number;
    private boolean kosher,animals,accessibility,parking,smoking,wi_fi;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item);

        controller = new MyItemController(this);

        listView = findViewById(R.id.requestListView);

        addressText = findViewById(R.id.address);
        checkInText = findViewById(R.id.checkIn);
        checkOutText = findViewById(R.id.checkOut);
        guestNumberText = findViewById(R.id.guestNumber);
        flagsText = findViewById(R.id.flags);

        Intent intent = this.getIntent();
        if(intent != null) {
            item_id = intent.getStringExtra("item_id");
            address = intent.getStringExtra("address");
            check_in = intent.getStringExtra("check_in");
            check_out = intent.getStringExtra("check_out");
            guest_number = intent.getStringExtra("guest_number");
            flags = intent.getStringExtra("flags");

            city = intent.getStringExtra("city");
            street = intent.getStringExtra("street");
            street_number = intent.getStringExtra("street_number");
            floor = intent.getStringExtra("floor");
            apartment_number = intent.getStringExtra("apartment_number");
            kosher = intent.getBooleanExtra("kosher",false);
            animals = intent.getBooleanExtra("animals",false);
            accessibility  = intent.getBooleanExtra("accessibility",false);
            parking  = intent.getBooleanExtra("parking",false);
            smoking  = intent.getBooleanExtra("smoking",false);
            wi_fi = intent.getBooleanExtra("wi_fi",false);

            addressText.setText(address);
            checkInText.setText(check_in);
            checkOutText.setText(check_out);
            guestNumberText.setText(guest_number);
            flagsText.setText(flags);
        }
        controller.getRequests(item_id);
    }

    public void drawRequests(ArrayList<IRequest> requestList) {
        ArrayList<String> requests = new ArrayList<>();
        for(IRequest req: requestList){
            requests.add(req.requestToString());
        }
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, requests);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(requestList.get(position).getStatus()!=1) {
                    Intent i = new Intent(MyItemActivity.this, ApprovalOrRejectionActivity.class);
                    i.putExtra("req_id", requestList.get(position).getID());
                    i.putExtra("item_id", requestList.get(position).getItemID());
                    i.putExtra("full_name", requestList.get(position).getFullName());
                    i.putExtra("phone_number", requestList.get(position).getPhoneNumber());
                    i.putExtra("message", requestList.get(position).getMessage());
                    i.putExtra("itemToChange",requestList.get(position).getItemToChange());
                    startActivity(i);
                }
            }
        });
    }

    public void updateItem(View view){
        Intent i = new Intent(MyItemActivity.this,UpdateItemActivity.class);
        i.putExtra("item_id",item_id);

        i.putExtra("check_in",check_in);
        i.putExtra("check_out",check_out);
        i.putExtra("guest_number",guest_number);
        i.putExtra("city",city);
        i.putExtra("street", street);
        i.putExtra("street_number",street_number);
        i.putExtra("floor",floor);
        i.putExtra("apartment_number",apartment_number);

        i.putExtra("kosher",kosher);
        i.putExtra("animals",animals);
        i.putExtra("accessibility",accessibility);
        i.putExtra("parking",parking);
        i.putExtra("smoking",smoking);
        i.putExtra("wi_fi",wi_fi);
        startActivity(i);
    }
}