package com.myapp.cookrecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<FoodData> myFoodList;
    FoodData mFoodData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycleView);

        GridLayoutManager gridLayoutManager =  new GridLayoutManager(MainActivity.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        myFoodList = new ArrayList<>();

        mFoodData = new FoodData("Giouvarlakia", "A low carb version of a traditional hearty Greek dish","Rs.400", "https://youtu.be/DID-8ytqMMY",R.drawable.image1);
        myFoodList.add(mFoodData);
        mFoodData = new FoodData("Grand Marnier & Coffee Ice Cream", "Easy and decadent!","Rs.400", "https://youtu.be/AMMECm7Huhk",R.drawable.image2);
        myFoodList.add(mFoodData);

        MyAdapter myAdapter =  new MyAdapter(MainActivity.this,myFoodList);
        mRecyclerView.setAdapter(myAdapter);
    }


}