package com.example.translationapp;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Translate {
    static void translate(Context context, TextView textView, String fromLanguage, String toLanguage, String text){
        String apiKey = "86e153d27dmsh0903348e6d64167p1ecfe4jsnd950aa28fece";
        String url = "https://text-translator2.p.rapidapi.com/translate";

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responeObj = new JSONObject(response);
                    textView.setText(responeObj.getJSONObject("data").getString("translatedText"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("X-RapidAPI-Key", apiKey);
                headers.put("X-RapidAPI-Host", "text-translator2.p.rapidapi.com");
                return headers;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("source_language", fromLanguage);
                params.put("target_language", toLanguage);
                params.put("text", text);
                return params;
            }
        };
        queue.add(request);
    }
}
