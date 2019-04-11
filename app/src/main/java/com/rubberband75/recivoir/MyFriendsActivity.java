package com.rubberband75.recivoir;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MyFriendsActivity extends AppCompatActivity {
    private static final String TAG = "[Recivoir]MyFriends";

    ListView listView;
    FriendAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friends);

        listView = findViewById(R.id.friends_listview);

        final Context context = this;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                TextView rID = (TextView) view.findViewById(R.id.friendListItemID);
                String recipeID = rID.getText().toString();

                Log.d(TAG, "onItemClick: " + recipeID);

                Intent intent = new Intent(context, ViewRecipeActivity.class);
                intent.putExtra("recipeID", recipeID);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        final Context context = this;
        Database.getMyFriends().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot snapshots) {
                List<User> users = Database.getUsersFromSnapshot(snapshots);

                for(User user : users){
                    Log.d(TAG, "Friend: (" + user.getDocumentID() + ") " + user.getFullName());
                }

                adapter= new FriendAdapter(context, users);

                ListView listView = (ListView) findViewById(R.id.friends_listview);
                listView.setAdapter(adapter);
            }
        });

    }
}
