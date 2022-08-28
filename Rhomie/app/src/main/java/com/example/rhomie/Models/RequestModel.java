package com.example.rhomie.Models;

import androidx.annotation.NonNull;

import com.example.rhomie.Objects.IRequest;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.Objects.Request;
import com.example.rhomie.Objects.SwitcherRequest;
import com.example.rhomie.Objects.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Observable;

public class RequestModel extends Observable {
    private DatabaseReference items,requests,users;
    private boolean available;
    public RequestModel(){
        items = FirebaseDatabase.getInstance().getReference("UserApartments");
        requests = FirebaseDatabase.getInstance().getReference("UserRequests");
        users = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void addRequest(String item_id,String user_id, IRequest request,boolean switcher,String myItem) {
        DatabaseReference pushReq = items.child(user_id).child(item_id).child("requests").child(request.getFromUserID());
        String reqKey = pushReq.getKey();
        request.setItemToChange(myItem);
        request.setID(reqKey);
        items.child(user_id).child(item_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Item item = snapshot.getValue(Item.class);
                pushReq.setValue(request).
                addOnSuccessListener(suc -> {
                    addToUserReq(item, request);
                    setChanged();
                    notifyObservers(true);
                }).addOnFailureListener(failed -> {
                    setChanged();
                    notifyObservers(false);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    private void addToUserReq(Item item, IRequest request){
        String user = getUser();
        String details = item.getAddress().getCity()+'#'+item.getGuestNumber()+'#'+item.getCheckIn()+" - "+item.getCheckOut()+'#'+item.getAddress().addressToString()+'#';
        requests.child(user).child(item.getItem()).setValue(request);
        requests.child(user).child(item.getItem()).child("details").setValue(details);

    }

    public String getUser(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public void getDetails(String item,String user_id,String message,String itemDetails,String switcher) {
        users.child(getUser()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> details = new ArrayList<>();
                User user = snapshot.getValue(User.class);
                String fullName = user.getFullName();
                String phoneNumber = user.getPhoneNumber();
                details.add(item);
                details.add(user_id);
                details.add(message);
                details.add(fullName);
                details.add(phoneNumber);
                details.add(switcher);
                details.add(itemDetails);

                setChanged();
                notifyObservers(details);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
