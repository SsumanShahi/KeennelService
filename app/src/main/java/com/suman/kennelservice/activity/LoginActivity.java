package com.suman.kennelservice.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.suman.kennelservice.BLL.LoginBLL;
import com.suman.kennelservice.NavActivity;
import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.Userlogin;
import com.suman.kennelservice.serverresponse.SignupResponse;
import com.suman.kennelservice.strictmode.StrictModeClass;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText ettusername, ettpassword;
    ImageView profileimage;
    Button btnlogin;
    TextView reg;
    TextView ProximitySensor, data;
    SensorManager mySensorManager;
    Sensor myProximitySensor;
    private NotificationManagerCompat notificationManagerCompat;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        getSupportActionBar().hide();
        ProximitySensor = (TextView) findViewById(R.id.proximitySensor);
        data = (TextView) findViewById(R.id.data);
        mySensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(
                Sensor.TYPE_PROXIMITY);
        if (myProximitySensor == null) {
            ProximitySensor.setText("No Proximity Sensor!");
        } else {
            mySensorManager.registerListener(proximitySensorEventListener,
                    myProximitySensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }


        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();
        ettusername = findViewById(R.id.ettusername);
        ettpassword = findViewById(R.id.ettpassword);
        btnlogin = findViewById(R.id.btnlogin);
        reg = findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (TextUtils.isEmpty(ettusername.getText())) {
                    ettusername.setError("please enter username");
                    ettusername.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(ettpassword.getText())) {
                    ettpassword.setError("please enter password");
//                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//                    vibrator.vibrate(400);
                    ettpassword.requestFocus();
                    return;


                }
                login();
            }

        });

}

    SensorEventListener proximitySensorEventListener
            = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onSensorChanged(SensorEvent event) {
            WindowManager.LayoutParams params = LoginActivity.this.getWindow().getAttributes();
            if(event.sensor.getType()==Sensor.TYPE_PROXIMITY){

                if(event.values[0]==0){
                    params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    params.screenBrightness = 0;
                    getWindow().setAttributes(params);
                }
                else{
                    params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    params.screenBrightness = -1f;
                    getWindow().setAttributes(params);
                }
            }
        }
    };




    @RequiresApi(api = Build.VERSION_CODES.O)
    private void login() {
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();
        String username = ettusername.getText().toString();
        String password = ettpassword.getText().toString();
        LoginBLL loginBLL = new LoginBLL();

        StrictModeClass.StrictMode();
        if (loginBLL.checklogin(username, password)) {
            Notification notification = new NotificationCompat.Builder(LoginActivity.this, CreateChannel.CHANNEL_1)
                    .setSmallIcon(R.drawable.not)
                    .setContentTitle("Login")
                    .setContentText("You are login successful")
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .build();
            notificationManagerCompat.notify(1,notification);
            Intent intent = new Intent(LoginActivity.this, NavActivity.class);
            intent.putExtra("Userlogin",databaseList());
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            ettusername.requestFocus();
        }

//
//        LoginBLL loginBLL = new LoginBLL(username,password);
        Userlogin userlogin = new Userlogin(username, password);

//        StrictModeClass.StrictMode();
//        Userapi userapi = url.getInstance().create(Userapi.class);
//        Call<SignupResponse> userCall = userapi.checklogin(userlogin);
//        userCall.enqueue(new Callback<SignupResponse>() {
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void onResponse(@NonNull Call<SignupResponse> call, @NonNull Response<SignupResponse> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(LoginActivity.this, "Code" + response.code(), Toast.LENGTH_LONG).show();
//                    return;
//                }
//                Notification notification = new NotificationCompat.Builder(LoginActivity.this, CreateChannel.CHANNEL_1)
//                        .setSmallIcon(R.drawable.not)
//                        .setContentTitle("Login")
//                        .setContentText("You are login successful")
//                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
//                        .build();
//                notificationManagerCompat.notify(1,notification);
//                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
//                url.token += response.body().getToken();
//                Intent intent = new Intent(LoginActivity.this, NavActivity.class);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<SignupResponse> call,@NonNull Throwable t) {
//                Toast.makeText(LoginActivity.this, "error is = " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//
//            }
//        });


////
//        String username = ettusername.getText().toString();
//        String password = ettpassword.getText().toString();



//        Userapi userapi = url.getInstance().create(Userapi.class);
//       Call<SignupResponse> userCall = userapi.checklogin(userlogin);
//        StrictModeClass.StrictMode();
//        try {
//            Response<SignupResponse> loginr = userCall.execute();
//            if(loginr.isSuccessful() && loginr.body().getStatus().equals("Login Successful!"))
//            {
//                url.token += loginr.body().getToken();
//                Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(this, NavActivity.class));
//                finish();
//            }
//            else
//            {
//                Toast.makeText(this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
//        }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    }
