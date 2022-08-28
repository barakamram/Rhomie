package com.example.rhomie.Models;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.rhomie.Objects.IUser;
import com.example.rhomie.Objects.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Observable;

public class SettingModel extends Observable {

    private DatabaseReference users;

    private FirebaseUser user;

    private String details;

//    ______
    private StorageReference storageReference;


    public SettingModel(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        user = FirebaseAuth.getInstance().getCurrentUser();

        storageReference = FirebaseStorage.getInstance().getReference(user.getUid().toString());
    }

    public void getDetails() {
        users.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                IUser userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {
                    String email = userProfile.getEmail();
                    String firstName = userProfile.getFirstName();
                    String lastName = userProfile.getLastName();
                    String phoneNumber = userProfile.getPhoneNumber();
                    String id = userProfile.getID();
                    details = email+'/'+firstName+'/'+lastName+'/'+phoneNumber+'/'+id+'/';
                }
                setChanged();
                notifyObservers(details);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    public void Logout() {
        FirebaseAuth.getInstance().signOut();
    }


    public void setProfileImage(Uri imageUri){
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Picasso.get().load(uri).into(profile_image);
                //uploade the controller
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                setChanged();
                notifyObservers(e);
            }
        });
    }
    public void getProfileImage(){
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                setChanged();
                notifyObservers(uri);
            }
        });
    }

}
