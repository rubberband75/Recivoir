package com.rubberband75.recivoir;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Database {

    private static final String TAG = "[Recivoir][Database]:";

    private static final String COLLECTION_RECIPES = "recipes";
    private static final String RECIPE_TITLE_KEY = "title";
    private static final String RECIPE_INGREDIENTS_KEY = "ingredients";
    private static final String RECIPE_STEPS_KEY = "steps";
    private static final String RECIPE_NOTES_KEY = "notes";
    private static final String RECIPE_IS_PUBLIC_KEY = "isPublic";
    private static final String RECIPE_AUTHOR_KEY = "authorID";
    private static final String RECIPE_AUTHOR_NAME_KEY = "authorName";



    private static FirebaseUser currentUser;
    private static FirebaseFirestore db;


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
        return new Recipe("Awesome Recipe Title", "Burger\nbuns", "but the burger in the bun\nenjoy", "Add ketchup if you want, I don't care", true);
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
    static public Task saveRecipe(String title, String ingredients, String steps, String notes, Boolean isPublic) {

        Map<String, Object> recipe = new HashMap<>();
        recipe.put(RECIPE_TITLE_KEY, title);
        recipe.put(RECIPE_INGREDIENTS_KEY, ingredients);
        recipe.put(RECIPE_STEPS_KEY, steps);
        recipe.put(RECIPE_NOTES_KEY, notes);
        recipe.put(RECIPE_IS_PUBLIC_KEY, isPublic);
        recipe.put(RECIPE_AUTHOR_KEY, currentUser.getUid());
        recipe.put(RECIPE_AUTHOR_NAME_KEY, currentUser.getDisplayName());

        Task task = db.collection(COLLECTION_RECIPES).add(recipe);

        return task;
    }


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


    /**
     * Initializes the shared firebase connection
     * @param context
     */
    public static void initializeDB(Context context) {
        FirebaseApp.initializeApp(context);
        db = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Log.d(TAG, "Initializing Database: " + currentUser.getProviderId());
    }


    /**
     * Returns current Firebase User
     */
    static public FirebaseUser getCurrentFirebaseUser() {
        return currentUser;
    }


    static public Task getPublicRecipesTask(){
        return db.collection(COLLECTION_RECIPES).whereEqualTo("isPublic", true).get();
    }

    static public ArrayList<Recipe> getRecipesFromTask(Task<QuerySnapshot> task){
        ArrayList<Recipe> recipes = new ArrayList<>();
        if (task.isSuccessful()) {
            for (QueryDocumentSnapshot document : task.getResult()) {
                Map<String, Object> data = document.getData();

                Recipe r = new Recipe(data.get("title").toString(), data.get("ingredients").toString(), data.get("steps").toString(), data.get("notes").toString(), true);
                recipes.add(r);
            }
        } else {
            Log.w(TAG, "Error getting documents.", task.getException());
        }
        return recipes;
    }

    static public void logPublic(){
        Task<QuerySnapshot> task = db.collection(COLLECTION_RECIPES).whereEqualTo("isPublic", true).get();

            task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, "getPublicRecipes:" + document.getId() + " => " + document.getData().get("title"));
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
    }



}
