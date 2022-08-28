package com.example.rhomie.Models;

import com.example.rhomie.Objects.User;
import java.util.HashMap;
import java.util.List;

public interface IModelDatabase {

    public void init();

    public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(User user);

    /**
     * this method gets a primery key {email or id},
     * search for the key in the database path child
     * and returns the Object user if found otherwise returns null.
     *
     * ________________________________________________________________
     * example:  getUser("1") -> will return the User named "Gilad".   *
     * example:  getUser("5") -> will return null.                     *
     * database  ___1                                                  *
     *          |    |__user                                           *
     *          |           |_name: "Gilad"                            *
     *          |           |_email: "giladmoa@gmail.com"              *
     *          |___2                                                  *
     *          |    |__user                                           *
     *          |           |_name: "Barak"                            *
     *          |           |_email: "barakHaGevar@gmail.com"          *
     *          |___3                                                  *
     *          |   |__user                                            *
     *          |           |_name: "Noy"                              *
     *          |           |_email: "noyosi@gmail.com"                *
     * ________________________________________________________________*
     * @param key user's identify
     * @return User
     */
    public User getUser(String key);

    /**
     * this method returns a list of all the user are in the database
     * if there is no users in the database returns an empty list.
     * @return List
     */
    public List<User> getAllUsers();

    /**
     * this method gets Map of string attributes and value
     * and returns list of all the users that fit the value.
     *
     * example:
     * for database [user1{"name":"gilad" , "age":27}, user2{"name":"hello", "age":23}, user3{"name":"world", age:23}]
     * HashMap
     * getAllUsers( HashMap{"age":23}) -> will return list of[user2, user3]
     * @param keys
     * @return
     */
    public List<User> getAllUsers(HashMap<String, Object> keys);



}
