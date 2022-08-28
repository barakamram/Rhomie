package com.example.rhomie.Controller;

import com.example.rhomie.Models.ApartmentListModel;
import com.example.rhomie.Models.IApartmentListModel;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.View.IApartmentListView;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ApartmentListController implements IApartmentListController, Observer {
    private IApartmentListModel model;
    private IApartmentListView view;

    public ApartmentListController(IApartmentListView view){
        this.view = view;
        this.model = new ApartmentListModel();
        ((Observable)model).addObserver(this);
    }

    @Override
    public void getItems() {
        model.getUserType();
    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<ArrayList<Item>> items = (ArrayList<ArrayList<Item>>) arg;
        if(items != null)
            view.drawItems(items);
//
//        if(items.get(0) != null){
//            view.drawItems(items.get(0));
//        if(items.get(1) != null)
//            view.getMyItems(items.get(1));
//        }

    }
}
