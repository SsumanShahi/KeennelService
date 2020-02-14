package com.suman.kennelservice;

import android.app.Notification;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.activity.AppointmentActivity;
import com.suman.kennelservice.activity.CreateChannel;
import com.suman.kennelservice.api.Userapi;
import static junit.framework.TestCase.assertEquals;
import com.suman.kennelservice.model.Appointment;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentTesting {

    boolean expected = true;
    boolean actual = false;

    @Test
    public void appointmenttesting() {
        Appointment appointment = new Appointment("Suman", "Hulk", "Dog", "2", "female");
        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<Appointment> appointmentCall = userapi.postappointment(url.token, appointment);

        try {
            Response<Appointment> register = appointmentCall.execute();
            if (register.isSuccessful() && register.body().getClass().equals("Appointment Success!")) {

            } else {
                actual=true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(actual,expected);

    }
}
