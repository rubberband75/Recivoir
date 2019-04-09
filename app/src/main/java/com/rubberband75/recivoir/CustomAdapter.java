package com.rubberband75.recivoir;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Recipe> {

    private Context mContext;
    private List<Recipe> recipeList = new ArrayList<>();

    public CustomAdapter(Context context, ArrayList<Recipe> list) {
        super(context, 0, list);
        mContext = context;
        recipeList = list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.recipe_item,parent,false);
        }

        Recipe currentRecipe = recipeList.get(position);

        TextView title = (TextView) listItem.findViewById(R.id.recipeTitleRow);
        title.setText(currentRecipe.getTitle());

        return listItem;
    }
}
