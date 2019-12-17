package com.suman.kennelservice;

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

public class RegisterActivity extends AppCompatActivity {

    ImageView img;
    EditText username, password, cmpassword;
    Button btnreg;
    TextView txtlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        img = findViewById(R.id.img);
        username=findViewById(R.id.username);
        password = findViewById(R.id.password);
        cmpassword=findViewById(R.id.cmpassword);
        btnreg=findViewById(R.id.btnreg);
        txtlog=findViewById(R.id.txtlog);

        txtlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logintent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(logintent);
            }
        });

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signup();
//                String user = username.getText().toString().trim();
//                String pwd = password.getText().toString().trim();
                //String cm_pass = cmpassword.getText().toString().trim();

//                if(pwd.equals(cm_pass))
//                {
//                    long val = db.addUser(user,pwd);
//                    if (val>0){
//                        Toast.makeText(RegisterActivity.this, "  Registration Successfully",Toast.LENGTH_LONG).show();
//                        Intent movetoLogin = new Intent(RegisterActivity.this, MainActivity.class);
//                        startActivity(movetoLogin);
//
//                    }
//                    else {
//                        Toast.makeText(RegisterActivity.this, " Registration Failed",Toast.LENGTH_LONG).show();
//
//                    }
//
//                    Toast.makeText(RegisterActivity.this, " Password matched",Toast.LENGTH_LONG).show();
//                    Intent movetoLogin = new Intent(RegisterActivity.this, MainActivity.class);
//                    startActivity(movetoLogin);
//                }
//                else
//                {
//                    Toast.makeText(RegisterActivity.this, " Password does not matched",Toast.LENGTH_LONG).show();
//                }
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
}
