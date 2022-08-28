package com.example.rhomie.Models;

import androidx.annotation.NonNull;

import com.example.rhomie.Objects.Address;
import com.example.rhomie.Objects.Flags;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.Objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Observable;

public class UpdateItemModel extends Observable {

    DatabaseReference items;
    FirebaseUser userID;

    public UpdateItemModel() {
        items = FirebaseDatabase.getInstance().getReference("UserApartments");
        userID = FirebaseAuth.getInstance().getCurrentUser();
    }


    public void updateItem(String item_id, Address address, Flags flags, String checkIn, String checkOut, String guestNumber) {
        items.child(userID.getUid()).child(item_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DatabaseReference item = items.child(userID.getUid()).child(item_id).getRef();

                Flags f = snapshot.getValue(Item.class).getFlags();

                if (!checkIn.isEmpty())
                    item.child("checkIn").setValue(checkIn);

                if (!checkOut.isEmpty())
                    item.child("checkOut").setValue(checkOut);

                if (!guestNumber.isEmpty())
                    item.child("guestNumber").setValue(guestNumber);

                if (!address.getCity().isEmpty())
                    item.child("address").child("city").setValue(address.getCity());
                if (!address.getStreet().isEmpty())
                    item.child("address").child("street").setValue(address.getStreet());
                if (!address.getStreetNumber().isEmpty())
                    item.child("address").child("streetNumber").setValue(address.getStreetNumber());
                if (!address.getFloor().isEmpty())
                    item.child("address").child("floor").setValue(address.getFloor());
                if (!address.getApartmentNumber().isEmpty())
                    item.child("address").child("apartmentNumber").setValue(address.getApartmentNumber());

                if (f.getKosher() != flags.getKosher())
                    item.child("flags").child("kosher").setValue(flags.getKosher());
                if (f.getAnimals() != flags.getAnimals())
                    item.child("flags").child("animals").setValue(flags.getAnimals());
                if (f.getAccessibility() != flags.getAccessibility())
                    item.child("flags").child("accessibility").setValue(flags.getAccessibility());
                if (f.getParking() != flags.getParking())
                    item.child("flags").child("parking").setValue(flags.getParking());
                if (f.getSmoking() != flags.getSmoking())
                    item.child("flags").child("smoking").setValue(flags.getSmoking());
                if (f.getWiFi() != flags.getWiFi())
                    item.child("flags").child("wiFi").setValue(flags.getWiFi());

                setChanged();
                notifyObservers(true);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
