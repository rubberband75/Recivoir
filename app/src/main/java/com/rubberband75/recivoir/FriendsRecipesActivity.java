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
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FriendsRecipesActivity extends AppCompatActivity {
    private static final String TAG = "[Recivoir]friendsR";

    private String userName;
    private String userID;

    private TextView activityTitle;
    private ListView listView;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_recipes);

        activityTitle = findViewById(R.id.friends_recipes_title);
        listView = findViewById(R.id.recipe_listview);

        Bundle bd = getIntent().getExtras();
        if(bd != null)
        {
            userName = (String) bd.get("userName");
            userID = (String) bd.get("userID");

            activityTitle.setText(userName + "'s Recipes");

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Getting Recipes For User: " + userID);
        final Context context = this;
        Database.getUsersRecipes(userID).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
