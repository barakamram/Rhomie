package com.example.rhomie.View;

import android.widget.ArrayAdapter;

import com.example.rhomie.Objects.Item;

import java.util.ArrayList;

public interface IApartmentListView {

    void drawItems(ArrayList<ArrayList<Item>> items);
}
