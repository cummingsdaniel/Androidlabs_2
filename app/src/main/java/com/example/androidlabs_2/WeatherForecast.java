package com.example.androidlabs_2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WeatherForecast extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        ImageView weatherImageView = findViewById(R.id.weather_imageView);
        TextView currTempTextView = findViewById(R.id.currentTempTextView);
        TextView minTempTextView = findViewById(R.id.minTempTextView);
        TextView maxTempTextView = findViewById(R.id.maxTempTextView);
        TextView uvRatingTextView = findViewById(R.id.uvRatingTextView);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }
    private class ForecastQuery extends AsyncTask<String, Integer, String> {

}
}
