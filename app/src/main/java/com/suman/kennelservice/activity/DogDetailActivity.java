package com.suman.kennelservice.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.suman.kennelservice.R;
import com.suman.kennelservice.model.MyDog;

public class DogDetailActivity extends AppCompatActivity {
private ImageView ivDog;
private TextView tvDogName,tvDogBreed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_detail);
        ivDog= findViewById(R.id.iv_dog);
        tvDogBreed= findViewById(R.id.tv_dog_breed);
        tvDogName= findViewById(R.id.tv_dog_name);
        MyDog myDog= (MyDog) getIntent().getSerializableExtra("DogDetail");
        tvDogName.setText(myDog.getPetName());
        tvDogBreed.setText(myDog.getBreed());

    }
}
