package com.suman.kennelservice.api;

import com.suman.kennelservice.model.Dogbreeds;
import com.suman.kennelservice.model.MyDog;
import com.suman.kennelservice.model.User;
import com.suman.kennelservice.model.Userlogin;
import com.suman.kennelservice.serverresponse.ImageResponse;
import com.suman.kennelservice.serverresponse.SignupResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MyDogsapi {

    @POST("dogregister")
    Call<Void> registerDog(@Header("Authorization")String token, @Body MyDog myDog);


    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

//    @GET("dogregister")
//    Call<Dogbreeds> getDog(@Header("Authorization") String token);
    @GET("dogregister")
    Call<Void> getDog(@Body MyDog myDog);

}
