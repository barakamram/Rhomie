package com.example.rhomie.Controller;

import com.example.rhomie.Models.ApprovalOrRejectionModel;
import com.example.rhomie.Models.RequestModel;
import com.example.rhomie.View.ApprovalOrRejectionActivity;
import com.example.rhomie.View.IRequestView;

import java.util.Observable;
import java.util.Observer;

public class ApprovalOrRejectionController implements Observer {

    private ApprovalOrRejectionModel model;
    private ApprovalOrRejectionActivity view;

    public ApprovalOrRejectionController(ApprovalOrRejectionActivity view) {
        this.view = view;
        model = new ApprovalOrRejectionModel();
        model.addObserver(this);
    }

    public void ApproveRequest(String req, String item) {
        model.ApproveRequest(req,item);
    }

    public void RejectRequest(String req, String item) {
        model.RejectRequest(req,item);
    }


    @Override
    public void update(Observable o, Object arg) {
        if ((int)arg == 1){
            view.OnSuccessA("Successfully approval apartment");
        }
        else if ((int)arg == -1)
            view.OnSuccessR("You are reject the apartment");
        else
            view.OnError("Failed");
    }
}
