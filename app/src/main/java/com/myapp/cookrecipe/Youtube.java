package com.myapp.cookrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class Youtube extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
    }
    protected void onResume() {
        super.onResume();
        TextView t = findViewById(R.id.txtMessage);
        String message = getIntent().getStringExtra(MainActivity.MESSAGE);
        t.setText(message);
    }
}