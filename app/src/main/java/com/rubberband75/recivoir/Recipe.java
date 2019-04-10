package com.rubberband75.recivoir;

import com.google.firebase.firestore.DocumentSnapshot;

/**
 * @author Chandler, Michael, Sam
 * @version 1.0
 *
 * Recipe Class
 */
public class Recipe {

    public String recipeID = "";
    public String title = "";
    public String ingredients = "";
    public String steps = "";
    public String notes = "";
    public Boolean isPublic = false;
    public String authorID = "";
    public String authorName = "";

    /**
     * Recipe Constructor
     */
    public Recipe(String recipeID, String title, String ingredients, String steps, String notes, Boolean isPublic) {
        this.recipeID = recipeID;
        this.title = title;
        this.ingredients = ingredients;
        this.steps = steps;
        this.notes = notes;
        this.isPublic = isPublic;
    }


    public Recipe(DocumentSnapshot document) {
        this.recipeID = document.getId();
        this.title = document.get(Database.RECIPE_TITLE_KEY).toString();
        this.ingredients = document.get(Database.RECIPE_INGREDIENTS_KEY).toString();
        this.steps = document.get(Database.RECIPE_STEPS_KEY).toString();
        this.notes = document.get(Database.RECIPE_NOTES_KEY).toString();
        this.isPublic = (boolean) document.get(Database.RECIPE_IS_PUBLIC_KEY);
        this.authorID = document.get(Database.RECIPE_AUTHOR_KEY).toString();
        this.authorName = document.get(Database.RECIPE_AUTHOR_NAME_KEY).toString();
    }


    /**
     * Gets recipeId
     * @return recipeID
     */
    public String getRecipeID() {
        return recipeID;
    }

    /**
     * Sets the recipeId of recipe
     * @param recipeID ID of recipe
     */
    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    /**
     * Gets title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of recipe
     * @param title Title of recipe
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets ingredients
     * @return ingredients
     */

    public String getIngredients() {
        return ingredients;
    }

    /**
     * Sets the ingredients of recipe
     * @param ingredients Ingredients of recipe
     */
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Gets steps
     * @return steps
     */
    public String getSteps() {
        return steps;
    }

    /**
     * Sets the steps of recipe
     * @param steps Steps of recipe
     */
    public void setSteps(String steps) {
        this.steps = steps;
    }

    /**
     * Gets notes
     * @return notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the notes of recipe
     * @param notes Title of recipe
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Gets isPublic
     * @return isPublic
     */
    public Boolean getIsPublic() {
        return isPublic;
    }

    /**
     * Sets the public setting of recipe
     * @param isPublic Public setting of recipe
     */
    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }


    /**
     * @return
     */
    public String getAuthorID() { return authorID; }


    /**
     * @param authorID
     */
    public void setAuthorID(String authorID) { this.authorID = authorID; }


    /**
     * @return
     */
    public String getAuthorName() { return authorName; }


    /**
     * @param authorName
     */
    public void setAuthorName(String authorName) { this.authorName = authorName; }


    public String toString() { return this.title; }
}
