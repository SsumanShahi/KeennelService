package com.suman.kennelwear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends WearableActivity {

    private FrameLayout fr;
    private TextView tvusername,tvpassword;
    private EditText etusername,etpassword;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etusername= findViewById(R.id.etusername);
        etpassword = findViewById(R.id.etpassword);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, pwd;
                user = etusername.getText().toString();
                pwd = etpassword.getText().toString();

                if (Objects.equals(user,"admin") && Objects.equals(pwd, "admin"))
                {
                    Toast.makeText(MainActivity.this, "Successfully Login",Toast.LENGTH_LONG).show();
                    Intent dashboard = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(dashboard);

                }

                else
                {
                    Toast.makeText(MainActivity.this, "Login failed",Toast.LENGTH_LONG).show();
                }
            }
        });


        // Enables Always-on
        setAmbientEnabled();
    }
}
