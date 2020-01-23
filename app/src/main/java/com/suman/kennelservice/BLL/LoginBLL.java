package com.suman.kennelservice.BLL;

import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.Userlogin;
import com.suman.kennelservice.serverresponse.SignupResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Url;

public class LoginBLL {
    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {

        Userlogin userlogin = new Userlogin(username,password);
        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<SignupResponse> usercall = userapi.checklogin(userlogin);

        try {
            Response<SignupResponse> loginResponse = usercall.execute();
            if (loginResponse.isSuccessful()
                    && loginResponse.body().getStatus().equals("Login Successfull")){
                url.token += loginResponse.body().getToken();
//                url.Cookie = imageResponseResponse.headers().get("Set=Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
