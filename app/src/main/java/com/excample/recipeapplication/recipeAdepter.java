package com.excample.recipeapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.*;

public class recipeAdepter extends RecyclerView.Adapter<recipeAdepter.recipeHolder> {
    private Context context;
    private List<recipe> recipeList;
    private List<recipe> Listr ;
    private List<recipe> recipett;
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

    public recipeAdepter(Context context,List<recipe> recipes){
        this.context = context;
        recipeList = recipes;
    }

    @NonNull
    @Override
    public recipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item ,parent,false);
        return new recipeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recipeHolder holder, int position) {
//           this.sampleCreateFirebase(recipeList);
//          Listr = queryFirebase();
//           System.out.println("wwwwwwwwwww = "+Listr);
          recipe recipe1 =recipeList.get(position);
          holder.name.setText(recipe1.getName());
          holder.calories.setText(recipe1.getCalories());
        Glide.with(context).load(recipe1.getImage()).into(holder.imageView);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(context, DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("name",recipe1.getName());
                bundle.putString("calories",recipe1.getCalories());
                bundle.putString("Image",recipe1.getImage());
                bundle.putString("carbs",recipe1.getCarbos());
                bundle.putString("description",recipe1.getDescription());
                bundle.putString("difficulty",recipe1.getDifficulty());
                bundle.putString("fats",recipe1.getFats());
                bundle.putString("headline",recipe1.getHeadline());
                bundle.putString("id",recipe1.getId());
                bundle.putString("proteins",recipe1.getProteins());
                bundle.putString("thumb",recipe1.getThumb());
                bundle.putString("time",recipe1.getTime());

                intents.putExtras(bundle);
//                System.out.println("aaaaaaaaaaaaaaaaaa "+intents);
                context.startActivity(intents);
            }

        });
    }


    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class recipeHolder extends RecyclerView.ViewHolder{
         ImageView imageView ;
         TextView name ,calories;
         LinearLayout layout;

        public recipeHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.Viewimage);
            name = itemView.findViewById(R.id.foodname);
            calories = itemView.findViewById(R.id.foodcalories);
            layout = itemView.findViewById(R.id.mainlayout);
        }
    }
}
