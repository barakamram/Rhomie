package com.example.rhomie.Models;

import androidx.annotation.NonNull;

import com.example.rhomie.Objects.IRequest;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.Objects.Request;
import com.example.rhomie.Objects.SwitcherRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Observable;

public class ApprovalOrRejectionModel extends Observable {

    private final String user;
    private DatabaseReference items,requests;

    public ApprovalOrRejectionModel(){
        items = FirebaseDatabase.getInstance().getReference("UserApartments");
        requests = FirebaseDatabase.getInstance().getReference("UserRequests");
        user = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public void ApproveRequest(String req, String item) {
        items.child(user).child(item).child("requests").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isSwitcher = snapshot.getValue(Item.class).getIsSwitcher();
                IRequest request;
                for(DataSnapshot dts : snapshot.getChildren()){
                    if(isSwitcher)
                        request = dts.getValue(SwitcherRequest.class);
                    else
                        request = dts.getValue(Request.class);
                    if(request.getID().equals(req)){
                        dts.getRef().child("status").setValue(1);
                        dts.getRef().getParent().getParent().child("isAvailable").setValue(false);
                        UpdateData(item,request.getFromUserID(),1);
                    }
                    else{
                        dts.getRef().child("status").setValue(-1);
                        UpdateData(item,request.getFromUserID(),-1);
                    }
                }
                setChanged();
                notifyObservers(1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void UpdateData(String item,String fromUserID,int status) {
        requests.child(fromUserID).child(item).child("status").setValue(status);
    }

    public void RejectRequest(String req, String item) {
        items.child(user).child(item).child("requests").child(req).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().child("status").setValue(-1);
                UpdateData(item,snapshot.getValue(Request.class).getFromUserID(),-1);

                setChanged();
                notifyObservers(-1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
