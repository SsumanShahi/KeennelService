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

import com.google.android.material.textfield.TextInputLayout;
import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.User;
import com.suman.kennelservice.serverresponse.SignupResponse;
import com.suman.kennelservice.ui.login.LoginFragment;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    CircleImageView profileimg;
    private TextInputLayout etfname, etlname, etaddress, etphone, etemail, etusername, etpassword, etcmpassword;
    private Button btnreg;
    private TextView txtlog;
    private String imagePath;
    private String imageName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        profileimg = findViewById(R.id.profileimg);
        etfname = findViewById(R.id.etfname);
        etlname = findViewById(R.id.etlname);
        etaddress = findViewById(R.id.etaddress);
        etphone = findViewById(R.id.etphone);
        etemail = findViewById(R.id.etemail);
        etusername=findViewById(R.id.etusername);
        etpassword = findViewById(R.id.etpassword);
        etcmpassword=findViewById(R.id.etcmpassword);
        btnreg=findViewById(R.id.btnreg);
        txtlog=findViewById(R.id.txtlog);

        txtlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logintent = new Intent(RegisterActivity.this, LoginFragment.class);
                startActivity(logintent);
            }
        });

        profileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browseimage();
            }
        });


        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etpassword.getEditText().toString().equals((etcmpassword.getEditText().toString()))){
                    if(validate()){
                        saveImageOnly();
                        signup();
                    }
                }else
                {
                    Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_LONG).show();
                    etpassword.requestFocus();
                    return;

                }
            }
        });


//        btnreg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                signup();
////                String user = username.getText().toString().trim();
////                String pwd = password.getText().toString().trim();
//                //String cm_pass = cmpassword.getText().toString().trim();
//
////                if(pwd.equals(cm_pass))
////                {
////                    long val = db.addUser(user,pwd);
////                    if (val>0){
////                        Toast.makeText(RegisterActivity.this, "  Registration Successfully",Toast.LENGTH_LONG).show();
////                        Intent movetoLogin = new Intent(RegisterActivity.this, MainActivity.class);
////                        startActivity(movetoLogin);
////
////                    }
////                    else {
////                        Toast.makeText(RegisterActivity.this, " Registration Failed",Toast.LENGTH_LONG).show();
////
////                    }
////
////                    Toast.makeText(RegisterActivity.this, " Password matched",Toast.LENGTH_LONG).show();
////                    Intent movetoLogin = new Intent(RegisterActivity.this, MainActivity.class);
////                    startActivity(movetoLogin);
////                }
////                else
////                {
////                    Toast.makeText(RegisterActivity.this, " Password does not matched",Toast.LENGTH_LONG).show();
////                }
//            }
//        });

    }



    private void browseimage() {
    }

    private void signup() {

        String fname = etfname.getEditText().toString();
        String lname = etlname.getEditText().toString();
        String address = etaddress.getEditText().toString();
        String phone = etphone.getEditText().toString();
        String email = etemail.getEditText().toString();
        String username = etusername.getEditText().toString();
        String password = etpassword.getEditText().toString();

        User user = new User(fname,lname,address,phone,email,username,password,imageName);

        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<SignupResponse> signupResponseCall = userapi.registerUser(user);


        signupResponseCall.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Code"+response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(RegisterActivity.this, LoginFragment.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });


//        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString("user",username.getText().toString());
//        editor.putString("pwd",password.getText().toString());
//        editor.commit();
//
//
//        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show();
    }
}
