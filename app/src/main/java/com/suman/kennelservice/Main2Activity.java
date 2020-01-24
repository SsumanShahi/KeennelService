package com.suman.kennelservice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {

    private ImageView card1;
    private TextView tvdogname,tvdogdescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        card1 = findViewById(R.id.card1);
        tvdogname = findViewById(R.id.tvdogname);
        tvdogdescription = findViewById(R.id.tvdogdescription);

        String dogName = getIntent().getStringExtra("DogName");
        URL url = null;
        try {
            url = new URL(getIntent().getStringExtra("ImageUrl"));
            card1.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
            tvdogname.setText(dogName);
            tvdogdescription.setText(getIntent().getStringExtra("DogDescription"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Log.d("Main2Activity","This is dog name "+dogName);
    }
}
