package com.myapp.cookrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView foodDescription;
        TextView foodTitle;
    ImageView foodImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        foodDescription =  (TextView)findViewById(R.id.txtDescription);
        foodImage = (ImageView)findViewById(R.id.ivImage2);
        foodTitle  = (TextView)findViewById(R.id.txtLink);


        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null){
            foodDescription.setText(mBundle.getString("Description"));
            foodTitle.setText(mBundle.getString("Link"));
            //foodImage.setImageResource(mBundle.getInt("Image"));
            Glide.with(this).load(mBundle.getString("Image")).into(foodImage);

        }
    }
}