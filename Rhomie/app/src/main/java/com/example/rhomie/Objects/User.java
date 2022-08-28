package com.example.rhomie.Objects;

import java.util.HashMap;
import java.util.regex.Pattern;

public class User implements IUser{
    private String email,password,first_name,last_name,phone_number,id;
    private boolean is_switcher;
    private static final int EMPTYFIELD = 0,MINNAMELENGTH = 2,MINPASSWORDLENGTH = 6,IDLENGTH = 9,PHONELENGTH = 10;


    /* Default Constructor */
    public User () {
        this.email = "";
        this.password= "";
        first_name = "";
        last_name = "";
        phone_number = "";
        id = "";
        is_switcher = false;
    }
    /* Constructor */
    public User (String em,String pass) {
        this.email = em;
        this.password = pass;
        first_name = "";
        last_name = "";
        phone_number = "";
        id = "";
        is_switcher = false;
    }

    /* Full Constructor*/
    public User (String em,String pass, String fn, String ln, String pn, String id) {
        this.email = em;
        this.password = pass;
        this.first_name = fn;
        this.last_name = ln;
        this.phone_number = pn;
        this.id = id;
        this.is_switcher = false;
    }

    /* Email */
    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail (String e) {
        this.email = e;
    }

    /* Password */
    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String p) {
        this.password = p;
    }

    /* Name */
    @Override
    public String getFullName() {
        return first_name +' '+last_name;
    }

    public void setFirstName(String fn) {
        this.first_name = fn;
    }

    public void setLastName(String ln) {
        this.last_name = ln;
    }

    /* Phone Number */
    @Override
    public String getPhoneNumber() {
        return this.phone_number;
    }

    public void setPhoneNumber(String pn) {
        this.phone_number = pn;
    }

    /* ID */
    @Override
    public String getFirstName() {
        return first_name;
    }

    @Override
    public String getLastName() {
        return last_name;
    }

    @Override
    public String getID() {
        return this.id;
    }
    public void setID(String i) {
        this.id = i;
    }

    public HashMap<String,Object> toMap () {
        HashMap<String, Object> user = new HashMap<>();
        user.put("email", this.email);
        user.put("password", this.password);
        user.put("first_name", this.first_name);
        user.put("last_name", this.last_name);
        user.put("phone_number", this.phone_number);
        user.put("id", this.id);
        user.put("switcher",this.is_switcher);
        return user;
    }

    static public User toObject (HashMap<String,Object> hash){
        String em = hash.get("email").toString();
        String p = hash.get("password").toString();
        String fn = hash.get("first_name").toString();
        String ln = hash.get("last_name").toString();
        String pn = hash.get("phone_number").toString();
        String id = hash.get("id").toString();
        return new User(em, p, fn, ln, pn, id);

    }

    @Override
    public int isValid() {
        if(this == null)
            return 0;
        //TODO check if all the attribute are correct
        if(getFirstName().length() < MINNAMELENGTH || !onlyAlphabetic(getFirstName()))
            return 1;
        if(getLastName().length() < MINNAMELENGTH || !onlyAlphabetic(getLastName()))
            return 2;
        if(getID().length() != IDLENGTH || !onlyDigit(getID()))
            return 3;
        //TODO how to check if its start in 05
        if(getPhoneNumber().length() != PHONELENGTH || !onlyDigit(getPhoneNumber()))
            return 4;
        if(!isEmail(getEmail()))
            return 5;
        if (getPassword().length() < MINPASSWORDLENGTH)
            return 6;
        return -1;
    }

    public int isValid(String email,String password) {
        if(this == null)
            return 0;
        if(email.length() == EMPTYFIELD)
            return 1;
        if (password.length() == EMPTYFIELD)
            return 2;
        if(!isEmail(email))
            return 3;
        return -1;
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