package com.chat.project;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class second extends AppCompatActivity {

    private static final String API_KEY = "36a110e72dmshb5e790f180e7823p1ff3eejsn1c100142331c";

    private Spinner currencySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView from_cur = findViewById(R.id._currency_Name_TextView);
        EditText value=findViewById(R.id._value);
        Button more=findViewById(R.id._more_Info_Button);
        Button back=findViewById(R.id.bac);
        currencySpinner = findViewById(R.id.spinner1);

        // Set up the spinner with currency options

        List<String> currencyOptions = Arrays.asList(
                "AED", "AFN", "ALL", "ARS", "AUD", "BDT", "BGN", "BHD", "BOB", "BRL", "CAD", "CHF", "CLP", "CNY",
                "COP", "CRC", "CZK", "DKK", "DZD", "EGP", "EUR", "FJD", "GBP", "GEL", "GHS", "HKD", "HRK", "HUF",
                "IDR", "ILS", "INR", "IQD", "ISK", "JOD", "JPY", "KES", "KRW", "KWD", "KZT", "LBP", "LKR", "MAD",
                "MDL", "MMK", "MNT", "MOP", "MXN", "MYR", "NGN", "NOK", "NZD", "PEN", "PHP", "PKR", "PLN", "PYG",
                "QAR", "RON", "RSD", "RUB", "SAR", "SEK", "SGD", "THB", "TRY", "TWD", "TZS", "UAH", "USD", "VEF",
                "VND", "ZAR"
        );


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencyOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySpinner.setAdapter(adapter);

        // Execute the currency conversion request
        ConvertCurrencyTask convertCurrencyTask = new ConvertCurrencyTask();
        convertCurrencyTask.execute();
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent =new Intent(second.this,third.class);
                startActivity(myintent);
                    finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(second.this,search.class);
                startActivity(myintent);
                finish();
            }
        });
    }

    private class ConvertCurrencyTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String response = null;
            HttpURLConnection urlConnection = null;

            try {
                String targetCurrency = currencySpinner.getSelectedItem().toString();

                // Create the URL with the endpoint
                URL url = new URL("https://community-neutrino-currency-conversion.p.rapidapi.com/convert?from-value=10&from-type=USD&to-type=" + targetCurrency);

                // Open the connection
                urlConnection = (HttpURLConnection) url.openConnection();

                // Set the headers
                urlConnection.setRequestProperty("X-RapidAPI-Key", API_KEY);
                urlConnection.setRequestProperty("X-RapidAPI-Host", "community-neutrino-currency-conversion.p.rapidapi.com");
                urlConnection.setRequestMethod("GET");

                // Get the response
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    response = stringBuilder.toString();
                } else {
                    response = "Error: " + responseCode;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if (response != null) {
                Log.d("CurrencyConversion", response);

                // Process the response and update UI accordingly
                try {
                    JSONObject json = new JSONObject(response);

                    // Parse the JSON response to get the converted value
                    double convertedValue = json.getDouble("result");

                    // Update your UI components with the converted value

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                // Handle the case where response is null or an error occurred during the API call
            }
        }

    }
}
