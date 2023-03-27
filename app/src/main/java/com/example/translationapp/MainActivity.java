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
            JSONArray countriesArray = countries.names();
            ArrayList<String> countriesArrayList = new ArrayList<String>();

            for(int i = 0; i < countriesArray.length(); i++){
                countriesArrayList.add(countries.getString(countriesArray.getString(i)));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item,
                    countriesArrayList);

            fromLanguageSpinner.setAdapter(adapter);
            toLanguageSpinner.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
                Translate.translate(MainActivity.this, toTextView, fromLanguage, toLanguage, fromEditText.toString());
            }
        });
    }
}