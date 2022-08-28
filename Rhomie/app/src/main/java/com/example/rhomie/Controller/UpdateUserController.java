package com.example.rhomie.Controller;

import com.example.rhomie.Models.UpdateUserModel;
import com.example.rhomie.View.UpdateUserActivity;

import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

public class UpdateUserController implements Observer {
    private UpdateUserActivity view;
    private UpdateUserModel model;


    public UpdateUserController(UpdateUserActivity view){
        this.view = view;
        this.model = new UpdateUserModel();
        model.addObserver(this);

    }

    public void updateUser(String firstName, String lastName, String id, String phoneNumber, String email, String password) {
        if(!firstName.isEmpty() && !onlyAlphabetic(firstName))
            view.OnError("First name must be only alphabetic");
        else if(!lastName.isEmpty() && !onlyAlphabetic(lastName))
            view.OnError("Last name must be only alphabetic");
        else if(!id.isEmpty() && (!onlyDigit(id) || id.length() != 9))
            view.OnError("ID must be only digits and equal to 9");
        else if(!phoneNumber.isEmpty() && (!onlyDigit(phoneNumber) || phoneNumber.length()!=10))
            view.OnError("Phone number must be only digits and equal to 10");
        else if(!email.isEmpty() && !isEmail(email))
            view.OnError("Invalid email");
        else if(!password.isEmpty() && password.length()<6)
            view.OnError("Password must be up then 6");
        else if(firstName.isEmpty() && lastName.isEmpty() && id.isEmpty() && phoneNumber.isEmpty() && email.isEmpty() && password.isEmpty())
            view.OnError("Nothing was changed");
        else
            model.updateUser(firstName,lastName,id,phoneNumber,email,password);
    }

    @Override
    public void update(Observable o, Object arg) {
        if((boolean) arg)
            view.OnSuccess("Successfully update user details");
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

    private  boolean isEmail(String s) {
        String email = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(email);
        if (s == null)
            return false;
        return pat.matcher(s).matches();
    }

}
