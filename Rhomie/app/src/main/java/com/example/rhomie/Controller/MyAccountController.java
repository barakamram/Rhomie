package com.example.rhomie.Controller;

import com.example.rhomie.Models.IMyAccountModel;
import com.example.rhomie.Models.MyAccountModel;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.View.IMyAccountView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MyAccountController implements IMyAccountController, Observer {
    private IMyAccountModel model;
    private IMyAccountView view;
    ArrayList<String> d = new ArrayList<>();

    public MyAccountController(IMyAccountView view){
        this.view = view;
        this.model = new MyAccountModel();
        ((Observable)model).addObserver(this);
    }

    @Override
    public void getItems() {
        model.getItems();
    }


    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Item> items = (ArrayList<Item>) arg;
        if(items != null)
            view.drawItems(items);


    }


}
