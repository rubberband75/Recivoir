package com.rubberband75.recivoir;

/**
 * @author Chandler, Michael, Sam
 * @version 1.0
 *
 * Recipe Class
 */
public class Recipe {

    public String recipeId = "r000001";
    public String title = "Cool Recipe Title";
    public String ingredients = "chips\ncorn\ncheese\nbeans\ntortilla";
    public String steps = "make the food";
    public String notes = "you don't need any notes";
    public String author = "u000123";
    public Boolean isPublic = true;

    /**
     * Recipe Constructor
     */
    public Recipe(String title, String ingredients, String steps, String notes, Boolean isPublic) {
        this.title = title;
        this.ingredients = ingredients;
        this.steps = steps;
        this.notes = notes;
        this.isPublic = isPublic;
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
     * Sets the title of recipe
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
}
