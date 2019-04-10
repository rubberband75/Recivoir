package com.rubberband75.recivoir;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class ViewRecipeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "[Recivoir]ViewRecipes";

    Button edit;
    Button delete;
    Button back;

    Recipe currentRecipe;

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

        edit = (Button) findViewById(R.id.editRecipeButton);
        edit.setOnClickListener(this);

        delete = (Button) findViewById(R.id.deleteRecipeButton);
        delete.setOnClickListener(this);

        back = (Button) findViewById(R.id.editBackButton);
        back.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        final Context context = this;
        Intent incomingIntent = getIntent();
        String recipeID = incomingIntent.getStringExtra("recipeID");
        Log.d(TAG, "recipe ID:" + recipeID);

        Database.getRecipe(recipeID).addOnSuccessListener(new  OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if(!documentSnapshot.exists()){
                    Toast.makeText(context, "Recipe Not Found", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(context, MainActivity.class));

                } else {
                    Recipe recipe = new Recipe(documentSnapshot);
                    currentRecipe = recipe;
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

            }
        });

    }


    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.editRecipeButton:
                Intent editScreen = new Intent(getApplicationContext(),EditRecipeActivity.class);
                TextView title = findViewById(R.id.editTitleView);
                editScreen.putExtra("RECIPE_TITLE", title.getText());
                TextView ingredients = findViewById(R.id.recipeIngredientsView);
                editScreen.putExtra("RECIPE_INGREDIENTS", ingredients.getText());
                TextView instructions = findViewById(R.id.editInstructionsView);
                editScreen.putExtra("RECIPE_INSTRUCTIONS", instructions.getText());
                TextView notes = findViewById(R.id.editNotesView);
                editScreen.putExtra("RECIPE_NOTES", notes.getText());

                editScreen.putExtra(Database.RECIPE_IS_PUBLIC_KEY, currentRecipe.getIsPublic());

                editScreen.putExtra(Database.RECIPE_ID_KEY, currentRecipe.getRecipeID());

                startActivity(editScreen);
                break;
            case R.id.deleteRecipeButton:
                break;
            case R.id.editBackButton:
                Intent backScreen = new Intent(this, RecipeListActivity.class);
                startActivity(backScreen);
                break;
        }
    }

}
