package com.example.rhomie.Controller;

import com.example.rhomie.Models.MyRequestsModel;
import com.example.rhomie.Objects.Request;
import com.example.rhomie.View.MyRequestsActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MyRequestsController implements Observer {
    private MyRequestsActivity view;
    private MyRequestsModel model;

    public MyRequestsController(MyRequestsActivity view) {
        this.view = view;
        this.model = new MyRequestsModel();
        model.addObserver(this);
    }


    public void getRequests() {
        model.getRequests();
    }


    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Request> requests = (ArrayList<Request>) arg;
        view.drawRequests(requests);
    }

}
