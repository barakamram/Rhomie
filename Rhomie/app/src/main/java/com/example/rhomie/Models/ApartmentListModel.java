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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

public class ApartmentListModel extends Observable implements IApartmentListModel {

    private DatabaseReference items,users;
    private String user;

    public ApartmentListModel(){
        users = FirebaseDatabase.getInstance().getReference("Users");
        items = FirebaseDatabase.getInstance().getReference("UserApartments");
        user = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @Override
    public void getItems(boolean switcher) {
        items.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ArrayList<Item>> lists = new ArrayList<>();
                ArrayList<Item> list = new ArrayList<>();
                ArrayList<Item> switcherList = new ArrayList<>();
                ArrayList<Item> myList = new ArrayList<>();
                for (DataSnapshot userSS : snapshot.getChildren()) {
                    for (DataSnapshot itemSS : userSS.getChildren()) {
                        Item item = itemSS.getValue(Item.class);
                        if(item.getIsAvailable() && isValidDate(item.getCheckIn())){
                            if(item.getFatherID().equals(user))
                                myList.add(item);
                            else if (item.getIsSwitcher())
                                switcherList.add(item);
                            else
                                list.add(item);

                        }
                    }
                }
                if (switcher){
                    lists.add(switcherList);
                }
                else{
                    lists.add(list);
                }
                lists.add(myList);
                setChanged();
                notifyObservers(lists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    public void getUserType(){
        users.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isSwitcher = snapshot.child("isSwitcher").exists();
                if (isSwitcher)
                    getItems(true);
                else
                    getItems(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public boolean isValidDate(String dateStr)  {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date.after(currentDate))
            return true;
        return false;
    }

}
