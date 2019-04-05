package com.rubberband75.recivoir;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddRecipeActivity extends AppCompatActivity {

    private static final String TAG = "[Recivoir]AddRecipe:";

    private static final String FIRESTORE_COLLECTION = "public";
    private static final String TITLE_KEY = "title";
    private static final String INGREDIENTS_KEY = "ingredients";
    private static final String STEPS_KEY = "steps";
    private static final String NOTES_KEY = "notes";


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
    }

    public void saveRecipe(View view) {
        Log.d(TAG, "saveRecipe: SAVE Button Press");
        Toast.makeText(this, "SAVE Button Press", Toast.LENGTH_SHORT).show();


          /* This works, it just needs to be adapted to the right inputs */

        EditText titleInput = (EditText) findViewById(R.id.new_title_input);
        EditText ingredientsInput = (EditText) findViewById(R.id.new_ingredients_input);
        EditText stepsInput = (EditText) findViewById(R.id.new_steps_input);
        EditText notesInput = (EditText) findViewById(R.id.new_notes_input);

        String titleText = titleInput.getText().toString();
        String ingredientsText = ingredientsInput.getText().toString();
        String stepsText = stepsInput.getText().toString();
        String notesText = notesInput.getText().toString();

        if(titleText.isEmpty() || ingredientsText.isEmpty() || stepsText.isEmpty()) {
            Toast.makeText(this, "Missing Info", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> recipe = new HashMap<>();
        recipe.put(TITLE_KEY, titleText);
        recipe.put(INGREDIENTS_KEY, ingredientsText);
        recipe.put(STEPS_KEY, stepsText);
        recipe.put(NOTES_KEY, notesText);


        Toast.makeText(this, "Saving Recipe", Toast.LENGTH_SHORT).show();

        db.collection(FIRESTORE_COLLECTION)
                .add(recipe)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void discardRecipe(View view) {
        Log.d(TAG, "saveRecipe: DISCARD Button Press");
        Toast.makeText(this, "DISCARD Button Press", Toast.LENGTH_SHORT).show();
    }
}
