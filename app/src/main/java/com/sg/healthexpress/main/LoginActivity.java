package com.sg.healthexpress.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sg.healthexpress.http.HttpRequestInstance;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Button button = findViewById(R.id.buttonView);

        // Capture the layout's TextView and set the string as its text
        final TextView textView = findViewById(R.id.textView);
        // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.nal.usda.gov/fdc/v1/foods/search?api_key=DEMO_KEY&query="+message;
        Map<String, String> params = new HashMap();
        params.put("query", "Cheddar cheese");
        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                            textView.setText("Response: " + response.toString());

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
        HttpRequestInstance.getInstance(this).addToRequestQueue(jsonObjectRequest);
        button.setText(message);
    }
}