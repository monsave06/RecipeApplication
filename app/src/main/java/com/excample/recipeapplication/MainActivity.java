package com.excample.recipeapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private RequestQueue requestQueue;
    private List<recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        recipeList =new ArrayList<>();
        fetchRecipe();
    }

    private void fetchRecipe() {
        String url="https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/recipes.json";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               for(int i= 0 ;i <response.length();i++){
//                   System.out.println(response);
                   try {
                       JSONObject jsonObject =response.getJSONObject(i);
                         String name = jsonObject.getString("name");
                         String calories = jsonObject.getString("calories");
                         String image = jsonObject.getString("image");
                         String carbos = jsonObject.getString("carbos");
                         String description = jsonObject.getString("description");
                         String difficulty = jsonObject.getString("difficulty");
                         String fats = jsonObject.getString("fats");
                         String headline = jsonObject.getString("headline");
                         String id = jsonObject.getString("id");
                         String proteins = jsonObject.getString("proteins");
                         String thumb = jsonObject.getString("thumb");
                         String time = jsonObject.getString("time");

                          recipe recipeA =new recipe(calories,carbos,description,difficulty,
                                  fats,headline,id,image,name,proteins,thumb,time);
                          recipeList.add(recipeA);
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }

                   recipeAdepter adepter =new recipeAdepter(MainActivity.this,recipeList);

                   recyclerview.setAdapter(adepter);
               }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
}