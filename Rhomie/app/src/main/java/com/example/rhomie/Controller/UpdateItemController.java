package com.example.rhomie.Controller;

import com.example.rhomie.Models.UpdateItemModel;
import com.example.rhomie.Objects.Address;
import com.example.rhomie.Objects.Flags;
import com.example.rhomie.View.UpdateItemActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class UpdateItemController implements Observer {

    private UpdateItemActivity view;
    private UpdateItemModel model;


    public UpdateItemController(UpdateItemActivity view){
        this.view = view;
        this.model = new UpdateItemModel();
        model.addObserver(this);

    }

    public void updateItem(String item_id, Address address, Flags flags, String checkIn, String checkOut, String guestNumber) {

        if (!checkIn.isEmpty() && !isValidDate(checkIn))
            view.OnError("Check-In should be later than Today");
        else if(!checkOut.isEmpty() && !isGreater(checkIn,checkOut))
            view.OnError("Check-Out should be later than Check-In");
        else if(!guestNumber.isEmpty() && !onlyDigit(guestNumber))
            view.OnError("Guest number must be only digit");
        else if(!address.getCity().isEmpty() && !onlyAlphabetic(address.getCity()))
            view.OnError("City must be only alphabetic");
        else if(!address.getStreet().isEmpty() && !onlyAlphabetic(address.getStreet()))
            view.OnError("Street must be only alphabetic");
        else if(!address.getStreetNumber().isEmpty() && !onlyDigit(address.getStreetNumber()))
            view.OnError("Street number must be only digit");
        else if(!address.getFloor().isEmpty() && !onlyDigit(address.getFloor()))
            view.OnError("Floor must be only digit");
        else if(!address.getApartmentNumber().isEmpty() && !onlyDigit(address.getApartmentNumber()))
            view.OnError("Apartment number must be only digit");
        else
            model.updateItem(item_id, address, flags, checkIn, checkOut, guestNumber);

    }

    @Override
    public void update(Observable o, Object arg) {
        if((boolean) arg)
            view.OnSuccess("Successfully update item details");
    }
    private boolean onlyAlphabetic(String s) {
        char[] chars = s.toCharArray();
        for(char c : chars){
            if(Character.isDigit(c))
                return false;
        }
        return true;
    }

    private boolean onlyDigit(String s) {
        char[] chars = s.toCharArray();
        for(char c : chars){
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }

    public boolean isValidDate(String dateStr)  {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date.after(currentDate))
            return true;
        return false;
    }

    public boolean isGreater(String check_in, String check_out)  {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateIn = null;
        Date dateOut = null;
        try {
            dateIn = sdf.parse(check_in);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            dateOut = sdf.parse(check_out);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(dateOut.after(dateIn))
            return true;

        return false;
    }
}
