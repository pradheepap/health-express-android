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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
//        String url ="https://api.nal.usda.gov/fdc/v1/foods/search?api_key=DEMO_KEY&query="+message;
        String url = "https://7y72io6h6d.execute-api.ap-southeast-1.amazonaws.com/dev/food/search/"+ message;
/*        Map<String, String> params = new HashMap();
        params.put("query", "Cheddar cheese");
        JSONObject parameters = new JSONObject(params);*/

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String dataJson = response.get("data").toString();

                            Gson gson = new Gson();
                            JsonElement json = gson.fromJson(dataJson, JsonElement.class);
                            String jsonInString = gson.toJson(json);
                            Log.d("Json", jsonInString);

                            JsonObject jsonObject = json.getAsJsonObject();
                            JsonElement foodsjson = jsonObject.get("foods");
                            Log.d("foodsjson", foodsjson.toString());

                            FoodInfo foodInfo = gson.fromJson(foodsjson.toString(), FoodInfo.class);
                            Log.d("foodInfo", foodInfo.toString());

                            List<Nutrients> nutrientsList = foodInfo.getNutrientsList();
                            String message = "";
                            for (Nutrients nutrient : nutrientsList) {
                                Log.d("getNutrientName", nutrient.getNutrientName());
                                Log.d("getValue", nutrient.getValue());
                                message += nutrient.getNutrientName() + ": " + nutrient.getValue() + "\n";
                            }

                            supportingTextView.setText("BrandOwner: " + foodInfo.getBrandOwner());
                            nutrientNameTextView.setText("Ingredients: " + foodInfo.getIngredients());
                            nutrientValueTextView.setText(message);


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