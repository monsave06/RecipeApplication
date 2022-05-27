package com.excample.recipeapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

         ImageView imageView = findViewById(R.id.imagefood);
        TextView nameF = findViewById(R.id.Textname);
        TextView headlineF = findViewById(R.id.Textheadline);
        TextView descriptionF = findViewById(R.id.Textdescription);
        TextView fatsF = findViewById(R.id.Textfats);
        TextView carbF = findViewById(R.id.Textcarbos);
        TextView caloriesF = findViewById(R.id.Textcalories);

        Bundle bundle = getIntent().getExtras();
        String names = bundle.getString("name");
        String caloriess = bundle.getString("calories");
        String Images = bundle.getString("Image");
        String carbss = bundle.getString("carbs");
        String descriptions = bundle.getString("description");
        String difficultys = bundle.getString("difficulty");
        String fatss = bundle.getString("fats");
        String headlines = bundle.getString("headline");
        String ids = bundle.getString("id");
        String proteinss = bundle.getString("proteins");
        String thumbs = bundle.getString("thumb");
        String times = bundle.getString("time");

         System.out.println("xxxxxxxxxxxxxxxxxx "+bundle);
//        System.out.println("xxxxxxxxxxxxxxxxxx "+carbss);
        Glide.with(this).load(Images).into(imageView);
        nameF.setText(names);
        headlineF.setText(headlines);
        descriptionF.setText(descriptions);
        fatsF.setText(fatss);
        carbF.setText(carbss);
        caloriesF.setText(caloriess);




    }
}