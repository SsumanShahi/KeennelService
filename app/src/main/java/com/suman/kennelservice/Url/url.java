package com.suman.kennelservice.Url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class url {
    //    public static final String Base_Url ="http://192.168.1.11:3000/";
    public static final String Base_Url="http://10.0.2.2:3001/";
//    public static final String Base_Url="http://172.100.100.5:3000/";

    public static String token="Bearer ";
    public static String imagePath= Base_Url+"uploads/";
    public static String Cookie="";

    public static Retrofit getInstance()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
