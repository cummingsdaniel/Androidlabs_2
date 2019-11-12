package com.example.androidlabs_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

public class TestToolBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tool_bar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setActionBar(myToolbar);
    }
}
