package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rhomie.Controller.MyRequestsController;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.Objects.Request;
import com.example.rhomie.R;

import java.util.ArrayList;

public class MyRequestsActivity extends AppCompatActivity {

    private MyRequestsController controller;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_requests);

        controller = new MyRequestsController(this);

        listView = findViewById(R.id.listView);
        controller.getRequests();
    }


    public void drawRequests(ArrayList<Request> requests) {
        ArrayList<String> requestsS = new ArrayList<>();
        for(Request req :requests){
            requestsS.add(req.ToString());
        }
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, requestsS);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
//        listView.setClickable(true);
    }



}