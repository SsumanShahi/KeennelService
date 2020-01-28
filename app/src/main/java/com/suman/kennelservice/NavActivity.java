package com.suman.kennelservice;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.User;
import com.suman.kennelservice.strictmode.StrictModeClass;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavActivity extends AppCompatActivity {

    ImageView imageView;
    private TextView tvusername;
    User user;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        Fragment fragment = null;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send, R.id.nav_profile,R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        loadCurrentUser();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void loadCurrentUser() {
        NavigationView navigationView =(NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        imageView = view.findViewById(R.id.imageView);
        tvusername=view.findViewById(R.id.tvusername);
        user=new User();
        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<User> userCall = userapi.getUserDetails(url.token);


        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(NavActivity.this, "Code" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                user= response.body();

                if (response.body() != null) {

                    String imgPath = null;
                    imgPath = url.imagePath + response.body().getImage();
//                    Picasso.get().load(imgPath).into(imageView);

                StrictModeClass.StrictMode();
                try{
                    URL url = new URL(imgPath);
                    imageView.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                }

//                tvusername.setText(response.body().getUsername());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(NavActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
