package com.example.rhomie.Controller;

public interface ISignUpController {

    /**
     * this method get params and checks if is correct
     * if at least one of the param are uncorrectable sends massage to the view
     * if all the param are correct creates new User object and adds it to the database
     * @param first_name
     * @param last_name
     * @param id
     * @param phone_number
     * @param email
     * @param password
     */
    public void OnSignUp(int choose,String first_name,String last_name,String id,String phone_number,String email,String password);
}