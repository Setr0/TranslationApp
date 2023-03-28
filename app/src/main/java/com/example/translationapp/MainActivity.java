package com.example.translationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText fromEditText;
    TextView toTextView;
    Spinner fromLanguageSpinner;
    Spinner toLanguageSpinner;
    Button translateButton;

    String fromLanguage;
    String toLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromEditText = findViewById(R.id.fromEditText);
        toTextView = findViewById(R.id.toTextView);
        fromLanguageSpinner = findViewById(R.id.fromLanguageSpinner);
        toLanguageSpinner = findViewById(R.id.toLanguageSpinner);
        translateButton = findViewById(R.id.translateButton);

        try {
            JSONObject countries = Countries.data();
            JSONArray countriesNamesArray = countries.names();
            ArrayList<String> countriesNamesArrayList = new ArrayList<String>();

            for(int i = 0; i < countriesNamesArray.length(); i++){
                countriesNamesArrayList.add(countriesNamesArray.get(i).toString());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item,
                    countriesNamesArrayList);

            fromLanguageSpinner.setAdapter(adapter);
            toLanguageSpinner.setAdapter(adapter);

            fromLanguageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    fromLanguage = fromLanguageSpinner.getSelectedItem().toString();
                    // Toast.makeText(MainActivity.this, fromLanguage, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Toast.makeText(MainActivity.this, "Nothing selected", Toast.LENGTH_SHORT).show();
                }
            });

            toLanguageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    toLanguage = toLanguageSpinner.getSelectedItem().toString();
                    // Toast.makeText(MainActivity.this, toLanguage, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Toast.makeText(MainActivity.this, "Nothing selected", Toast.LENGTH_SHORT).show();
                }
            });

            translateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String fromLanguageCode = null;
                    try {
                        fromLanguageCode = countries.getString(fromLanguage);
                        String toLanguageCode = countries.getString(toLanguage);

                        Translate.translate(MainActivity.this, toTextView, fromLanguageCode, toLanguageCode, fromEditText.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}