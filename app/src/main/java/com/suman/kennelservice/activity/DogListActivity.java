package com.suman.kennelservice.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.adaptar.DogListAdaptar;
import com.suman.kennelservice.adaptar.Dogbreedadaptar;
import com.suman.kennelservice.api.MyDogsapi;
import com.suman.kennelservice.model.MyDog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogListActivity extends AppCompatActivity {
RecyclerView rvDogList;
DogListAdaptar dogListAdaptar;
List<MyDog> myDogList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_list);
        rvDogList = findViewById(R.id.rv_dog_list);

        myDogList = new ArrayList<>();
        MyDogsapi myDogsapi = url.getInstance().create(MyDogsapi.class);
        Call<List<MyDog>> myDogCall = myDogsapi.getMydog(url.token);
        myDogCall.enqueue(new Callback<List<MyDog>>() {
            @Override
            public void onResponse(Call<List<MyDog>> call, Response<List<MyDog>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DogListActivity.this, "Code Error" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                myDogList= response.body();
                dogListAdaptar = new DogListAdaptar(DogListActivity.this,myDogList);
                rvDogList.setHasFixedSize(true);
                rvDogList.setAdapter(dogListAdaptar);
                rvDogList.setLayoutManager(new LinearLayoutManager(DogListActivity.this));

            }

            @Override
            public void onFailure(Call<List<MyDog>> call, Throwable t) {

            }
        });

    }
}
