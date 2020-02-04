package com.suman.kennelservice.api;

import com.suman.kennelservice.model.Appointment;
import com.suman.kennelservice.model.AppointmentCRUD;
import com.suman.kennelservice.model.MyDog;
import com.suman.kennelservice.model.MyDogCRUD;
import com.suman.kennelservice.model.User;
import com.suman.kennelservice.model.UserCRUD;
import com.suman.kennelservice.model.Userlogin;
import com.suman.kennelservice.serverresponse.ImageResponse;
import com.suman.kennelservice.serverresponse.SignupResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Userapi {
    @POST("users/signup")
    Call<SignupResponse> registerUser(@Body User user);

//    @FormUrlEncoded
//    @POST("users/login")
//    Call<SignupResponse> checklogin(@Field("username") String username, @Field("password") String password);

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


    //Appointment
    @POST("appointment")
    Call<Appointment> postappointment(@Header("Authorization") String token, @Body Appointment appointment);

    @GET("appointment")
    Call<List<AppointmentCRUD>> getappoint(@Header("Authorization") String token);

    @DELETE("appointment/{id}")
    Call<AppointmentCRUD> deleteappointment(@Header("Authorization") String token, @Path("id") String id);
}
