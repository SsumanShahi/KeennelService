package com.suman.kennelservice.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.api.MyDogsapi;
import com.suman.kennelservice.model.MyDog;
import com.suman.kennelservice.model.MyDogCRUD;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogDetailActivity extends AppCompatActivity {

    private Button btn_edit,btn_delete;
private ImageView ivDog;
private TextView tvDogName,tvDogBreed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_detail);
        ivDog= findViewById(R.id.iv_dog);
        tvDogBreed= findViewById(R.id.tv_dog_breed);
        tvDogName= findViewById(R.id.tv_dog_name);

        btn_edit = findViewById(R.id.btn_edit);
        btn_delete = findViewById(R.id.btn_delete);
        final MyDogCRUD myDog= (MyDogCRUD) getIntent().getSerializableExtra("DogDetail");
        tvDogName.setText(myDog.getPetName());
        tvDogBreed.setText(myDog.getBreed());
        final String imgPath = url.imagePath + myDog.getImage();
        try {
            URL url = new URL(imgPath);
            ivDog.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DogDetailActivity.this, EditDogDetailsActivity.class);
                intent.putExtra("DogDetail",myDog);
                startActivity(intent);
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDogsapi myDogsapi = url.getInstance().create(MyDogsapi.class);
                Call<MyDogCRUD> myDogCRUDCall = myDogsapi.deleteMyDog(url.token,myDog.getId());
                myDogCRUDCall.enqueue(new Callback<MyDogCRUD>() {
                    @Override
                    public void onResponse(Call<MyDogCRUD> call, Response<MyDogCRUD> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(DogDetailActivity.this, "Code Error" + response.code(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        startActivity(new Intent(getApplicationContext(),DogListActivity.class));
                    }

                    @Override
                    public void onFailure(Call<MyDogCRUD> call, Throwable t) {
                        Toast.makeText(DogDetailActivity.this, "Something went wrong!! Please Try Again Later!!" , Toast.LENGTH_LONG).show();

                    }
                });

            }
        });

    }
}
