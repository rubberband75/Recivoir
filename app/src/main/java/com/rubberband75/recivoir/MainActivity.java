package com.rubberband75.recivoir;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "Recivoir - MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "The creation has occured");

        findViewById(R.id.login_button).setOnClickListener(this);
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
        Log.d(TAG, "Saving name to shared preferences");

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences userDetails = this.getSharedPreferences("userdetails", MODE_PRIVATE);

        if(userDetails.contains("username")) {
            String name = userDetails.getString("username", null);
            EditText nameInput = (EditText) findViewById(R.id.name_input);
            nameInput.setText(name);
            Log.d(TAG, "Loading name from shared preferences");
        } else {
            Log.d(TAG, "No name saved in shared preferences");
        }
    }


    public void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        Log.d(TAG, "Starting Login Activity");
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                startLoginActivity();
                break;
            // ...
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
