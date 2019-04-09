package com.rubberband75.recivoir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EditRecipeActivity extends AppCompatActivity {

    String editTitle;
    String editIngredients;
    String editSteps;
    String editNotes;

    public EditRecipeActivity(Recipe input){
        editTitle = input.getTitle();
        editIngredients = input.getIngredients();
        editSteps = input.getSteps();
        editNotes = input.getNotes();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);

        EditText editTitleText = (EditText) findViewById(R.id.editRecipeTitle);
        EditText editIngredientsText = (EditText) findViewById(R.id.editRecipeIngredients);
        EditText editStepsText = (EditText) findViewById(R.id.editRecipeSteps);
        EditText editNotesText = (EditText) findViewById(R.id.editRecipeNotes);

        editTitleText.setText(editTitle);
        editIngredientsText.setText(editIngredients);
        editStepsText.setText(editSteps);
        editNotesText.setText(editNotes);
    }

}
