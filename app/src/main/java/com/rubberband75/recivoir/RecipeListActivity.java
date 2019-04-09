package com.rubberband75.recivoir;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "[Recivoir]RecipeList";

    ListView recipeListView;
    CustomAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);

        recipeListView = (ListView)findViewById(R.id.recipe_listview);
        ArrayList<Recipe> data = new ArrayList<>();

        data = Database.getRecipes();

        for(Recipe r : data) {
            Log.d(TAG, "onComplete: " + r.getTitle());
        }

        mAdapter = new CustomAdapter(this, data);
        recipeListView.setAdapter(mAdapter);


        final Context context = this;
        recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                /*This was causing an error, so I commented it out*/
                //ItemClicked item = parent.getItemAtPosition(position).toString();

                Intent intent = new Intent(context, ViewRecipeActivity.class);
                startActivity(intent);
            }
        });

        //public ItemClicked getItem(int position) {
        //    return items.get(position);
        //}
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}


