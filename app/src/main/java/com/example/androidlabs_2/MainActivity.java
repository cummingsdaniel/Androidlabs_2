package com.example.androidlabs_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.i(TAG, "inside onCreate method");
//        emailEditText = findViewById(R.id.inputBox);
//        Button loginButton = (Button)findViewById(R.id.button);
//        if(loginButton != null){
//            loginButton.setOnClickListener(clk -> {
//
//            });
//        }
//
   }

    @Override
    protected void onStart() {
        super.onStart();
//        Log.i(TAG, "inside onStart method");

    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.i(TAG, "inside onResume method");

    }

    @Override
    protected void onStop() {
        super.onStop();
//        Log.i(TAG, "inside onStop method");

    }

    @Override
    protected void onPause() {
        super.onPause();
//        Log.i(TAG, "inside onPause method");
        SharedPreferences pref = getSharedPreferences("")
//        SharedPreferences.Editor usersEmail
//        SharedPreferences usersEmail = new Intent(emailEditText.);
//        Intent autoFillEmail = new Intent(emailEditText.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Log.i(TAG, "inside onDestroy method");

    }
}
