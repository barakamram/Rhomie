package com.example.rhomie.Objects;

import java.util.HashMap;

public class Address {

    private String city;
    private String street;
    private String street_number;
    private String floor;
    private String apartment_number;

    /* Default Constructor */
    public Address () {
        city = "";
        street = "";
        street_number = "";
        floor = "";
        apartment_number = "";
    }

    /* Full Constructor */
    public Address (String c,String s,String sn,String f, String an) {
        this.city = c;
        this.street = s;
        this.street_number = sn;
        this.floor = f;
        this.apartment_number = an;
    }

    /* City */
    public String getCity () {
        return this.city;
    }

    public void setCity (String c) {
        this.city = c;
    }

    /* Street */
    public String getStreet () {
        return this.street;
    }

    public void setStreet (String s) {
        this.street = s;
    }

    /* Street Number */
    public String getStreetNumber () {
        return this.street_number;
    }

    public void setStreetNumber (String sn) {
        this.street_number = sn;
    }

    /* Floor */
    public String getFloor () {
        return this.floor;
    }

    public void setFloor (String f) {
        this.floor = f;
    }

    /* Apartment Number */
    public String getApartmentNumber () {
        return this.apartment_number;
    }

    public void setApartmentNumber (String an) {
        this.apartment_number = an;
    }

    public HashMap<String,Object> addressToMap () {
        HashMap<String, Object> address = new HashMap<>();
        address.put("city", this.city);
        address.put("street", this.street);
        address.put("street_number", this.street_number);
        address.put("floor", this.floor);
        address.put("apartment_number", this.apartment_number);
        return address;
    }

    public String addressToString(){
        if (getFloor().equals("") && !getApartmentNumber().equals(""))
            return this.city + " - " + street + " " + street_number + " number: " + apartment_number;
        else if (getApartmentNumber().equals("") && !getFloor().equals(""))
            return this.city + " - " + street + " " + street_number + " floor: " + floor;
        else if (getFloor().equals("") && getApartmentNumber().equals(""))
            return this.city + " - " + street + " " + street_number;
        return this.city + " - " + street + " " + street_number + " floor: " + floor + " number: " + apartment_number;
    }
}