package com.suman.kennelservice.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.suman.kennelservice.NavActivity;
import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.User;
import com.suman.kennelservice.model.UserCRUD;
import com.suman.kennelservice.serverresponse.ImageResponse;
import com.suman.kennelservice.serverresponse.SignupResponse;
import com.suman.kennelservice.strictmode.StrictModeClass;
import com.suman.kennelservice.ui.MyProfile.ProfileFragment;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    private CircleImageView profileimg;
    private EditText etfname, etlname, etaddress, etphone, etemail, etusername;;
    private Button btnupdate;
    private String imagePath;
    private String imageName="";
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        profileimg = findViewById(R.id.profileimg);
        etfname = findViewById(R.id.etfname);
        etlname = findViewById(R.id.etlname);
        etaddress = findViewById(R.id.etaddress);
        etphone = findViewById(R.id.etphone);
        etemail = findViewById(R.id.etemail);
        etusername=findViewById(R.id.etusername);
        btnupdate=findViewById(R.id.btnupdate);
        user= (User) getIntent().getSerializableExtra("User");
        Log.d("Edit profile","phone number is ="+user.getPhoneNumber());

        etfname.setText(user.getFirstName());
        etlname.setText(user.getLastName());
        etaddress.setText(user.getAddress());
        etphone.setText(user.getPhoneNumber());
        etemail.setText(user.getEmail());
        etusername.setText(user.getUsername());

//        profileimg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                browseimage();
//            }
//        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                saveImageOnly();
*/
                profileupdate();
            }
        });
    }

//    private void saveImageOnly() {
//        File file = new File(imagePath);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
//        MultipartBody.Part body = MultipartBody.Part.createFormData("myFile",
//                file.getName(),requestBody);
//
//        Userapi userapi = url.getInstance().create(Userapi.class);
//        Call<ImageResponse> responseBodyCall = userapi.uploadImage(body);
//
//        StrictModeClass.StrictMode();
//        //Synchronomus method
//
//        try{
//            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
//            imageName = imageResponseResponse.body().getFilename();
//            Toast.makeText(this, "Image Inserted", Toast.LENGTH_LONG).show();
//        }catch (IOException e)
//        {
//            Toast.makeText(this, "Error"+e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//            e.printStackTrace();
//        }
//    }

//    private void browseimage() {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent,0);
//    }


    private void profileupdate() {
        String fname = etfname.getText().toString();
        String lname = etlname.getText().toString();
        String address = etaddress.getText().toString();
        String phone = etphone.getText().toString();
        String email = etemail.getText().toString();
        String username = etusername.getText().toString();

        UserCRUD userCRUD = new UserCRUD(fname,lname,address,phone,email,username,imageName);

        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<UserCRUD> userCRUDCall = userapi.editUser(url.token,userCRUD);


        userCRUDCall.enqueue(new Callback<UserCRUD>() {
            @Override
            public void onResponse(Call<UserCRUD> call, Response<UserCRUD> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(EditProfileActivity.this, "Code"+response.code(), Toast.LENGTH_LONG).show();
                    Log.d("EditProfileActivity", "response other than 200"+response.code());
                    return;
                }
                Toast.makeText(EditProfileActivity.this, "Updated", Toast.LENGTH_LONG).show();
                Log.d("EditProfileActivity", "response of 200"+response.body().getAddress());


//                FragmentManager fm = getSupportFragmentManager();
//                ProfileFragment profileFragment = new ProfileFragment();
//                fm.beginTransaction().replace(R.id.l,profileFragment.getTargetFragment()).commit();
                Intent intent = new Intent(EditProfileActivity.this, NavActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<UserCRUD> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.d("EditProfileActivity", "failur cause "+t.getLocalizedMessage());

            }
        });

    }
}
