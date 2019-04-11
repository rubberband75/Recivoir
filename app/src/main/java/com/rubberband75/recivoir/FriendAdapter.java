package com.rubberband75.recivoir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public  class FriendAdapter extends ArrayAdapter<User> {
    public FriendAdapter(Context context, List<User> friends){
        super(context, 0, friends);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User friend = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.friend_list_item, parent, false);
        }
        // Lookup view for data population
        TextView recipeTitle = (TextView) convertView.findViewById(R.id.friendListItemName);
        TextView recipeID = (TextView) convertView.findViewById(R.id.friendListItemID);

        // Populate the data into the template view using the data object
        recipeTitle.setText(friend.getFullName());
        recipeID.setText(friend.getUserID());

        // Return the completed view to render on screen
        return convertView;
    }
}