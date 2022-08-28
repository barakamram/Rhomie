package com.example.rhomie.Models;

import com.example.rhomie.Objects.IUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Observable;

public class SignUpModel extends Observable implements ISignUpModel {

    private DatabaseReference users;
    private FirebaseAuth mAuth;

    public SignUpModel() {
        this.users = FirebaseDatabase.getInstance().getReference("Users");
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void init() {
    //TODO if we need that
    }

    @Override
    public void addUser(String choose, IUser user) {
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
            .addOnSuccessListener(suc->{
                users.child(mAuth.getUid()).setValue(user)
                    .addOnSuccessListener(suc2->{
                        //success to add user to authentication database and realtime database.users.
                        int chs = 0;
                        if(choose.equals("Users"))
                          chs = -1;
                        else if(choose.equals("SwitcherUsers"))
                            chs = 1;
                        setChanged();
                        notifyObservers(chs);
                    }).addOnFailureListener(fail->{
                            //success to add user to authentication database but not to the realtime database.users.
                        setChanged();
                        notifyObservers(2);
                    });
            }).addOnFailureListener(fail->{
                // failed to add user to authentication database - probably he is exist already.
                setChanged();
                notifyObservers(3);
            });
    }
}


