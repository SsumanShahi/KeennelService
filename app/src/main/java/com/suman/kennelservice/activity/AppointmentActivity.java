package com.suman.kennelservice.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.suman.kennelservice.NavActivity;
import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.Appointment;
import com.suman.kennelservice.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentActivity extends AppCompatActivity {

    private TextView tvusername,btn_app;
    private ImageView profileimg1;
    private EditText et_ownername,et_petname,et_breed,et_age,et_gender;
    private NotificationManagerCompat notificationManagerCompat;

    User user;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();

        et_ownername = findViewById(R.id.et_ownername);
        et_petname = findViewById(R.id.et_petname);
        et_breed = findViewById(R.id.et_breed);
        et_age = findViewById(R.id.et_age);
        et_gender = findViewById(R.id.et_gender);
        btn_app = findViewById(R.id.btn_app);
        tvusername = findViewById(R.id.tvusername);
        profileimg1 = findViewById(R.id.profileimg1);
        user=new User();
        user= (User) getIntent().getSerializableExtra("User");
        tvusername.setText(user.getUsername());
        final String imgPath = url.imagePath + user.getImage();
        try {
            URL url = new URL(imgPath);
            profileimg1.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        btn_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointment();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void appointment() {
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();
        String ownername = et_ownername.getText().toString();
        String petname = et_petname.getText().toString();
        String breed = et_breed.getText().toString();
        String age = et_age.getText().toString();
        String gender = et_gender.getText().toString();

        Appointment appointment = new Appointment(ownername,petname,breed,age,gender);

        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<Appointment> appointmentCall = userapi.postappointment(url.token,appointment);

        appointmentCall.enqueue(new Callback<Appointment>() {
            @Override
            public void onResponse(Call<Appointment> call, Response<Appointment> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(AppointmentActivity.this, "Code"+response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                Notification notification = new NotificationCompat.Builder(AppointmentActivity.this, CreateChannel.CHANNEL_1)
                        .setSmallIcon(R.drawable.not)
                        .setContentTitle("Appointment")
                        .setContentText("Appointment successful")
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();
                notificationManagerCompat.notify(1,notification);
                Toast.makeText(AppointmentActivity.this, "Appointment Successfull", Toast.LENGTH_LONG).show();
                startActivity(new Intent(AppointmentActivity.this, NavActivity.class));
            }

            @Override
            public void onFailure(Call<Appointment> call, Throwable t) {
                Toast.makeText(AppointmentActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
        }


}
