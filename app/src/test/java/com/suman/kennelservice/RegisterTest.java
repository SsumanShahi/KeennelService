package com.suman.kennelservice;

import android.app.Notification;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.activity.CreateChannel;
import com.suman.kennelservice.activity.RegisterActivity;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.User;
import static junit.framework.TestCase.assertEquals;
import com.suman.kennelservice.serverresponse.SignupResponse;
import com.suman.kennelservice.ui.MyProfile.ProfileFragment;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterTest {
boolean expected=true;
boolean actual=false;

    @Test
    public void signupTest(){

        User user = new User("testNamee","testName","test@gmail.com","98745630125","test123","test1234","test123",null);


        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<SignupResponse> signupResponseCall = userapi.registerUser(user);

        try {
            Response<SignupResponse> register = signupResponseCall.execute();
            if(register.isSuccessful() && register.body().getStatus().equals("Signup Success!"))
            {
                actual=true;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        assertEquals(actual,expected);

    }


    @Test
    public void signupTestfailed(){

        User user = new User("testNamee","testName","test@gmail.com","98745630125","test123","test1234","test123",null);


        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<SignupResponse> signupResponseCall = userapi.registerUser(user);

        try {
            Response<SignupResponse> register = signupResponseCall.execute();
            if(register.isSuccessful() && register.body().getStatus().equals("Signup Success!"))
            {
                actual=true;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        assertEquals(actual,expected);

    }
}
