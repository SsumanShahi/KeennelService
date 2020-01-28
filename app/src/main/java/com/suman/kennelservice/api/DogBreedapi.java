package com.suman.kennelservice.api;

import com.suman.kennelservice.model.Dogbreeds;
import com.suman.kennelservice.model.ProductClass;
import com.suman.kennelservice.model.Puppy;
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



    //forproduct
    @GET("product")
    Call<List<ProductClass>> getproducts();

    @GET("product")
    Call<ProductClass> getproductimage(@Header("Authorization")String token);

//for puppy
    @GET("puppy")
    Call<List<Puppy>> getpuppy();


    @GET("puppy")
    Call<Puppy> getpuppyimage(@Header("Authorization")String token);


}
