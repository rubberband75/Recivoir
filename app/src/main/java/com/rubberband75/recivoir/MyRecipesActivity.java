package com.rubberband75.recivoir;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyRecipesActivity extends AppCompatActivity {
    private static final String TAG = "[Recivoir]MyRecipes";

    ListView listView;
    RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        listView = (ListView)findViewById(R.id.recipe_listview);
        ArrayList<Recipe> data = Database.getRecipes();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        final Context context = this;

        Database.getMyRecipes().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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


    private class RecipeAdapter extends ArrayAdapter<Recipe> {
        public RecipeAdapter(Context context, ArrayList<Recipe> recipes){
            super(context, 0, recipes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Recipe recipe = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_item, parent, false);
            }
            // Lookup view for data population
            TextView recipeTitle = (TextView) convertView.findViewById(R.id.recipeTitleRow);

            // Populate the data into the template view using the data object
            recipeTitle.setText(recipe.getTitle());

            // Return the completed view to render on screen
            return convertView;
        }
    }
}
