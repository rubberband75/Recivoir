package com.rubberband75.recivoir;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyRecipesActivity extends AppCompatActivity {
    private static final String TAG = "[Recivoir]MyRecipes";

    ListView listView;

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

        Database.getMyRecipes().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d(TAG, "onComplete: getMyRecipes()");
                ArrayList<Recipe> recipes = Database.getRecipesFromTask(task);

                for(Recipe r : recipes) {
                    Log.d(TAG, "Recipe Title: " + r.getTitle());
                }
            }
        });

    }
}
