package com.suman.kennelservice;

import android.app.Notification;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.suman.kennelservice.BLL.LoginBLL;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.activity.CreateChannel;
import com.suman.kennelservice.activity.RegisterActivity;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.User;
import com.suman.kennelservice.serverresponse.SignupResponse;
import com.suman.kennelservice.ui.MyProfile.ProfileFragment;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class LoginTest {




    //login failed
    @Test
    public void testLoginFail()
    {
        LoginBLL loginBLL = new LoginBLL();
        boolean result = loginBLL.checklogin("suman321","suman");
        assertTrue(result);
    }

    //loginpass
    @Test
    public void loginpass()
    {
        LoginBLL loginBLL = new LoginBLL();
        boolean result = loginBLL.checklogin("suman123","suman");
        assertTrue(result);
    }

    @Test
    public void registerpass()
    {

        User user = new User();
        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<SignupResponse> signupResponseCall = userapi.registerUser(user);
        try {
            Response<SignupResponse> register = signupResponseCall.execute();
            if(register.isSuccessful() && register.body().getStatus().equals("Signup Success!"))
            {
                assertEquals(true,signupResponseCall);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void registerfail()
    {

        User user = new User("suman","shahi","nakkhu","9849532862","s@gmail.com","suman123","suman",null);

        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<SignupResponse> signupResponseCall = userapi.registerUser(user);
        try {
            Response<SignupResponse> register = signupResponseCall.execute();
            if(register.isSuccessful() && register.body().getStatus().equals("Signup Successs!"))
            {
                assertEquals(false,register);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
