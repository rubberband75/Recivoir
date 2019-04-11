package com.rubberband75.recivoir;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FindRecipesActivity extends AppCompatActivity {
    private static final String TAG = "[Recivoir]MainActivity:";

    ListView listView;
    RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_recipes);

        listView = findViewById(R.id.recipe_listview);

        final Context context = this;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                TextView rID = (TextView) view.findViewById(R.id.recipeItemID);
                String recipeID = rID.getText().toString();

                Log.d(TAG, "onItemClick: " + recipeID);

                Intent intent = new Intent(context, ViewRecipeActivity.class);
                intent.putExtra("recipeID", recipeID);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        final Context context = this;

        Database.getPublicRecipesTask().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d(TAG, "onComplete: getMyRecipes()");
                ArrayList<Recipe> recipes = Database.getRecipesFromTask(task);

                for(Recipe r : recipes) {
                    Log.d(TAG, "Recipe Title: " + r.getTitle());
                }

                adapter= new RecipeAdapter(context, recipes);

                ListView listView = (ListView) findViewById(R.id.recipe_listview);
                listView.setAdapter(adapter);
            }
        });

    }
}
