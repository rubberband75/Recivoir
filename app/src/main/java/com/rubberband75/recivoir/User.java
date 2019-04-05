package com.rubberband75.recivoir;

import java.util.ArrayList;

public class User {

    public String userID = "u000001";
    public String firstName = "First";
    public String lastName = "Last";
    public String email = "email@google.com";


    public ArrayList<Recipe> getRecipes(){
        return Database.getRecipes();
    }

    public ArrayList<User> getFriends(){
        return Database.getUsers();
    }

    public void addFriend(String userID) {}

    public void removeFriend(String userID) {}
}
