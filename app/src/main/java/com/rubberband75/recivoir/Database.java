package com.rubberband75.recivoir;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Database {

    private static final String TAG = "[Recivoir][Database]";

    public static final String COLLECTION_USERS = "users";
    public static final String USER_ID = "userID";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";
    public static final String USER_DOC_ID = "documentID";
    public static final String USER_COLLECTOIN_FRIENDS = "friends";

    public static final String COLLECTION_RECIPES = "recipes";
    public static final String RECIPE_ID_KEY = "recipeID";
    public static final String RECIPE_TITLE_KEY = "title";
    public static final String RECIPE_INGREDIENTS_KEY = "ingredients";
    public static final String RECIPE_STEPS_KEY = "steps";
    public static final String RECIPE_NOTES_KEY = "notes";
    public static final String RECIPE_IS_PUBLIC_KEY = "isPublic";
    public static final String RECIPE_AUTHOR_KEY = "authorID";
    public static final String RECIPE_AUTHOR_NAME_KEY = "authorName";



    private static FirebaseUser currentFirebaseUser;
    private static FirebaseFirestore db;
    private static User currentUser;


    static public String test() {
        return "database test";
    }


    static public ArrayList<Recipe> getRecipes(){
        ArrayList<Recipe> docs = new ArrayList<Recipe>();

        Recipe doc1 = new Recipe("1234", "Cookies", "Flour\nbaking soda\nsugar\nother stuff", "Mix and bake", "None", true);
        Recipe doc2 = new Recipe("1234","Grape juice", "grapes\nwater\nsugar", "Blend it all up!", "Have fun!", true);
        Recipe doc3 = new Recipe("1234", "Bad Booze", "Grape juice\nThe Sun","Leave the Grapejuice out in the sun", "Don't actually drink this!", false);

        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);

        return docs;
    }

    static public ArrayList<User> getUsers() {
        ArrayList<User> people = new ArrayList<>();

        User p1 = new User();
        p1.name = "Chandler";
        User p2 = new User();
        p2.name = "Sam";
        User p3 = new User();
        p3.name = "Michael";

        people.add(p1);
        people.add(p2);
        people.add(p3);

        return people;
    }


    /**
     * Gets chosen recipe from User's list of recipes
     * @return Recipe
     */
    static public Task getRecipe(final String recipeId) {
        return db.collection(COLLECTION_RECIPES).document(recipeId).get();
    }

    /**
     * Gets current User
     * @return User
     */
    static public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Finds User by email address
     * @return Task<QuerySnapshot>
     * @param email Email of target User
     */
    static public Task<QuerySnapshot> findUser(String email){
        return db.collection(COLLECTION_USERS).whereEqualTo(USER_EMAIL, email).get();
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
        recipe.put(RECIPE_AUTHOR_KEY, currentFirebaseUser.getUid());
        recipe.put(RECIPE_AUTHOR_NAME_KEY, currentFirebaseUser.getDisplayName());

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
    static public Task editRecipe(String recipeID, String title, String ingredients, String steps, String notes, Boolean isPublic) {

        return db.collection(COLLECTION_RECIPES).document(recipeID).update(
            RECIPE_TITLE_KEY, title,
            RECIPE_INGREDIENTS_KEY, ingredients,
            RECIPE_STEPS_KEY, steps,
            RECIPE_NOTES_KEY, notes,
            RECIPE_IS_PUBLIC_KEY, isPublic
        );
    }


    /**
     * Deletes recipe of given ID
     * @return Recipe
     */
    static public Task deleteRecipe(final String recipeID) {
        return db.collection(COLLECTION_RECIPES).document(recipeID).delete();
    }


    /**
     * Initializes the shared firebase connection
     * @param context
     */
    public static void initializeDB(Context context) {
        FirebaseApp.initializeApp(context);
        db = FirebaseFirestore.getInstance();
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Log.d(TAG, "Initializing Database: " + currentFirebaseUser.getProviderId());

        db.collection(COLLECTION_USERS).whereEqualTo(USER_ID, currentFirebaseUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    QuerySnapshot result = task.getResult();

                    if(result.isEmpty()) {
                        Log.d(TAG, "Adding user to database");

                        Map<String, Object> user = new HashMap<>();
                        user.put(USER_ID, currentFirebaseUser.getUid());
                        user.put(USER_NAME, currentFirebaseUser.getDisplayName());
                        user.put(USER_EMAIL, currentFirebaseUser.getEmail());

//                        user.put(USER_friends, new ArrayList<>());
//                        user.put(USER_recipes, new ArrayList<>());
//                        user.put("things", (new HashMap<String, Object>()).put("name", "asdf"));

                        db.collection(COLLECTION_USERS).add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        Log.d(TAG, "Successfully added user: " + documentSnapshot.get(USER_NAME));
                                        setCurrentUser(documentSnapshot);
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding user", e);
                            }
                        });

                    } else if(result.size() == 1){
                        DocumentSnapshot document = result.getDocuments().get(0);
                        Log.d(TAG, "retrieved user: " + document.get(USER_NAME));
                        setCurrentUser(document);

                    } else {
                        Log.w(TAG, "gettingUser: Multiple Users for ID: " + currentFirebaseUser.getUid());
                    }
                } else {
                    Log.d(TAG, "Error getting user: ", task.getException());
                }
            }
        });

    }

    /**
     * @param userID
     * @param friendID
     * @param friendName
     * @return
     */
    static public Task addFriend(String userID, String friendID, String friendName) {

        Map<String, Object> friend = new HashMap<>();
        friend.put("userID", friendID);
        friend.put("name", friendName);

        Task task = db.collection(COLLECTION_USERS).document(userID).collection("friends").add(friend);

        return task;
    }

    /**
     * @param document
     */
    private static void setCurrentUser(DocumentSnapshot document){
        currentUser = new User(document);
    }

    /**
     * Returns current Firebase User
     */
    static public FirebaseUser getCurrentFirebaseUser() {
        return currentFirebaseUser;
    }


    static public Task getPublicRecipesTask(){
        return db.collection(COLLECTION_RECIPES).whereEqualTo(RECIPE_IS_PUBLIC_KEY, true).get();
    }

    static public Task getMyRecipes() {
        Log.d(TAG, "getMyRecipes(where author == " + currentUser.getUserID()+")");
        return db.collection(COLLECTION_RECIPES).whereEqualTo(RECIPE_AUTHOR_KEY, currentUser.getUserID()).get();
    }

    static public ArrayList<Recipe> getRecipesFromTask(Task<QuerySnapshot> task){
        ArrayList<Recipe> recipes = new ArrayList<>();
        if (task.isSuccessful()) {
            for (QueryDocumentSnapshot document : task.getResult()) {
                Map<String, Object> data = document.getData();

                Recipe r = new Recipe(document.getId(), data.get("title").toString(), data.get("ingredients").toString(), data.get("steps").toString(), data.get("notes").toString(), true);
                recipes.add(r);
            }
        } else {
            Log.w(TAG, "Error getting documents.", task.getException());
        }
        return recipes;
    }

    /**
     * @return
     */
    static public Task getMyFriends() {
        Log.d(TAG, "getMyFriends(where id == " + currentUser.getUserID()+")");
        Task t =  db.collection(COLLECTION_USERS).document(currentUser.getDocumentID()).collection("friends").get();
        return t;
    }

    /**
     * @param snapshots
     * @return
     */
    static public List<User> getUsersFromSnapshot(QuerySnapshot snapshots) {
        List<User> users = new ArrayList<>();

        for(DocumentSnapshot snapshot : snapshots.getDocuments()) {
            try {
                Log.d(TAG, "FriendName: " + snapshot.get(Database.USER_NAME));
                User u = new User(snapshot);
                users.add(u);
            } catch (Exception e) {
                Log.w(TAG, "getUsersFromSnapshot: ");
            }
        }

        return users;
    }

    /**
     * @param task
     * @return
     */
    static public ArrayList<User> getFriendsFromTask(Task<QuerySnapshot> task){
        ArrayList<User> friends = new ArrayList<>();
        if (task.isSuccessful()) {
            for (QueryDocumentSnapshot document : task.getResult()) {
                Map<String, Object> data = document.getData();

                User friend = new User();
                friends.add(friend);
            }
        } else {
            Log.w(TAG, "Error getting documents.", task.getException());
        }
        return friends;
    }

}
