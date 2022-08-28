package com.example.rhomie.Controller;

import android.util.Log;
import com.example.rhomie.Models.ISignUpModel;
import com.example.rhomie.Models.SignUpModel;
import com.example.rhomie.Objects.SwitcherUser;
import com.example.rhomie.Objects.User;
import com.example.rhomie.View.ISignUpView;
import java.util.Observable;
import java.util.Observer;

public class SignUpController implements ISignUpController, Observer {

    private ISignUpView view;
    private ISignUpModel model;

    public SignUpController(ISignUpView view) {
        this.view = view;
        model = new SignUpModel();
        ((SignUpModel)model).addObserver(this);
    }

    @Override
    public void OnSignUp(int db,String first_name, String last_name, String id, String phone_number, String email, String password) {
        int signupCode;
        User user = new User(email, password, first_name, last_name, phone_number, id);
        SwitcherUser switcherUser = new SwitcherUser(email, password, first_name, last_name, phone_number, id);

        if (db == -1)
            signupCode = user.isValid();
        else
            signupCode = switcherUser.isValid();

        if(signupCode == 0){
            Log.e("user","user cant be null"); //its mean code problem.
        }
        if(signupCode == 1){
            view.signUpError("First name consists only letters");
        }
        if(signupCode == 2){
            view.signUpError("Last name consists only letters");
        }
        if(signupCode == 3){
            view.signUpError("ID consists only digits and should be equal to 9");
        }
        if(signupCode == 4){
            view.signUpError("Phone number consists only digits and should be equal to 10");
        }
        if(signupCode == 5){
            view.signUpError("This is not an email");
        }
        if(signupCode == 6){
            view.signUpError("Password must be up than 6");
        }
        if(signupCode == -1 && db == -1)
            model.addUser("Users",user);
        else if(signupCode == -1 && db == 1)
            model.addUser("SwitcherUsers",switcherUser);
    }

    /** this function called automatic from SignUpModel.addUser()
     *  gets -1 if addUser success to add to authentication and realtime database
     *  gets 1 if addUser added to authentication but not to realtime database
     *  gets 2 if add user failed to add user to authentication database
     */
    @Override
    public void update(Observable o, Object arg) {
        int keyCode = (int) arg;
        if(keyCode == -1)
            view.signUpSuccess(-1,"Successfully signed up!");
        else if(keyCode == 1)
            view.signUpSuccess(1,"Successfully signed up!");
        else if(keyCode == 2)
            Log.e("firebase", "the user added to the authentication but not to realtime database");
        else if (keyCode == 3)
            view.signUpError("Email already exists!");
    }
}
