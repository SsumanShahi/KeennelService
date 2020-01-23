package com.suman.kennelservice.api;

import com.suman.kennelservice.model.User;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Userapi {
    @POST("users/signup")
    Call<SignupResponse> registerUser(@Body User user);

    @FormUrlEncoded
    @POST("users/login")
    Call<SignupResponse> checkUser(@Field("username") String username, @Field("password") String password);

//
//    @POST("users/login")
//    Call<SignupResponse> checklogin(@Body username Username);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("users/me")
    Call<User> getUserDetails(@Header("Authorization") String token);
}
