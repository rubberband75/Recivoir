package com.rubberband75.recivoir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class FriendsRecipesActivity extends AppCompatActivity {
    private static final String TAG = "[Recivoir]friendsR";

    private String userName;
    private String userID;

    private TextView activityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_recipes);

        activityTitle = findViewById(R.id.friends_recipes_title);

        Bundle bd = getIntent().getExtras();
        if(bd != null)
        {
            userName = (String) bd.get("userName");
            userID = (String) bd.get("userID");

            activityTitle.setText(userName + "'s Recipes");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Getting Recipes For User: " + userID);
    }
}
