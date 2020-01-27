package com.suman.kennelservice.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.Appointment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentActivity extends AppCompatActivity {

    private EditText et_ownername,et_petname,et_breed,et_age,et_gender;
    private Button btn_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        et_ownername = findViewById(R.id.et_ownername);
        et_petname = findViewById(R.id.et_petname);
        et_breed = findViewById(R.id.et_breed);
        et_age = findViewById(R.id.et_age);
        et_gender = findViewById(R.id.et_gender);
        btn_app = findViewById(R.id.btn_app);

        btn_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointment();
            }
        });
    }

    private void appointment() {
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

                Toast.makeText(AppointmentActivity.this, "Appointment Successfull", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Appointment> call, Throwable t) {
                Toast.makeText(AppointmentActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
        }


}
