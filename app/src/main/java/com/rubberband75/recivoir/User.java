package com.rubberband75.recivoir;

import java.util.ArrayList;

public class User {

    public String userID = "";
    public String name = "";
    public String email = "";

    /**
     * Gets User ID
     * @return userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets User ID
     * @param userID User ID of User
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Gets Name of User
     * @return lastName
     */
    public String getFullName() {
        return name;
    }

    /**
     * Sets Name of User
     * @param name Name of User
     */
    public void setFullName(String name) {
        this.name = name;
    }

    /**
     * Gets User Email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets User Email
     * @param email Email of User
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets User's list of Recipes
     * @return ArrayList<Recipe>
     */
    public ArrayList<Recipe> getRecipes(){
        return Database.getRecipes();
    }

    /**
     * Gets User's list of friends (Users)
     * @return ArrayList<User>
     */
    public ArrayList<User> getFriends(){
        return Database.getUsers();
    }

    /**
     * Adds friend to User's list of friends
     * @param userID  User ID of target User
     */
    public void addFriend(String userID) {}

    /**
     * Removes friend from User's list of friends
     * @param userID  User ID of target User
     */
    public void removeFriend(String userID) {}
}
