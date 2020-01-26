package com.suman.kennelservice.api;

import com.suman.kennelservice.model.Dogbreeds;
import com.suman.kennelservice.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface DogBreedapi {

    @GET("dogbreed")
    Call<List<Dogbreeds>> getdogbreeds();


    @GET("dogbreed")
    Call<Dogbreeds> getdogbreedimage(@Header("Authorization")String token);

    @GET("dogbreed/notes")
    Call<Dogbreeds> getdogdetail(@Header("Authorization") String token);
}