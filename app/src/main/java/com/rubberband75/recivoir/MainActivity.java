package com.rubberband75.recivoir;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStop() {
        // call the superclass method first
        super.onStop();

        EditText nameInput = findViewById(R.id.name_input);
        String name = nameInput.getText().toString();

        SharedPreferences userDetails = this.getSharedPreferences("userdetails", MODE_PRIVATE);
        SharedPreferences.Editor edit = userDetails.edit();
        edit.putString("username", name);
        edit.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences userDetails = this.getSharedPreferences("userdetails", MODE_PRIVATE);
        String name = userDetails.getString("username", "");

        EditText nameInput = (EditText) findViewById(R.id.name_input);
        nameInput.setText(name);
    }
}
