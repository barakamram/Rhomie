package com.example.rhomie.Objects;

import java.util.HashMap;

public class Flags {

    private boolean kosher;
    private boolean animals;
    private boolean accessibility;
    private boolean parking;
    private boolean smoking;
    private boolean Wi_Fi;

    /* Default Constructor */
    public Flags () {
        kosher = false;
        animals = false;
        accessibility = false;
        parking = false;
        smoking = false;
        Wi_Fi = false;
    }

    /* Full Constructor */
    public Flags (boolean k, boolean an, boolean ac, boolean p, boolean s, boolean wi) {
        kosher = k;
        animals = an;
        accessibility = ac;
        parking = p;
        smoking = s;
        Wi_Fi = wi;
    }

    /* Kosher */
    public boolean getKosher() {
        return this.kosher;
    }

    public void setKosher(boolean c) {
        this.kosher = c;
    }

    /* Animals */
    public boolean getAnimals () {
        return this.animals;
    }

    public void setAnimals (boolean an) {
        this.animals = an;
    }

    /* Accessibility */
    public boolean getAccessibility() {
        return this.accessibility;
    }

    public void setAccessibility(boolean ac) {
        this.accessibility = ac;
    }

    /* Parking */
    public boolean getParking () {
        return this.parking;
    }

    public void setParking (boolean p) {
        this.parking = p;
    }

    /* Smoking */
    public boolean getSmoking () {
        return this.smoking;
    }

    public void setSmoking (boolean s) {
        this.smoking = s;
    }

    /* Wi-Fi */
    public boolean getWiFi () {
        return this.Wi_Fi;
    }

    public void setWiFi (boolean wifi) {
        this.Wi_Fi = wifi;
    }

    public HashMap<String,Object> flagsToMap () {
        HashMap<String, Object> flags = new HashMap<>();
        flags.put("kosher", this.kosher);
        flags.put("animals", this.animals);
        flags.put("accessibility", this.accessibility);
        flags.put("parking", this.parking);
        flags.put("smoking", this.smoking);
        flags.put("Wi_Fi", this.Wi_Fi);
        return flags;
    }

    public String flagsToString() {
        return "kosher: "+kosher+"\nanimals: "+animals+"\naccessibility: "+accessibility+"\nparking: "+parking+"\nsmoking: "+smoking+"\nWi_Fi: "+Wi_Fi;
    }
}
