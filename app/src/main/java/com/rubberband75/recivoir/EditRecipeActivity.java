package com.rubberband75.recivoir;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;

public class EditRecipeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "[Recivoir]EditRecipe:";

    String editTitle;
    String editIngredients;
    String editSteps;
    String editNotes;
    Boolean editIsPublic;

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
        CheckBox editIsPublicCheck = (CheckBox) findViewById(R.id.editIsPublic);

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
            editIsPublic = (Boolean) bd.get(Database.RECIPE_IS_PUBLIC_KEY);
            editIsPublicCheck.setChecked(editIsPublic);
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
                saveRecipe();
                break;
            case R.id.editDeleteButton:
                Log.d(TAG, "onClick: delete");
                deleteRecipe();
                break;
            case R.id.editBackButton:
                finish();
                break;
        }
    }

    private void saveRecipe() {
        String recipeID = getIntent().getStringExtra(Database.RECIPE_ID_KEY);
        String title = ((EditText)findViewById(R.id.editRecipeTitle)).getText().toString();
        String ingredients = ((EditText)findViewById(R.id.editRecipeIngredients)).getText().toString();
        String steps = ((EditText) findViewById(R.id.editRecipeSteps)).getText().toString();
        String notes = ((EditText) findViewById(R.id.editRecipeNotes)).getText().toString();
        Boolean isPublic = ((CheckBox) findViewById(R.id.editIsPublic)).isChecked();

        Database.editRecipe(recipeID, title, ingredients, steps, notes, isPublic).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully updated!");
                finish();
            }
        });
    }

    private void deleteRecipe() {
        final Context context = this;
        String recipeID = getIntent().getStringExtra(Database.RECIPE_ID_KEY);
        Log.d(TAG, "deleteRecipe: " + recipeID);
        Database.deleteRecipe(recipeID).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Document successfully deleted!");
                Toast.makeText(context, "Recipe Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, MainActivity.class));
            }
        });
    }

}
