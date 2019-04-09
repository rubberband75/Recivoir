package com.rubberband75.recivoir;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public class ViewRecipeActivity extends AppCompatActivity {
    private static final String TAG = "[Recivoir]ViewRecipes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Intent incomingIntent = getIntent();
        String recipeID = incomingIntent.getStringExtra("recipeID");

        Database.getRecipe(recipeID).addOnSuccessListener(new  OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                Recipe recipe = new Recipe(documentSnapshot);
                Log.d(TAG, "onSuccess: " + recipe.getRecipeID() + " | " + recipe.getTitle());

                String titleText = recipe.getTitle();
                TextView titleView = findViewById(R.id.recipeTitleView);
                titleView.setText(titleText);

                String ingredientsText = recipe.getIngredients();
                TextView ingredientsView = findViewById(R.id.recipeIngredientsView);
                ingredientsView.setText(ingredientsText);

                String instructionsText = recipe.getSteps();
                TextView instructionsView = findViewById(R.id.recipeInstructionsView);
                instructionsView.setText(instructionsText);

                String notesText = recipe.getNotes();
                TextView notesView = findViewById(R.id.recipeNotesView);
                notesView.setText(notesText);

            }
        });

    }

}
