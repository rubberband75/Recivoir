package com.rubberband75.recivoir;

import java.util.ArrayList;


public class Database {

    static public String test() {
        return "database test";
    }


    static public ArrayList<Recipe> getRecipes(){
        ArrayList<Recipe> docs = new ArrayList<Recipe>();

        Recipe doc1 = new Recipe("Cookies", "Flour\nbaking soda\nsugar\nother stuff", "Mix and bake", "None", true);
        Recipe doc2 = new Recipe("Grape juice", "grapes\nwater\nsugar", "Blend it all up!", "Have fun!", true);
        Recipe doc3 = new Recipe("Bad Booze", "Grape juice\nThe Sun","Leave the Grapejuice out in the sun", "Don't actually drink this!", false);

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


    /**
     * Gets chosen recipe from User's list of recipes
     * @return Recipe
     */
    static public Recipe getRecipe(String recipeId) {
        return new Recipe();
    }

    /**
     * Gets current User
     * @return User
     */
    static public User getCurrentUser() {
        return new User();
    }

    /**
     * Finds User by email address
     * @return User
     * @param email Email of target User
     */
    static public User findUser(String email){
        return new User();
    }

    /**
     * Gets User by userId
     * @return User
     * @param userId The user ID of target user
     */
    static public User getUser(String userId) {return new User(); }

    /**
     * Saves recipe to user's list
     * @param title Title of recipe
     * @param ingredients Ingredients list for recipe
     * @param steps Steps list for recipe
     * @param notes Notes for recipe
     * @param isPublic Public setting of recipe
     */
    static public void saveRecipe(String title, String ingredients, String steps, String notes, Boolean isPublic) { }

    /**
     * Allows user to edit recipe
     * @param recipeID ID of chosen recipe
     * @param title Title of recipe
     * @param ingredients Ingredients list for recipe
     * @param steps Steps list for recipe
     * @param notes Notes for recipe
     * @param isPublic Public setting of recipe
     */
    static public void editRecipe(String recipeID, String title, String ingredients, String steps, String notes, Boolean isPublic) {}

}
