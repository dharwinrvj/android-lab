package com.yash.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView resultTextView;

//  b6be40a27cb94c277bc4feb6004a04be == my_open_weather_map_key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        resultTextView = findViewById(R.id.resultTextView);
    }

    public void getWeather(View view) {
        try {
            DownloadTask task = new DownloadTask();
            String encodedCityName = URLEncoder.encode(editText.getText().toString(), "UTF-8");
            Log.println(Log.DEBUG,"hello",encodedCityName);
            task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + encodedCityName + "&appid=9d788cb5e0afc9957f27d8fc6eb1e699");

            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();

            Thread thread = new Thread(){
                public void run(){
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Stuck in getweather() :(", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
            thread.start();
        }
    }

    public class DownloadTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                return result;

            } catch (Exception e) {
                e.printStackTrace();

                Thread thread = new Thread(){
                    public void run(){
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Stuck in downloadtask_class :(", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                };
                thread.start();

                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);

                String weatherInfo = jsonObject.getString("weather");
                String temppressInfo = jsonObject.getString("main");
                String mainInfo = "["+temppressInfo+"]";
                Log.i("Weather content", weatherInfo);

                JSONArray weath = new JSONArray(weatherInfo);
                JSONArray tempinfo = new JSONArray(mainInfo);

                String message = "";

                for (int i = 0; i < weath.length(); i++) {
                    JSONObject jsonPart = weath.getJSONObject(i);

                    String main = jsonPart.getString("main");
                    String description = jsonPart.getString("description");

                    if (!main.equals("") && !description.equals("")) {
                        message += "Main : "+main+ "\nDescription : " + description + "\r\n";
                    }
                }

                for (int i = 0; i < tempinfo.length(); i++) {
                    JSONObject jsonPart = tempinfo.getJSONObject(i);

                    String temp = jsonPart.getString("temp");
                    String pressure = jsonPart.getString("pressure");
                    String humidity = jsonPart.getString("humidity");
//
                    if (!temp.equals("") && !pressure.equals("")) {
                        message += "Temperature : " + temp + " K\nPressure : "+pressure+" Pa\nHumidity : "+humidity+" %\r\n";
                    }

                }

                if (!message.equals("")) {
                    resultTextView.setText(message);
                } else {

                    Thread thread = new Thread(){
                        public void run(){
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "No text entered :(", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    };
                    thread.start();

                }

            } catch (Exception e) {

                Thread thread = new Thread(){
                    public void run(){
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Could not find weather :(", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                };
                thread.start();

                e.printStackTrace();
            }

        }
    }


}
