package com.rubberband75.recivoir;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class MyFriendsActivity extends AppCompatActivity {
    private static final String TAG = "[Recivoir]MyFriends";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friends);

        Database.getMyFriends();

//        Database.getMyFriends().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                Log.d(TAG, "onComplete: getMyFriends()");
//                if (task.isSuccessful()) {
//                    Log.d(TAG, "onSuccess: getMyFriends()");
//
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Log.d(TAG, "Friend: " + document.getId());
//                    }
//                }
//            }
//        });

//        Database.getMyFriends().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                Log.d(TAG, "onComplete: getMyFriends()");
//                //Then within this, call the appropriate get____fromTask(task) function
//                //If it's a list of recipes, it's this
//                //But there will also be one for Users
//                ArrayList<User> friends = Database.getFriendsFromTask(task);
//
//                for(User friend : friends) {
//                    Log.d(TAG, "onComplete: " + friend.getUserID());
//                }
//
//            }
//        });
    }

}
