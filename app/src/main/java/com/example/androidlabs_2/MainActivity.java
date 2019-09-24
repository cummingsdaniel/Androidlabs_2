package com.example.androidlabs_2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText emailInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailInput = (EditText) findViewById(R.id.emailEditText);
        SharedPreferences saved = getSharedPreferences("Users", Context.MODE_PRIVATE);
        String emailBox = saved.getString("ReserveEmail","");
        emailInput.setText(emailBox);

        Button loginButton = (Button) findViewById(R.id.login_button);
        if (loginButton != null) {
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent jumpToProfile = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(jumpToProfile);
                }
            });
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","on Pause");
        SharedPreferences saved = getSharedPreferences("Users", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = saved.edit();
        edit.putString("ReserveEmail", emailInput.getText().toString());
        edit.apply();
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
