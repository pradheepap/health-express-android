package com.sg.healthexpress.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView secondaryTextView = findViewById(R.id.secondaryText);

        // Capture the layout's TextView and set the string as its text
        final TextView supportingTextView = findViewById(R.id.supportingText);
        final TextView nutrientNameTextView = findViewById(R.id.nutrientName);
        final TextView nutrientValueTextView = findViewById(R.id.nutrientValue);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.nal.usda.gov/fdc/v1/foods/search?api_key=DEMO_KEY&query="+message;
/*        Map<String, String> params = new HashMap();
        params.put("query", "Cheddar cheese");
        JSONObject parameters = new JSONObject(params);*/

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String jsonString = response.get("foods").toString();
                            Log.d(jsonString, "Food Info.jsonString.");

                            Gson gson = new Gson();
                            Type foodInfoList = new TypeToken<ArrayList<FoodInfo>>() {
                            }.getType();

                            List<FoodInfo> foodInfo = gson.fromJson(jsonString, foodInfoList);
                            Log.d(foodInfo.get(0).getIngredients(), "Food Info..");

                            Log.d(foodInfo.get(0).getNutrientsList().toString(), "Food Info getNutrientsList..");


                            List<Nutrients> nutrientsList = foodInfo.get(0).getNutrientsList();
                            Nutrients nutrient_1 = nutrientsList.get(0);
                            Log.d(nutrient_1.toString(), "Food Info nutrient_1..");


                            supportingTextView.setText("Ingredients: " + foodInfo.get(0).getIngredients());
                            nutrientNameTextView.setText(nutrient_1.getNutrientName() + ": " + nutrient_1.getValue());
                            nutrientValueTextView.setText("Description: " + foodInfo.get(0).getDescription());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        supportingTextView.setText("Response: " + error.toString());
                    }
                });

        HttpRequestInstance.getInstance(this).addToRequestQueue(jsonObjectRequest);
        secondaryTextView.setText(message.toUpperCase());
    }
}