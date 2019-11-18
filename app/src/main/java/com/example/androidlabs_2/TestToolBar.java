package com.example.androidlabs_2;



import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

public class TestToolBar extends AppCompatActivity {
    public String newMessage = "";
    public Snackbar sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tool_bar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        sb = Snackbar.make(myToolbar, "Go Back", Snackbar.LENGTH_LONG)
                .setAction("Back", e -> finish());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "This is the initial message", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                alertBox();
                break;
            case R.id.item3:
                if(newMessage.equals("")) {
                    newMessage = "This is the initial message";
                    sb.show();
                }
                Toast.makeText(this, newMessage, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }return true;
    }
    public void alertBox() {
        View middle = getLayoutInflater().inflate(R.layout.dialog_box, null);
        EditText input = (EditText)middle.findViewById(R.id.view_edit_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("What is your message")
                .setPositiveButton("+", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        newMessage = input.getText().toString();
                    }
                })
                .setNegativeButton("-", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setView(middle);
        builder.create().show();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
