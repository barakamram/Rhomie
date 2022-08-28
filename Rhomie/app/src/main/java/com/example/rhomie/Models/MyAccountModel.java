package com.example.rhomie.Models;

import androidx.annotation.NonNull;
import com.example.rhomie.Objects.IUser;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.Objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Observable;

public class MyAccountModel extends Observable implements IMyAccountModel {

    private DatabaseReference items, databaseUser;
    private FirebaseUser user;

    public MyAccountModel(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        items = db.getReference("UserApartments");
        databaseUser = db.getReference("Users");
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void getItems() {
        items.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Item> items = new ArrayList<>();
                for (DataSnapshot dts: snapshot.getChildren()) {
                        items.add(dts.getValue(Item.class));
                }
                setChanged();
                notifyObservers(items);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

    }


}
