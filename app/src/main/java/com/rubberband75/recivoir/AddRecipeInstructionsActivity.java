package com.rubberband75.recivoir;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class AddRecipeInstructionsActivity extends AppCompatActivity {

    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    EditText textInput;
    Button add;
    Button save;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe_ingredients);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        textInput = (EditText)findViewById(R.id.addInstruction);
        add = (Button)findViewById(R.id.addInstructionButton);
        list = (ListView)findViewById(R.id.ingredientList);

        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(AddRecipeInstructionsActivity.this, android.R.layout.simple_list_item_1,arrayList);
        list.setAdapter(adapter);

        onAddClick();
    }

    public void onAddClick() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = textInput.getText().toString();
                arrayList.add(result);
                adapter.notifyDataSetChanged();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddRecipeNotesActivity.class);
            }
        });
    }

}
