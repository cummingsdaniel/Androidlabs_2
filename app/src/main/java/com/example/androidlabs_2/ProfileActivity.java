package com.example.androidlabs_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

public class ProfileActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        EditText inputName = findViewById(R.id.editTextName);
        EditText inputEmail = findViewById(R.id.emailEditText);
        ImageButton picButton = findViewById(R.id.pic_button);
        SharedPreferences savedEmail = getSharedPreferences("Users", MODE_PRIVATE);
        String emailBox = savedEmail.getString("ReserveEmail", "no Value");
        inputEmail.setText(emailBox);
        Log.d("ProfileActivity", "Email="+emailBox);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

}
