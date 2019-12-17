package com.suman.kennelservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    private ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        img1 = findViewById(R.id.img1);
        // using a thread and hald screen for 2 seconds

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
                String user = sharedPreferences.getString("user","");
                String psw = sharedPreferences.getString("psw","");

                if(user.equals("admin") ||
                        psw.equals("admin"))
                {
                    Toast.makeText(SplashActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SplashActivity.this,Dashboard.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(SplashActivity.this, "Either username of password is incorrect", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
//
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();//close the activity
            }
        },2000);
    }
}
