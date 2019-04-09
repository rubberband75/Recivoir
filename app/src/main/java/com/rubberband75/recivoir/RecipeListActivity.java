package com.rubberband75.recivoir;

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

        recipeListView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> 1, View v, int position, long id){
        Log.i("HelloListView", "You clicked item: " + id + " at position:" + position);
        Intent intent = new Intent();
        intent.setClass(this,)
    }
}


