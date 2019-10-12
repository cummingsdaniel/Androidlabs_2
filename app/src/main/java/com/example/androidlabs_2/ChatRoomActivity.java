package com.example.androidlabs_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
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
import android.database.Cursor;


import java.util.ArrayList;
import java.util.Arrays;

public class ChatRoomActivity extends AppCompatActivity {
    ArrayList<Message> chat;
    BaseAdapter theAdapter;
    static final String ACTIVITY_NAME = "CHATROOM_ACTIVITY";

    public void printCursor(Cursor c) {
        Log.i(ACTIVITY_NAME, "version number: " + MyDatabaseOpenHelper.VERSION_NUM);
        Log.i(ACTIVITY_NAME, "Number of columns in curser" +c.getColumnCount());
        Log.i(ACTIVITY_NAME, "Name of Column in curser"+ Arrays.toString(c.getColumnNames()));
        Log.i(ACTIVITY_NAME, "Number of results in curser: " +c.getCount());

        int idColIndex = c.getColumnIndex(MyDatabaseOpenHelper.COL_ID);
        int messageColIndex = c.getColumnIndex(MyDatabaseOpenHelper.COL_MESSAGE);
        int isSentColIndex = c.getColumnIndex(MyDatabaseOpenHelper.IS_SENT);

        while(c.moveToNext() == true) {
            String message = c.getString(messageColIndex);
            Boolean isSent = Boolean.parseBoolean(c.getString(isSentColIndex));
            long idCol = c.getLong(idColIndex);

            Log.i(ACTIVITY_NAME, "id= "+idCol+" "+message+" " + isSent);
            //add Content to the array
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        chat = new ArrayList<>();
        EditText messageTextEdit = (EditText) findViewById(R.id.messageInput);
        ListView listings = findViewById(R.id.theList);
        listings.setAdapter(theAdapter = new MyListAdapter());

        //get data base
        MyDatabaseOpenHelper dbOpener = new MyDatabaseOpenHelper(this);
        SQLiteDatabase db = dbOpener.getWritableDatabase();

        //making a list of columes
        String [] columnsArray = {MyDatabaseOpenHelper.COL_ID, MyDatabaseOpenHelper.COL_MESSAGE, MyDatabaseOpenHelper.IS_SENT};
        //.query is a getter, it gets query from database
        Cursor resultsCursor = db.query(false, MyDatabaseOpenHelper.TABLE_NAME, columnsArray, null, null, null, null, null, null);

        printCursor(resultsCursor);

        int idColIndex = resultsCursor.getColumnIndex(MyDatabaseOpenHelper.COL_ID);
        int messageColIndex = resultsCursor.getColumnIndex(MyDatabaseOpenHelper.COL_MESSAGE);
        int isSentColIndex = resultsCursor.getColumnIndex(MyDatabaseOpenHelper.IS_SENT);

        while(resultsCursor.moveToNext() == true) {
            String message = resultsCursor.getString(messageColIndex);
            Boolean isSent = Boolean.parseBoolean(resultsCursor.getString(isSentColIndex));
            long idCol = resultsCursor.getLong(idColIndex);

            //add Content to the array
            chat.add(new Message(idCol,message, isSent));
        }

        Button sendButton = findViewById(R.id.sendButton);
        Button receiveButton = findViewById(R.id.receiveButton);

        sendButton.setOnClickListener(v -> {

            ContentValues newRowValues = new ContentValues();
            String userInputMessage = messageTextEdit.getText().toString();
            newRowValues.put(MyDatabaseOpenHelper.COL_MESSAGE, userInputMessage);
            newRowValues.put(MyDatabaseOpenHelper.IS_SENT, "true");
            long newId = db.insert(MyDatabaseOpenHelper.TABLE_NAME, null, newRowValues);
            Message mleft = new Message(newId, userInputMessage, true);
            chat.add(mleft);
            theAdapter.notifyDataSetChanged();
            messageTextEdit.setText("");
        });

        receiveButton.setOnClickListener(v -> {
            Log.d("RecButtonClicked", "bbbb");
            ContentValues newRowValues = new ContentValues();
            String userInputMessage = messageTextEdit.getText().toString();
            newRowValues.put(MyDatabaseOpenHelper.COL_MESSAGE, userInputMessage);
            newRowValues.put(MyDatabaseOpenHelper.IS_SENT, "false");
            long newId = db.insert(MyDatabaseOpenHelper.TABLE_NAME, null, newRowValues);

            Message mright = new Message(newId, userInputMessage, false);
            chat.add(mright);
            theAdapter.notifyDataSetChanged();
            messageTextEdit.setText("");
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


