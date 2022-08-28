package com.example.rhomie.Objects;

public interface IUser {

    public String getFirstName();
    public String getLastName();
    public String getID();
    public String getFullName();
    public String getPhoneNumber();
    public String getEmail();
    public String getPassword();

    /**
     * this methos checking if all the attribute are correct.
     * if the object is null returns 0.
     * if the first name is uncorrectable returns 1.
     * if the last name is uncorrectable returns 2.
     * if the id is uncorrectable returns 3.
     * if the phone number is uncorrectable returns 4.
     * if the email is uncorrectable returns 5.
     * if the password is uncorrectable returns 6.
     *
     * if the all attribute ate correct returns -1.
     * @return
     */
    public int isValid();
}
