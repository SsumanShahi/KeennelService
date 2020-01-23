package com.suman.kennelservice.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.suman.kennelservice.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    EditText username, password;
    ImageView img;
    Button btnlogin;
    TextView reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        img = findViewById(R.id.img);
        btnlogin = findViewById(R.id.btnlogin);
        reg = findViewById(R.id.reg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Login();

                String user, pwd;
                user = username.getText().toString();
                pwd = password.getText().toString();

                if (Objects.equals(user,"admin") && Objects.equals(pwd, "admin"))
                {

                    signup();
//                    Toast.makeText(MainActivity.this, "Successfully Login",Toast.LENGTH_LONG).show();
//                    Intent dashboard = new Intent(MainActivity.this, Dashboard.class);
//                    startActivity(dashboard);
//                    finish();

                }

                else
                {
                    Toast.makeText(MainActivity.this, "Login failed",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void signup() {

        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("user",username.getText().toString());
        editor.putString("pwd",password.getText().toString());
        editor.commit();


        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show();
    }

    private void Login() {

//        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
//        String user = sharedPreferences.getString("user","");
//        String psw = sharedPreferences.getString("psw","");
//
//        if(user.equals(username.getText().toString()) ||
//        psw.equals(password.getText().toString()))
//        {
//            Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(MainActivity.this,Dashboard.class);
//            startActivity(intent);
//            finish();
//        }
//        else
//        {
//            Toast.makeText(MainActivity.this, "Either username of password is incorrect", Toast.LENGTH_SHORT).show();
//        }

    }
}
