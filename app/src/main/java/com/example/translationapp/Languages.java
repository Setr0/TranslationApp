package com.example.translationapp;

import android.content.Context;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Languages {
    static void data(Context context, Spinner fromLanguageSpinner, Spinner toLanguageSpinner, Button translateButton, EditText fromEditText, TextView toTextView) throws JSONException {
        String apiKey = "86e153d27dmsh0903348e6d64167p1ecfe4jsnd950aa28fece";
        String url = "https://text-translator2.p.rapidapi.com/getLanguages";

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseObj = new JSONObject(response);
                    JSONArray languages = responseObj.getJSONObject("data").getJSONArray("languages");

                    JSONObject languagesObj = new JSONObject();
                    ArrayList<String> languagesNames = new ArrayList<String>();
                    for(int i = 0; i < languages.length(); i++){
                        languagesObj.put(languages.getJSONObject(i).getString("name"), languages.getJSONObject(i).getString("code"));
                        languagesNames.add(languages.getJSONObject(i).getString("name"));
                    }

                    Collections.sort(languagesNames);

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                            languagesNames);
                    fromLanguageSpinner.setAdapter(adapter);
                    toLanguageSpinner.setAdapter(adapter);

                    for(int i = 0; i < adapter.getCount(); i++){
                        if(languagesObj.getString(adapter.getItem(i)).equals(Locale.getDefault().getLanguage())){
                            fromLanguageSpinner.setSelection(i);
                        }

                        if(adapter.getItem(i).equals("English")){
                            toLanguageSpinner.setSelection(i);
                        }
                    }

                    if(toLanguageSpinner.getSelectedItem().equals(fromLanguageSpinner.getSelectedItem())){
                        Random random = new Random();
                        int randomNumber = random.nextInt(adapter.getCount());
                        toLanguageSpinner.setSelection(randomNumber);
                    }

                    translateButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(fromEditText.getText().toString().replace(" ", "").length() == 0){
                                Toast.makeText(context, "Empty input", Toast.LENGTH_SHORT).show();

                                return;
                            }

                            String fromLanguageCode = fromLanguageSpinner.getSelectedItem().toString();
                            String toLanguageCode = toLanguageSpinner.getSelectedItem().toString();

                            if(fromLanguageCode.equals(toLanguageCode)) return;
                            Translate.translate(context, toTextView, fromLanguageCode, toLanguageCode, fromEditText.getText().toString().toLowerCase());
                        }
                    });

                    System.out.println();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

                headers.put("X-RapidAPI-Key", apiKey);
                headers.put("X-RapidAPI-Host", "text-translator2.p.rapidapi.com");

                return headers;
            }
        };
        queue.add(request);
    }
}
