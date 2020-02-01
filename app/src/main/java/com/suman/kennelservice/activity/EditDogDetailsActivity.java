package com.suman.kennelservice.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.suman.kennelservice.NavActivity;
import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.api.MyDogsapi;
import com.suman.kennelservice.model.MyDog;
import com.suman.kennelservice.model.MyDogCRUD;
import com.suman.kennelservice.model.User;
import com.suman.kennelservice.serverresponse.ImageResponse;
import com.suman.kennelservice.strictmode.StrictModeClass;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDogDetailsActivity extends AppCompatActivity {

    private EditText etdogname,etpettype,etbreed,etage,etwieght,etgender,etvaccination;
    private ImageView dogpimg;
    private TextView btnupdate;
    private String imagePath;
    private String imageName="";
    MyDogCRUD myDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dog_details);
        dogpimg = findViewById(R.id.dogpimg);
        etdogname = findViewById(R.id.etdogname);
        etpettype = findViewById(R.id.etpettype);
        etbreed = findViewById(R.id.etbreed);
        etage = findViewById(R.id.etage);
        etwieght = findViewById(R.id.etwieght);
        etgender = findViewById(R.id.etgender);
        etvaccination=findViewById(R.id.etvaccination);
        btnupdate=findViewById(R.id.btnupdate);
        myDog = new MyDogCRUD();

        myDog= (MyDogCRUD) getIntent().getSerializableExtra("DogDetail");
        Log.d("Edit DogDetail","breed is ="+myDog.getBreed());


        etdogname.setText(myDog.getPetName());
        etpettype.setText(myDog.getPetType());
        etbreed.setText(myDog.getBreed());
        etage.setText(myDog.getAge());
        etwieght.setText(myDog.getPetSize());
        etgender.setText(myDog.getGender());
        etvaccination.setText(myDog.getPetvaccination());
        final String imgPath = url.imagePath + myDog.getImage();
        try {
            URL url = new URL(imgPath);
            dogpimg.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveImageOnly();

                update();

            }
        });

        dogpimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browseimage();
            }
        });

    }

    private void update() {
        String dogname = etdogname.getText().toString();
        String petype = etpettype.getText().toString();
        String breed = etbreed.getText().toString();
        String age = etage.getText().toString();
        String weight = etwieght.getText().toString();
        String gender = etgender.getText().toString();
        String vaccination = etvaccination.getText().toString();

        MyDogCRUD myDogCRUD = new MyDogCRUD(myDog.getId(),dogname,petype,breed,age,weight,gender,vaccination,imageName);
        MyDogsapi myDogsapi = url.getInstance().create(MyDogsapi.class);
        Call<MyDogCRUD> mydog1 = myDogsapi.editMydog(url.token,myDogCRUD,myDog.getId());

        mydog1.enqueue(new Callback<MyDogCRUD>() {
            @Override
            public void onResponse(Call<MyDogCRUD> call, Response<MyDogCRUD> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(EditDogDetailsActivity.this, "Code"+response.code(), Toast.LENGTH_LONG).show();
                    Log.d("EditDogDetailsActivity", "response other than 200"+response.code());
                    return;
                }

                Toast.makeText(EditDogDetailsActivity.this, "Updated", Toast.LENGTH_LONG).show();
                Log.d("EditDogDetailsActivity", "response of 200"+response.body());
                Intent intent = new Intent(EditDogDetailsActivity.this, DogListActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<MyDogCRUD> call, Throwable t) {
                Log.d("EditDogDetailsActivity", "error is "+t.getLocalizedMessage());


            }
        });
    }

    private void browseimage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
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
