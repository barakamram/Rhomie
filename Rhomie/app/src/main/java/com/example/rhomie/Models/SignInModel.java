package com.example.rhomie.Models;

import com.example.rhomie.Objects.IUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Observable;

public class SignInModel extends Observable implements ISignInModel {

    private FirebaseAuth mAuth;

    public SignInModel() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void CheckUser(IUser user){
        mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
            .addOnCompleteListener((OnCompleteListener<AuthResult>) task -> {
                if(task.isSuccessful()){
                    setChanged();
                    notifyObservers(-1);
                } else {
                    setChanged();
                    notifyObservers(1);
                }
            });
    }
}