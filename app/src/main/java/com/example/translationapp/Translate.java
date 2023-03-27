package com.example.translationapp;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Translate {
    static void translate(Context context, TextView textView, String fromLanguage, String toLanguage, String text){
        // String apiKey = "86e153d27dmsh0903348e6d64167p1ecfe4jsnd950aa28fece";
        String url = "https://rapid-translate-multi-traduction.p.rapidapi.com/t";

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getLocalizedMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("from", "hu");
                params.put("to", "it");
                params.put("e", "");
                params.put("q", "szia");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("X-RapidAPI-Key", "86e153d27dmsh0903348e6d64167p1ecfe4jsnd950aa28fece");
                headers.put("X-RapidAPI-Host", "rapid-translate-multi-traduction.p.rapidapi.com");
                headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 7.0) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Focus/1.0 Chrome/59.0.3029.83 Mobile Safari/537.36");

                return headers;
            }
        };
        queue.add(request);
    }
}
