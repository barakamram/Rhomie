package com.example.rhomie.Models;

import androidx.annotation.NonNull;

import com.example.rhomie.Objects.Item;
import com.example.rhomie.Objects.Request;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Observable;

public class MyRequestsModel extends Observable {

    private FirebaseUser user;
    private DatabaseReference userRequests;

    public MyRequestsModel(){
        userRequests = FirebaseDatabase.getInstance().getReference("UserRequests");
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void getRequests() {
        userRequests.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Request> requestsList = new ArrayList<>();
                for(DataSnapshot dts : snapshot.getChildren()){
                    requestsList.add(dts.getValue(Request.class));
                }
                setChanged();
                notifyObservers(requestsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
