package com.suman.kennelservice.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText ettusername, ettpassword;
    ImageView profileimage;
    Button btnlogin;
    TextView reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ettusername = findViewById(R.id.ettusername);
        ettpassword = findViewById(R.id.ettpassword);
        profileimage = findViewById(R.id.profileimage);
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
                login();
            }
        });
    }

    private void login() {
        String username = ettusername.getText().toString();
        String password = ettpassword.getText().toString();


        LoginBLL loginBLL = new LoginBLL();
        Userlogin userlogin = new Userlogin(username, password);

        StrictModeClass.StrictMode();
        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<SignupResponse> userCall = userapi.checklogin(userlogin);
        userCall.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignupResponse> call, @NonNull Response<SignupResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Code" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                url.token += response.body().getToken();
                Intent intent = new Intent(LoginActivity.this, NavActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(@NonNull Call<SignupResponse> call,@NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, "error is = " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}
