package com.example.androidlabs_2;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.xmlpull.v1.XmlPullParser.END_TAG;
import static org.xmlpull.v1.XmlPullParser.START_TAG;
import static org.xmlpull.v1.XmlPullParser.TEXT;

public class WeatherForecast extends AppCompatActivity {
      ProgressBar progressBar;
      String wind;
      String uv;
      String currentTemperature;
      String min;
      String max;
      Bitmap picOfCurrentWeather;


//    private static final String TEMPERATURE;
//    private static final String UV;
//    private static final String MIN_TEMPERATURE;
//    private static final String MAX_TEMPERATURE;
//    private Bitmap picOfCurrentWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        ImageView weatherImageView = findViewById(R.id.weather_imageView);
        TextView currTempTextView = findViewById(R.id.currentTempTextView);
        TextView minTempTextView = findViewById(R.id.minTempTextView);
        TextView maxTempTextView = findViewById(R.id.maxTempTextView);
        TextView uvRatingTextView = findViewById(R.id.uvRatingTextView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        ForecastQuery startForecast = new ForecastQuery();
        startForecast.execute();
    }
    private class ForecastQuery extends AsyncTask<String, Integer, String> {
    String responseType;

        @Override
        protected String doInBackground(String... strings) {
            String ret = null;
            String queryURL = "hhttp://api.openweathermap.org/data/2.5/uvi?appid=7e943c97096a9784391a981c4d878b22&lat=45.348945&lon=-75.759389";

            try {       // Connect to the server:
                URL url = new URL(queryURL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inStream = urlConnection.getInputStream();

                //Set up the XML parser:
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput( inStream  , "UTF-8");

                //Iterate over the XML tags:
                int EVENT_TYPE;         //While not the end of the document:
                while((EVENT_TYPE = xpp.getEventType()) != XmlPullParser.END_DOCUMENT)
                {
                    switch(EVENT_TYPE)
                    {
                        case START_TAG:         //This is a start tag < ... >
                            String tagName = xpp.getName(); // What kind of tag?
                            if(tagName.equals("temperature")) {
                                currentTemperature = xpp.getAttributeValue(null, "value");  //What is the String associated with message?
                                publishProgress(25, 50, 75);
                                min = xpp.getAttributeValue(null, "min");
                                publishProgress(25, 50, 75);
                                max = xpp.getAttributeValue(null, "max");
                                publishProgress(25, 50, 75);
                            }
                            else if(tagName.equals("weather")) {
                                String weatherIcon = xpp.getAttributeValue(null, "icon");
                                publishProgress(25, 50, 75);
                            }
                            break;
                        case END_TAG:           //This is an end tag: </ ... >
                            break;
                        case TEXT:              //This is text between tags < ... > Hello world </ ... >
                            break;
                    }
                    xpp.next(); // move the pointer to next XML element
                   // Log.i(currentTemperature, min, max);
                }
            }

            catch(MalformedURLException mfe){ ret = "Malformed URL exception"; }
            catch(IOException ioe)          { ret = "IO Exception. Is the Wifi connected?";}
            catch(XmlPullParserException pe){ ret = "XML Pull exception. The XML is not properly formed" ;}
            //What is returned here will be passed as a parameter to onPostExecute:
            return ret;
        }

        @Override                   //Type 3
        protected void onPostExecute(String sentFromDoInBackground) {
            super.onPostExecute(sentFromDoInBackground);
            //update GUI Stuff:
        }

        @Override                       //Type 2
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
}
