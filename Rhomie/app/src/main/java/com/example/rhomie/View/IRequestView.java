package com.example.rhomie.View;

import com.example.rhomie.Objects.Item;

public interface IRequestView {

    public void addRequest();

    public void OnSuccess(String massage);
    public void OnError(String massage);
}
