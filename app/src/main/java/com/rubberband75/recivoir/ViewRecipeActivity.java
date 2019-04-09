package com.rubberband75.recivoir;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ViewRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();

        String titleText = Recipe.getTitle();
        TextView titleView = findViewById(R.id.recipeTitleView);
        titleView.setText(titleText);

        String ingredientsText = Recipe.getIngredients();
        TextView ingredientsView = findViewById(R.id.recipeIngredientsView);
        ingredientsView.setText(ingredientsText);

        String instructionsText = Recipe.getSteps();
        TextView instructionsView = findViewById(R.id.recipeInstructionsView);
        instructionsView.setText(instructionsText);

        String notesText = Recipe.getNotes();
        TextView notesView = findViewById(R.id.recipeNotesView);
        notesView.setText(notesText);


    }

}
