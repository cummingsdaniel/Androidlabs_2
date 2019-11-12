package com.example.androidlabs_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    String weatherIcon;
    Bitmap picOfCurrentWeather;
    ImageView weatherImageView;
    TextView currTempTextView;
    TextView minTempTextView;
    TextView maxTempTextView;
    TextView uvRatingTextView;
      public boolean fileExistance(String fname){
          File file = getBaseContext().getFileStreamPath(fname);
          return file.exists();   }



    //    private static final String TEMPERATURE;
//    private static final String UV;
//    private static final String MIN_TEMPERATURE;
//    private static final String MAX_TEMPERATURE;
//    private Bitmap picOfCurrentWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        weatherImageView = findViewById(R.id.weather_imageView);
        currTempTextView = findViewById(R.id.currentTempTextView);
        minTempTextView = findViewById(R.id.minTempTextView);
        maxTempTextView = findViewById(R.id.maxTempTextView);
        uvRatingTextView = findViewById(R.id.uvRatingTextView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        ForecastQuery startForecast = new ForecastQuery();
        startForecast.execute();
    }
    private class ForecastQuery extends AsyncTask<String, Integer, String> {
    String responseType;

        @Override
        protected String doInBackground(String... strings) {
            picOfCurrentWeather = null;
            String ret = null;
            String queryURL = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=7e943c97096a9784391a981c4d878b22&mode=xml&units=metric";
            String queryURL2 = "http://api.openweathermap.org/data/2.5/uvi?appid=7e943c97096a9784391a981c4d878b22&lat=45.348945&lon=-75.759389";
            URL url;
            URL urlJSON;
            try {



                // Connect to the server:
                url = new URL(queryURL);
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
                                publishProgress(25);
                                min = xpp.getAttributeValue(null, "min");
                                publishProgress(50);
                                max = xpp.getAttributeValue(null, "max");
                                publishProgress( 75);
                            }
                            else if(tagName.equals("wind")) {
                                xpp.next();
                                wind = xpp.getAttributeValue(null, "value");

                            }
                            else if(tagName.equals("weather")) {
                                String weatherIcon = xpp.getAttributeValue(null, "icon");
                                String urlString = "http://openweathermap.org/img/w/" + weatherIcon + ".png";


                                if(!fileExistance(weatherIcon + ".png")){
                                    Log.i("file not found", weatherIcon + ".png");
                                    //step 9
                                    URL url2 = new URL(urlString);
                                    HttpURLConnection stringUrlConnection = (HttpURLConnection) url2.openConnection();
                                    stringUrlConnection.connect();
                                    int responseCode = stringUrlConnection.getResponseCode();
                                    if (responseCode == 200) {
                                        picOfCurrentWeather = BitmapFactory.decodeStream(stringUrlConnection.getInputStream());
                                    }

                                    //step 9
                                    FileOutputStream outputStream = openFileOutput(weatherIcon + ".png", Context.MODE_PRIVATE);
                                    picOfCurrentWeather.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                                    outputStream.flush();
                                    outputStream.close();
                                    publishProgress(100);
                                }else{
                                    FileInputStream fis = null;
                                    try {
                                        fis = openFileInput(weatherIcon + ".png");   }
                                    catch (FileNotFoundException e) {    e.printStackTrace();  }
                                    picOfCurrentWeather = BitmapFactory.decodeStream(fis);

                                }
                            }
                            break;
                        case END_TAG:           //This is an end tag: </ ... >
                            break;
                        case TEXT:              //This is text between tags < ... > Hello world </ ... >
                            break;
                    }
                    xpp.next(); // move the pointer to next XML element
//json:
                    // Connect to the server:
                     urlJSON = new URL(queryURL2);
                     urlConnection = (HttpURLConnection) urlJSON.openConnection();
                     inStream = urlConnection.getInputStream();

// json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    String result = sb.toString();
                    JSONObject jObject = new JSONObject(result);
                    double value = jObject.getDouble("value");
                    uv = String.valueOf(value);

                }

             //done xml




            }

            catch(MalformedURLException mfe){ ret = "Malformed URL exception"; }
            catch(IOException ioe)          { ret = "IO Exception. Is the Wifi connected?";}
            catch(XmlPullParserException pe){ ret = "XML Pull exception. The XML is not properly formed" ;}
            catch (JSONException e) { ret = "JSON exception";
                e.printStackTrace();
            }


            //What is returned here will be passed as a parameter to onPostExecute:
            return ret;
        }

        @Override                   //Type 3
        protected void onPostExecute(String sentFromDoInBackground) {
            super.onPostExecute(sentFromDoInBackground);
            weatherImageView.setImageBitmap(picOfCurrentWeather);
            currTempTextView.setText(currentTemperature);
            minTempTextView.setText(min);
            maxTempTextView.setText(max);
            uvRatingTextView.setText(uv);
            progressBar .setVisibility(View.INVISIBLE);
            //update GUI Stuff:

        }

        @Override                       //Type 2
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(values[0]);
            //Update GUI stuff only:
        }
    }
}
