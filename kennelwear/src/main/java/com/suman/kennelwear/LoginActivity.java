package com.suman.kennelwear;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class LoginActivity extends WearableActivity {

    private EditText ettusername,ettpassword;
    private Button btnlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ettusername = findViewById(R.id.ettusername);
        ettpassword = findViewById(R.id.ettpassword);
        btnlogin = findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, pwd;
                user = ettusername.getText().toString();
                pwd = ettpassword.getText().toString();

                if (Objects.equals(user,"admin") && Objects.equals(pwd, "admin"))
                {
                    Toast.makeText(LoginActivity.this, "Successfully Login",Toast.LENGTH_LONG).show();
//                    Intent dashboard = new Intent(MainActivity.this, DashboardActivity.class);
//                    startActivity(dashboard);

                }

                else
                {
                    Toast.makeText(LoginActivity.this, "Login failed",Toast.LENGTH_LONG).show();
                }
            }
        });
        // Enables Always-on
        setAmbientEnabled();
    }
}
