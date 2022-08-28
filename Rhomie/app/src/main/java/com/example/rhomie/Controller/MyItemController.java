package com.example.rhomie.Controller;

import com.example.rhomie.Models.MyItemModel;
import com.example.rhomie.Objects.IRequest;
import com.example.rhomie.Objects.Request;
import com.example.rhomie.View.MyItemActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MyItemController implements Observer {
    private MyItemModel model;
    private MyItemActivity view;

    public MyItemController(MyItemActivity view){
        this.view = view;
        this.model = new MyItemModel();
        ((Observable)model).addObserver(this);
    }

    public void getRequests(String item_id){
        model.getRequests(item_id);
    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<IRequest> requestList = (ArrayList<IRequest>) arg;
        if(requestList != null)
            view.drawRequests(requestList);
    }
}
