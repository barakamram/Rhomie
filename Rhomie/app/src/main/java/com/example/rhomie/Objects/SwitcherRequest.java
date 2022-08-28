package com.example.rhomie.Objects;

import java.util.ArrayList;

public class SwitcherRequest implements IRequest{
    private String requestID;
    private String itemID;
    private String fromUserID;
    private String toUserID;
    private String message;
    private int status;
    private String fullName;
    private String phoneNumber;
    private String details;
    private String itemToChange;
    ArrayList<String> d = new ArrayList<>();

    public SwitcherRequest() {
        this.requestID = null;
        this.itemID = null;
        this.fromUserID = null;
//        this.toUserID = null;
        this.message = "";
        this.status = 0;
        this.fullName = null;
        this.phoneNumber = null;
        this.details = null;
        this.itemToChange = null;

    }

    public SwitcherRequest(String request,String item, String fromUser, String message, int status, String fullName,String phoneNumber,String myItem) {
        this.requestID = request;
        this.itemID = item;
        this.fromUserID = fromUser;
//        this.toUserID = toUser;
        this.message = message;
        this.status = status;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.itemToChange = myItem;
    }
    public SwitcherRequest(String request,String item, String fromUser, String message, int status, String fullName,String phoneNumber,String details,String myItem) {
        this.requestID = request;
        this.itemID = item;
        this.fromUserID = fromUser;
//        this.toUserID = toUser;
        this.message = message;
        this.status = status;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.details = details;
        this.itemToChange = myItem;

    }

    public String getID() {
        return this.requestID;
    }

    public void setID(String id){
        this.requestID = id;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(String fromUserID) {
        this.fromUserID = fromUserID;
    }

    public String getToUserID() {
        return toUserID;
    }
    public void setToUserID(String toUser) {
        this.toUserID = toUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDetails(){
        return details;
    }

    public String getItemToChange() {
        return itemToChange;
    }

    public void setItemToChange(String itemToChange) {
        this.itemToChange = itemToChange;
    }

    public int isValid() {
        if (this == null)
            return 0;
        if(getMessage().isEmpty())
            return 1;
        return -1;
    }

    public String requestToString() {
        if(getStatus() == 0)
            return "You have new request from "+fullName+"\n";
        else if(getStatus() == 1)
            return "You approved the request from "+fullName+"\nYou can continue communicate with him in his phone number "+phoneNumber+"\n";
        else
            return null;
    }

    public String ToString(){
        splitString(getDetails());
        String city = d.get(0);
        String guestNumber = d.get(1);
        String date = d.get(2);
        String address = d.get(3);
        String msg1 = " request for apartment:\n";
        String msg2 = "\nfor "+guestNumber+" guests\ndates: "+date+"\n";
        if(getStatus()==0)
            return "You sent a"+msg1+"city: "+city+msg2;
        else if(getStatus()==1)
            return "Congratulations! your"+msg1+"address: "+address+msg2+" approved\n";
        else
            return "We are sorry! your"+msg1+"city: "+city+msg2+" not approved\n";
    }

    private void splitString(String s) {
        char[] chars = s.toCharArray();
        String word = "";
        for(char c : chars) {
            if(c!= '#') {
                word += c;
            }
            else {
                d.add(word);
                word = "";
            }
        }
    }
}
