package com.example.rhomie.Models;

import com.example.rhomie.Objects.IUser;

public interface ISignInModel {

    /**
     * adds the user to the database
     * if success returns true otherwise returns false.
     * @param user
     */
    public void CheckUser(IUser user);
}
