package com.rubberband75.recivoir;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class AddFriends extends AppCompatActivity {
    private static final String TAG = "[Recivoir]AddFriends";

    private String email;
    private EditText emailInput;
    private Button searchButton;
    private TextView resultText;

    final Context context  = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);

        email = "";
        emailInput = (EditText) findViewById(R.id.find_friends_email_input);
        searchButton = (Button) findViewById(R.id.find_friends_search_button);
        resultText = (TextView) findViewById(R.id.find_friends_result_text);
    }

    public void search(View view) {
        email = emailInput.getText().toString();
        Log.d(TAG, "search: " + email);

        resultText.setText("");
        searchButton.setEnabled(false);

        if(email.equals("")) {
            return;
        } else if(email.equals(Database.getCurrentUser().getEmail())) {
            Toast.makeText(context, "You Can't Add Yourself", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Searching...", Toast.LENGTH_SHORT).show();


            Database.findUser(email).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot snapshots) {
                    Log.d(TAG, "UsersFound: " + snapshots.size());
                    List<User> users = Database.getUsersFromSnapshot(snapshots);

                    if(!users.isEmpty()) {
                        User user = users.get(0);
                        Log.d(TAG, "Adding Friend: " + user.getFullName());




                    } else {

                        searchButton.setEnabled(true);
                        resultText.setText("User Not Found");
                    }
                }
            });
        }
    }
}
