package com.example.rhomie.Objects;

public interface IRequest {
    public String getID();

    public void setID(String id);

    public String getItemID();

    public void setItemID(String itemID);

    public String getFromUserID();

    public void setFromUserID(String fromUserID);

    public String getToUserID();
    public void setToUserID(String toUser);

    public String getMessage();

    public void setMessage(String message);

    public int getStatus();

    public void setStatus(int status);

    public String getFullName();

    public void setFullName(String fullName);



    public String getPhoneNumber();

    public void setPhoneNumber(String phoneNumber);

    public String getDetails();

    public String getItemToChange();


    public void setItemToChange(String itemToChange);
    public int isValid();

    String requestToString();
}
