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

        ArrayAdapter<String> fromAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[] {"a","b","c"});
        fromLanguageSpinner.setAdapter(fromAdapter);

        ArrayAdapter<String> toAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[] {"A","B","C"});
        toLanguageSpinner.setAdapter(toAdapter);

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
                Toast.makeText(MainActivity.this, String.format("Translate %s from %s to %s", fromEditText.getText(), fromLanguage, toLanguage), Toast.LENGTH_SHORT).show();
            }
        });
    }
}