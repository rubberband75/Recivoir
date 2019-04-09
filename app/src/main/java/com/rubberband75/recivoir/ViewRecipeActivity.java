package com.rubberband75.recivoir;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class ViewRecipeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "[Recivoir]ViewRecipes";

    Button edit;
    Button delete;
    Button back;

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
                TextView titleView = findViewById(R.id.editTitleView);
                titleView.setText(titleText);

                String ingredientsText = recipe.getIngredients();
                TextView ingredientsView = findViewById(R.id.recipeIngredientsView);
                ingredientsView.setText(ingredientsText);

                String instructionsText = recipe.getSteps();
                TextView instructionsView = findViewById(R.id.editInstructionsView);
                instructionsView.setText(instructionsText);

                String notesText = recipe.getNotes();
                TextView notesView = findViewById(R.id.editNotesView);
                notesView.setText(notesText);

            }
        });

        edit = (Button) findViewById(R.id.editRecipeButton);
        edit.setOnClickListener(this);

        delete = (Button) findViewById(R.id.deleteRecipeButton);
        delete.setOnClickListener(this);

        back = (Button) findViewById(R.id.editBackButton);
        back.setOnClickListener(this);
    }

    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.editRecipeButton:
                
                break;
            case R.id.deleteRecipeButton:
                break;
            case R.id.editBackButton:
                break;
        }
    }

}
