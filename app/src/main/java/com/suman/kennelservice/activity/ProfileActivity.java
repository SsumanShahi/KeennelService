package com.suman.kennelservice.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.User;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    CircleImageView profileimg1;
    private TextView tvfname, tvlname, tvaddress, tvphone, tvemail, tvusername;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileimg1 = findViewById(R.id.profileimg1);
        tvfname = findViewById(R.id.tvfname);
        tvlname = findViewById(R.id.tvlname);
        tvaddress = findViewById(R.id.tvaddress);
        tvphone = findViewById(R.id.tvphone);
        tvemail = findViewById(R.id.tvemail);
        tvusername = findViewById(R.id.tvusername);

        loadcurrentuser();
    }

    private void loadcurrentuser() {


        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<User> userCall = userapi.getUserDetails(url.token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ProfileActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String imgPath = url.imagePath +  response.body().getImage();
//                tvfname.setText(user.getFirstName());
//                tvlname.setText(user.getLastName());
//                tvaddress.setText(user.getAddress());
//                tvemail.setText(user.getEmail());
//                tvphone.setText(user.getPhoneNumber());
//                tvusername.setText(user.getUsername());
                Picasso.get().load(imgPath).into(profileimg1);


//                StrictModeClass.StrictMode();
//                try {
//                    URL url = new URL(imgPath);
//                    imgProgileImg.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(ProfileActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}
