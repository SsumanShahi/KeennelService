package com.suman.kennelservice.activity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.loader.content.CursorLoader;

import android.app.Notification;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.User;
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

public class RegisterActivity extends AppCompatActivity {

    CircleImageView profileimg;
    private EditText etcmpassword, etpassword, etfname, etlname, etaddress, etphone, etemail, etusername;;
    private Button btnreg;
    private TextView txtlog;
    private String imagePath;
    private String imageName="";

    private NotificationManagerCompat notificationManagerCompat;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();
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
        getSupportActionBar().hide();

        txtlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logintent = new Intent(RegisterActivity.this, ProfileFragment.class);
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
                if(etpassword.getText().toString().equals((etcmpassword.getText().toString()))){
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

    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("myFile",
                file.getName(),requestBody);

        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<ImageResponse> responseBodyCall = userapi.uploadImage(body);

        StrictModeClass.StrictMode();
        //Synchronomus method

        try{
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image Inserted", Toast.LENGTH_LONG).show();
        }catch (IOException e)
        {
            Toast.makeText(this, "Error"+e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private boolean validate() {
        boolean status = true;
        if(etusername.getText().toString().length()<6)
        {
            etusername.setError("minimum 6 character");
            status=false;
        }
        return status;
    }

    private void browseimage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void signup() {
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();

        String fname = etfname.getText().toString();
        String lname = etlname.getText().toString();
        String address = etaddress.getText().toString();
        String phone = etphone.getText().toString();
        String email = etemail.getText().toString();
        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();

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
                Notification notification = new NotificationCompat.Builder(RegisterActivity.this, CreateChannel.CHANNEL_1)
                        .setSmallIcon(R.drawable.not)
                        .setContentTitle("Register")
                        .setContentText("Register successful")
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();
                notificationManagerCompat.notify(1,notification);
                Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(RegisterActivity.this, ProfileFragment.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(data == null)
            {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_LONG).show();
            }
        }

        Uri uri = data.getData();
        profileimg.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {

        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),uri,projection, null
                ,null,null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }
}
