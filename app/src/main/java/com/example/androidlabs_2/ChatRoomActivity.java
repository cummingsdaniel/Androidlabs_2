package com.example.androidlabs_2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {
    ArrayList<Message> chat;
    BaseAdapter theAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        chat = new ArrayList<>();
        EditText mess = (EditText) findViewById(R.id.messageInput);
        ListView listings = findViewById(R.id.theList);
        listings.setAdapter(theAdapter = new MyListAdapter());
        Button sendButton = findViewById(R.id.sendButton);
        Button receiveButton = findViewById(R.id.receiveButton);

        sendButton.setOnClickListener(v -> {
            Log.d("sendButtonClicked", "bbbb");
            Message mleft = new Message(mess.getText().toString(), true);
            chat.add(mleft);
            theAdapter.notifyDataSetChanged();
            mess.setText("");
        });

        receiveButton.setOnClickListener(v -> {
            Log.d("RecButtonClicked", "bbbb");
            Message mright = new Message(mess.getText().toString(), false);
            chat.add(mright);
            theAdapter.notifyDataSetChanged();
            mess.setText("");
        });

    }
    private class MyListAdapter extends BaseAdapter {

        public int getCount() {  return chat.size();  } //This function tells how many objects to show

        public Message getItem(int position) { return chat.get(position);  }  //This returns the string at position p

        public long getItemId(int p) { return p; } //This returns the database id of the item at position p

        public View getView(int p, View recycled, ViewGroup parent)
        {

            LayoutInflater inflater = getLayoutInflater();
            View thisRow = null;

            Log.d("fdfggdg", chat.get(p).toString());


                if(chat.get(p).isSent()) {
                    thisRow = inflater.inflate(R.layout.table_row_left, null);
                }
                else if(!chat.get(p).isSent()){
                    thisRow = inflater.inflate(R.layout.table_row_right, null);
                }

Log.d("gggggggg",getItem(p).getChat());
            TextView itemField = thisRow.findViewById(R.id.itemField);
            itemField.setText(getItem(p).getChat());



            return thisRow;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}


