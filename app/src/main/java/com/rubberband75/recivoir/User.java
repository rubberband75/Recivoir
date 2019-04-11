package com.rubberband75.recivoir;

import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {

    public String userID = "";
    public String name = "";
    public String email = "";
    private String documentID = "";

    /**
     * Default Constructor
     */
    public User(){}

    /**
     * @param document
     */
    public User(DocumentSnapshot document) {
        this.setFullName(document.get(Database.USER_NAME).toString());
        this.setUserID(document.get(Database.USER_ID).toString());
        this.setEmail(document.get(Database.USER_EMAIL).toString());

        String documentIDStr;
        Object documentID = document.get(Database.USER_DOC_ID);
        if(documentID == null){
            documentIDStr = document.getId();
        } else {
            documentIDStr = documentID.toString();
        }
        this.setDocumentID(documentIDStr);
    }

    /**
     * @return User object as Map
     */
    public Map toMap(){
        Map<String, Object> map = new HashMap<>();
        map.put(Database.USER_ID, this.userID);
        map.put(Database.USER_NAME, this.name);
        map.put(Database.USER_EMAIL, this.email);
        map.put(Database.USER_DOC_ID, this.documentID);
        return map;
    }

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

    /**
     * @return
     */
    public String getDocumentID() {
        return documentID;
    }

    /**
     * @param documentID
     */
    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }
}
