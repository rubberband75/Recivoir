package com.rubberband75.recivoir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FindRecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_recipes);

        Database.getPublicRecipes();
    }
}
