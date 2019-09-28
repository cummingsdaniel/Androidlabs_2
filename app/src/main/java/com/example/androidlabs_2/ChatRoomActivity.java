package com.example.androidlabs_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {
    ArrayList<Message> chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        chat = new ArrayList<>();
        ListView listings = findViewById(R.id.theList);
        listings.setAdapter();
    }
    private class MyListAdapter extends BaseAdapter {

        public int getCount() {  return chat.size();  } //This function tells how many objects to show

        public String getItem(int position) { return chat.get(position);  }  //This returns the string at position p

        public long getItemId(int p) { return p; } //This returns the database id of the item at position p

        public View getView(int p, View recycled, ViewGroup parent)
        {

            LayoutInflater inflater = getLayoutInflater();
            View thisRow = recycled;

            if(thisRow == null) {
                thisRow = inflater.inflate(R.layout.table_row_layout, null);
            }

            TextView itemField = thisRow.findViewById(R.id.itemField);
            itemField.setText(getItem(p));

            TextView numberField = thisRow.findViewById(R.id.numberField);
            numberField.setText(Integer.toString(p));

            return thisRow;
        }
    }
}
}
