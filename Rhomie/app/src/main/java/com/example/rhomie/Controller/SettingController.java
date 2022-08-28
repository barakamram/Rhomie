package com.example.rhomie.Controller;

import android.net.Uri;

import com.example.rhomie.Models.SettingModel;
import com.example.rhomie.View.SettingActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class SettingController implements Observer {

    private SettingActivity view;
    private SettingModel model;
    private ArrayList<String> d = new ArrayList<>();

    public SettingController(SettingActivity view){
        this.view = view;
        this.model = new SettingModel();
        ((Observable)model).addObserver(this);
    }


    public void getDetails() {
        model.getDetails();
    }

    public void Logout(){
        model.Logout();
    }


//    _____________________________
//          PROFILE IMAGE
    public void setProfileImage(Uri imageUri){
        model.setProfileImage(imageUri);
    }
    public void getProfileImage(){ model.getProfileImage(); }
//          PROFILE IMAGE
//    _____________________________

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Uri){
            //from profile image - getProfileImage image
            view.setProfileImage((Uri)arg);

        } else if(arg instanceof Exception){
            //from profile image - setProfileImage error
            view.OnSetProfileImageError((((Exception) arg).getMessage()));

        }else {
            String details = (String) arg;
            if (details != null) {
                splitString(details);
                String email = d.get(0);
                String firstName = d.get(1);
                String lastName = d.get(2);
                String phoneNumber = d.get(3);
                String id = d.get(4);
                view.getDetails("היי " + firstName + "!", email, firstName, lastName, phoneNumber, id);
            }
        }
    }

    private void splitString(String s) {
        char[] chars = s.toCharArray();
        String word = "";
        for(char c : chars) {
            if(c!= '/') {
                word += c;
            }
            else {
                d.add(word);
                word = "";
            }
        }
    }

}
