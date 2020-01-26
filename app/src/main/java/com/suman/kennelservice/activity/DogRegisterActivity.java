package com.suman.kennelservice.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.api.MyDogsapi;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.MyDog;
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

public class DogRegisterActivity extends AppCompatActivity {

    private EditText etdogname,etpettype,etbreed,etage,etwieght,etgender,etvaccination;
    private CircleImageView dogpimg;
    private Button btndreg;
    private String imagePath;
    private String imageName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_register);
        dogpimg = findViewById(R.id.dogpimg);
        etdogname = findViewById(R.id.etdogname);
        etpettype = findViewById(R.id.etpettype);
        etbreed = findViewById(R.id.etbreed);
        etage = findViewById(R.id.etage);
        etwieght = findViewById(R.id.etwieght);
        etgender = findViewById(R.id.etgender);
        etvaccination=findViewById(R.id.etvaccination);
        btndreg=findViewById(R.id.btndreg);

        btndreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        saveImageOnly();
                        register();

            }
        });

        dogpimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browseimage();
            }
        });


    }

    private void register() {
        String dogname = etdogname.getText().toString();
        String petype = etpettype.getText().toString();
        String breed = etbreed.getText().toString();
        String age = etage.getText().toString();
        String weight = etwieght.getText().toString();
        String gender = etgender.getText().toString();
        String vaccination = etvaccination.getText().toString();

        MyDog myDog = new MyDog(dogname,petype,breed,age,weight,gender,vaccination,imageName);

        MyDogsapi myDogsapi = url.getInstance().create(MyDogsapi.class);
        Call<Void> voidCall= myDogsapi.registerDog(url.token,myDog);


        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(DogRegisterActivity.this, "Code"+response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(DogRegisterActivity.this, "Registered", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(DogRegisterActivity.this, DogListActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(DogRegisterActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("myFile",
                file.getName(),requestBody);

        MyDogsapi myDogsapi = url.getInstance().create(MyDogsapi.class);
        Call<ImageResponse> responseBodyCall = myDogsapi.uploadImage(body);

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

    private void browseimage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
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
        dogpimg.setImageURI(uri);
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
