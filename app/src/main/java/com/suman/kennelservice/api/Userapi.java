package com.suman.kennelservice.api;

import com.suman.kennelservice.model.User;
import com.suman.kennelservice.model.UserCRUD;
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
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface Userapi {
    @POST("users/signup")
    Call<SignupResponse> registerUser(@Body User user);

//    @FormUrlEncoded
//    @POST("users/login")
//    Call<SignupResponse> checkUser(@Field("username") String username, @Field("password") String password);

//
    @POST("users/login")
    Call<SignupResponse> checklogin(@Body Userlogin userlogin);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("users/me")
    Call<User> getUserDetails(@Header("Authorization") String token);


    @PUT("users/me")
    Call<UserCRUD> editUser(@Header("Authorization") String token,@Body UserCRUD userCRUD);
}
