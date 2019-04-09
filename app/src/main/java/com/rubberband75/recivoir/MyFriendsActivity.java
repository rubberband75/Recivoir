package com.rubberband75.recivoir;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyFriendsActivity extends AppCompatActivity {
    private static final String TAG = "[Recivoir]MyFriends";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friends);

        Database.getMyFriends().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d(TAG, "onComplete: getMyFriends()");
                //Then within this, call the appropriate get____fromTask(task) function
                //If it's a list of recipes, it's this
                //But there will also be one for Users
                ArrayList<User> friends = Database.getFriendsFromTask(task);

                for(User friend : friends) {
                    Log.d(TAG, "onComplete: " + friend.getUserID());
                }

            }
        });
    }

}
