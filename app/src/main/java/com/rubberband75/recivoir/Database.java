package com.rubberband75.recivoir;

import java.util.ArrayList;


public class Database {

    static public String test() {
        return "database test";
    }


    static public ArrayList<Recipe> getRecipes(){
        ArrayList<Recipe> docs = new ArrayList<Recipe>();

        Recipe doc1 = new Recipe();
        Recipe doc2 = new Recipe();
        doc2.title = "Title Two!!";
        Recipe doc3 = new Recipe();
        doc3.title = "Title 3";

        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);

        return docs;
    }

    static public ArrayList<User> getUsers() {
        ArrayList<User> people = new ArrayList<>();

        User p1 = new User();
        p1.firstName = "Chandler";
        User p2 = new User();
        p2.firstName = "Sam";
        User p3 = new User();
        p3.firstName = "Michael";

        people.add(p1);
        people.add(p2);
        people.add(p3);

        return people;
    }



    static public Recipe getRecipe(String recipeId) {
        return new Recipe();
    }

    static public User getCurrentUser() {
        return new User();
    }

    static public User findUser(String email){
        return new User();
    }

    static public User getUser(String userId) {return new User(); }

    static public void saveRecipe(String title, String ingredients, String steps, String notes, Boolean isPublic) { }

    static public void editRecipe(String recipeID, String title, String ingredients, String steps, String notes, Boolean isPublic) {}

}
