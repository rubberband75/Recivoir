package com.rubberband75.recivoir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public  class RecipeAdapter extends ArrayAdapter<Recipe> {
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
        TextView recipeID = (TextView) convertView.findViewById(R.id.recipeID);

        // Populate the data into the template view using the data object
        recipeTitle.setText(recipe.getTitle());
        recipeID.setText(recipe.getRecipeID());

        // Return the completed view to render on screen
        return convertView;
    }
}