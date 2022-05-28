package com.excample.recipeapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private RequestQueue requestQueue;
    private List<recipe> recipeList;
    private List<recipe> resultList;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        recipeList =new ArrayList<>();
        resultList =new ArrayList<>();
        fetchRecipe();
        this.queryFirebase();

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        System.out.println(email);
    }
    public void onclickfavoritemenu(View view){
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://recipeapplication-70c65-default-rtdb.firebaseio.com/");
        DatabaseReference myRef= database.getReference("User/"+email);
        User user = new User();
        user.getMenu().add(new String());
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
                       this.sampleCreateFirebase(recipeList);

                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }
            }

            private void sampleCreateFirebase(List<recipe> recipeList) {
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://recipeapplication-70c65-default-rtdb.firebaseio.com/");
                DatabaseReference myRef= database.getReference("recipe");
                for(int i =0 ;i <recipeList.size();i++){
                    recipe recipea = new recipe(recipeList.get(i).getCalories(),recipeList.get(i).getCarbos(),recipeList.get(i).getDescription(),recipeList.get(i).getDifficulty(),recipeList.get(i).getFats(),recipeList.get(i).getHeadline(),
                            recipeList.get(i).getId(),recipeList.get(i).getImage(),recipeList.get(i).getName(),recipeList.get(i).getProteins(),recipeList.get(i).getThumb(),recipeList.get(i).getTime());
                    DatabaseReference stu1 = myRef.child(recipea.getId());
                    stu1.setValue(recipea);
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
    private void queryFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://recipeapplication-70c65-default-rtdb.firebaseio.com/");
        DatabaseReference myRef= database.getReference("recipe");
        Query query1 = myRef.orderByValue();
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            String names ;
            String caloriess = "";
            String Images = "";
            String carbss = "";
            String descriptions = "";
            String difficultys = "";
            String fatss = "";
            String headlines = "";
            String ids = "";
            String proteinss = "";
            String thumbs = "";
            String times ="";
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List ss = null;
                for(DataSnapshot ds : snapshot.getChildren()){
//                    Log.d("data : ",ds.getKey()+" "+ds.getValue());
//                    Listr= (List<String>) ds.getValue();
                    String key = ds.getKey();
                    names = ds.child("name").getValue().toString();
                    caloriess =ds.child("calories").getValue().toString();
                    Images =ds.child("image").getValue().toString();
                    carbss =ds.child("carbos").getValue().toString();
                    descriptions =ds.child("description").getValue().toString();
                    difficultys =ds.child("difficulty").getValue().toString();
                    fatss =ds.child("fats").getValue().toString();
                    headlines =ds.child("headline").getValue().toString();
                    ids =ds.child("id").getValue().toString();
                    proteinss =ds.child("proteins").getValue().toString();
                    thumbs =ds.child("thumb").getValue().toString();
                    times =ds.child("time").getValue().toString();

                    recipe rr = new recipe(caloriess,carbss,descriptions, difficultys, fatss, headlines, ids, Images, names, proteinss, thumbs, times);
                    resultList.add(rr);
                }
                recipeAdepter adepter =new recipeAdepter(MainActivity.this,resultList);
                recyclerview.setAdapter(adepter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}