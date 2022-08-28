package com.example.rhomie.Models;

import androidx.annotation.NonNull;

import com.example.rhomie.Objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.internal.zzx;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Observable;

public class UpdateUserModel extends Observable {

    DatabaseReference users;
    FirebaseUser userID;
    public UpdateUserModel(){
        users = FirebaseDatabase.getInstance().getReference("Users");
        userID = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void updateUser(String firstName, String lastName, String id, String phoneNumber, String email, String password) {

        users.child(userID.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DatabaseReference user = users.child(userID.getUid()).getRef();
                String fn = snapshot.getValue(User.class).getFirstName();
                String ln = snapshot.getValue(User.class).getLastName();
                if(!firstName.isEmpty() && lastName.isEmpty()) {
                    user.child("firstName").setValue(firstName);
                    user.child("fullName").setValue(firstName+" "+ln);
                }

                if(firstName.isEmpty() && !lastName.isEmpty()) {
                    user.child("lastName").setValue(lastName);
                    user.child("fullName").setValue(fn + " " + lastName);
                }
                if (!firstName.isEmpty() && !lastName.isEmpty()) {
                    user.child("firstName").setValue(firstName);
                    user.child("lastName").setValue(lastName);
                    user.child("fullName").setValue(firstName + " " + lastName);
                }

                if(!id.isEmpty())
                    user.child("id").setValue(id);
                if(!phoneNumber.isEmpty())
                    user.child("phoneNumber").setValue(phoneNumber);
                if(!email.isEmpty()) {
                    user.child("email").setValue(email);
                    userID.updateEmail(email);
                }
                if(!password.isEmpty()) {
                    user.child("password").setValue(password);
                    userID.updatePassword(password);
                }

                setChanged();
                notifyObservers(true);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
