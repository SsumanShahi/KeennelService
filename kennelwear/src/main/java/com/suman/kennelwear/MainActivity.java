package com.suman.kennelwear;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.suman.kennelwear.loginbll.LoginBLL;
import com.suman.kennelwear.strictmodeclass.StrictModeClass;

import java.util.Objects;

public class MainActivity extends WearableActivity {

    private FrameLayout fr;
    private TextView tvusername,tvpassword;
    private EditText etuname,etpass;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etuname= findViewById(R.id.etuname);
        etpass = findViewById(R.id.etpass);
        btnlogin = findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String user, pwd;
//                user = etuname.getText().toString();
//                pwd = etpass.getText().toString();
//
//                if (Objects.equals(user,"admin") && Objects.equals(pwd, "admin"))
//                {
//                    Toast.makeText(MainActivity.this, "Successfully Login",Toast.LENGTH_LONG).show();
//                    Intent dashboard = new Intent(MainActivity.this, DashboardActivity.class);
//                    startActivity(dashboard);
//
//                }
//
//                else
//                {
//                    Toast.makeText(MainActivity.this, "Login failed",Toast.LENGTH_LONG).show();
//                }

                login();
            }
        });


        // Enables Always-on
        setAmbientEnabled();
    }

    private void login() {

        String username = etuname.getText().toString();
        String password = etpass.getText().toString();
        LoginBLL loginBLL = new LoginBLL();

        StrictModeClass.StrictMode();
        if (loginBLL.checklogin(username, password)) {
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            intent.putExtra("Userlogin",databaseList());
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            etuname.requestFocus();
        }

    }
}
