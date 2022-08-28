package com.example.rhomie.Controller;

public interface ISignInController {

    /**
     * this method get params and checks if is correct
     * if at least one of the param are uncorrectable sends massage to the view
     * check if the Email and Password correct in the database and Login to the system
     * @param email
     * @param password
     */
    public void OnSignIn(String email,String password);
}


