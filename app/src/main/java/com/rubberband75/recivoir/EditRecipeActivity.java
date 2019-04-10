package com.rubberband75.recivoir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditRecipeActivity extends AppCompatActivity implements View.OnClickListener {

    String editTitle;
    String editIngredients;
    String editSteps;
    String editNotes;
    Button editSave;
    Button editDelete;
    Button editBack;

//    public EditRecipeActivity(Recipe input){
//        new Recipe output = input;
//        editTitle = input.getTitle();
//        editIngredients = input.getIngredients();
//        editSteps = input.getSteps();
//        editNotes = input.getNotes();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);

        EditText editTitleText = (EditText) findViewById(R.id.editRecipeTitle);
        EditText editIngredientsText = (EditText) findViewById(R.id.editRecipeIngredients);
        EditText editStepsText = (EditText) findViewById(R.id.editRecipeSteps);
        EditText editNotesText = (EditText) findViewById(R.id.editRecipeNotes);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();

        if(bd != null)
        {
            editTitle = (String) bd.get("RECIPE_TITLE");
            editTitleText.setText(editTitle);
            editIngredients = (String) bd.get("RECIPE_INGREDIENTS");
            editIngredientsText.setText(editIngredients);
            editSteps = (String) bd.get("RECIPE_INSTRUCTIONS");
            editStepsText.setText(editSteps);
            editNotes = (String) bd.get("RECIPE_NOTES");
            editNotesText.setText(editNotes);
        }




        editSave = (Button) findViewById(R.id.editSaveButton);
        editSave.setOnClickListener(this);

        editDelete = (Button) findViewById(R.id.editDeleteButton);
        editDelete.setOnClickListener(this);

        editBack = (Button) findViewById(R.id.editBackButton);
        editBack.setOnClickListener(this);
    }

    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.editSaveButton:
                input.setTitle(editTitle);
                input.setIngredients(editIngredients);
                input.setSteps(editSteps);
                input.setNotes(editNotes);
                break;
            case R.id.editDeleteButton:
                break;
            case R.id.editBackButton:
                break;
        }
    }

}
