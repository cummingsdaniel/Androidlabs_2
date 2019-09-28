package com.example.androidlabs_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ProfileActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";
    private ImageButton mImageButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        EditText inputName = findViewById(R.id.editTextName);
        EditText inputEmail = findViewById(R.id.emailEditText);
        SharedPreferences savedEmail = getSharedPreferences("Users", MODE_PRIVATE);
        String emailBox = savedEmail.getString("ReserveEmail", "no Value");
        inputEmail.setText(emailBox);
        Button goToChat = (Button) findViewById(R.id.start_chat);
        goToChat.setOnClickListener(v ->{
            Intent jumpToChat = new Intent(ProfileActivity.this, ChatRoomActivity.class);
            startActivity(jumpToChat);
        });
        mImageButton = (ImageButton) findViewById(R.id.pic_button);
        mImageButton.setOnClickListener(clk -> {
            dispatchTakePictureIntent();
            Log.i("hi", "there");
        });
        Log.d("ProfileActivity", "Email="+emailBox);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageButton.setImageBitmap(imageBitmap);
            Log.e(ACTIVITY_NAME, "in function: onActivityResult");
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e(ACTIVITY_NAME, "in function: onResume");

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e(ACTIVITY_NAME, "in function: onStart");

    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.e(ACTIVITY_NAME, "in function: onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(ACTIVITY_NAME, "in function: onPause ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(ACTIVITY_NAME, "in function: onDestroy");
    }

}
