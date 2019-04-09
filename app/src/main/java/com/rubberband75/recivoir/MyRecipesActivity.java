package com.rubberband75.recivoir;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyRecipesActivity extends AppCompatActivity {
    private static final String TAG = "[Recivoir]MyRecipes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        // Here is an example of how the Database will work
        // First call the get___Task() function that you need
        // Then call the addOnCompleteListener() as seen below
        // You will

        Database.getMyRecipes().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d(TAG, "onComplete: getMyRecipes()");
                //Then within this, call the appropriate get____fromTask(task) function
                //If it's a list of recipes, it's this
                //But there will also be one for Users
                ArrayList<Recipe> recipes = Database.getRecipesFromTask(task);

                for(Recipe r : recipes) {
                    Log.d(TAG, "onComplete: " + r.getTitle());
                }

            }
        });

    }
}
