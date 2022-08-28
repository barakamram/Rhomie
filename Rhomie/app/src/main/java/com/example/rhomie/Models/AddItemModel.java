package com.example.rhomie.Models;

import androidx.annotation.NonNull;

import com.example.rhomie.Objects.Item;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddItemModel extends Observable {

    private DatabaseReference items,users;

    public AddItemModel(){
        this.users = FirebaseDatabase.getInstance().getReference("Users");
        this.items = FirebaseDatabase.getInstance().getReference("UserApartments");
    }

    public void addItem(Item item,boolean switcher){
        DatabaseReference push = items.child(getUser()).push();
        String key = push.child(push.getKey()).getKey();
        item.setItem(key);
        item.setIsSwitcher(switcher);
        push.setValue(item)
        .addOnSuccessListener(suc->{
            //success
            setChanged();
            push.child("isSwitcher").setValue(switcher);
            notifyObservers(true);
        }).addOnFailureListener(fail->{
            //failed
            setChanged();
            notifyObservers(false);
        });
    }

    public String getUser(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public void getUserType(Item item){
        users.child(getUser()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isSwitcher = snapshot.child("isSwitcher").exists();

                if (isSwitcher)
                   addItem(item,true);
                else
                    addItem(item,false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
