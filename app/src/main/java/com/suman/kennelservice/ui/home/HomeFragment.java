package com.suman.kennelservice.ui.home;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.suman.kennelservice.BLL.LoginBLL;
import com.suman.kennelservice.NavActivity;
import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.adaptar.Dogbreedadaptar;
import com.suman.kennelservice.adaptar.ProductAdaptar;
import com.suman.kennelservice.adaptar.PuppyAdapater;
import com.suman.kennelservice.adaptar.TrainingAdaptar;
import com.suman.kennelservice.api.DogBreedapi;
import com.suman.kennelservice.model.Dogbreeds;
import com.suman.kennelservice.model.ProductClass;
import com.suman.kennelservice.model.Puppy;
import com.suman.kennelservice.model.Training;
import com.suman.kennelservice.strictmode.StrictModeClass;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {


    RecyclerView recycler2,recycler3,recycler4;
    private ImageView product_image,pup_image,trimage;

    //Adapatar
    ProductAdaptar productAdaptar;
    PuppyAdapater puppyAdapater;
    TrainingAdaptar trainingAdaptar;


    //List of array
    List<ProductClass> productClasses;
    List<Puppy> puppies;
    List<Training> trainings;

    private int[] mImages = new int[]{
            R.drawable.sl1, R.drawable.sl2, R.drawable.sl3, R.drawable.sl4,
            R.drawable.sl5, R.drawable.sl6
    };
    CarouselView carouselView;


    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        //Carousel View
        carouselView = root.findViewById(R.id.cara1);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override

            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });

        recycler2 = root.findViewById(R.id.recycler2);
        recycler3 = root.findViewById(R.id.recycler3);
        recycler4 = root.findViewById(R.id.recycler4);
        product_image = root.findViewById(R.id.product_image);
        pup_image = root.findViewById(R.id.pup_image);
        trimage = root.findViewById(R.id.trimage);


        //Productview
        productClasses = new ArrayList<>();

        DogBreedapi dogBreedapi = url.getInstance().create(DogBreedapi.class);
        Call<List<List<ProductClass>>> getproducts = dogBreedapi.getproducts();
        Call<ProductClass> productClassCall = dogBreedapi.getproductimage(url.token);

        getproducts.enqueue(new Callback<List<List<ProductClass>>>() {

            @Override
            public void onResponse(Call<List<List<ProductClass>>> call, Response<List<List<ProductClass>>> response) {
                if(!response.isSuccessful())
                {
                    Log.d("HomeFragment","response body is "+response.code());

                    Toast.makeText(getContext(), "Code Error" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                List<List<ProductClass>> productClasses = response.body();
                List<ProductClass> productClasses1= productClasses.get(0);
                Log.d("HomeFragment","response body is "+response.body());
                ProductAdaptar productAdaptar = new ProductAdaptar(getContext(),productClasses1);
                recycler2.setHasFixedSize(true);
                recycler2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                recycler2.setAdapter(productAdaptar);
//                recycler2.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<List<ProductClass>>> call, Throwable t) {
                Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.d("HomeFragment","response body is "+t.getLocalizedMessage());

            }
        });

        productClassCall.enqueue(new Callback<ProductClass>() {
            @Override
            public void onResponse(Call<ProductClass> call, Response<ProductClass> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(), "Code Error" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                String imgPath = url.imagePath +  response.body().getImage();
//                Picasso.get().load(imgPath).into(card1);
                StrictModeClass.StrictMode();
                try{


                    URL url = new URL(imgPath);
                    product_image.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));

                }  catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ProductClass> call, Throwable t) {
                Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });


        //puppies
        puppies = new ArrayList<>();
        DogBreedapi breedapi = url.getInstance().create(DogBreedapi.class);
        Call<List<Puppy>> puppyList = breedapi.getpuppy();
        Call<Puppy> puppyCall = breedapi.getpuppyimage(url.token);
        puppyList.enqueue(new Callback<List<Puppy>>() {
            @Override
            public void onResponse(Call<List<Puppy>> call, Response<List<Puppy>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Code Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Puppy> puppies = response.body();
                puppyAdapater = new PuppyAdapater(getContext(),puppies);
                recycler3.setHasFixedSize(true);
                recycler3.setAdapter(puppyAdapater);
                recycler3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

            }

            @Override
            public void onFailure(Call<List<Puppy>> call, Throwable t) {
                Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        puppyCall.enqueue(new Callback<Puppy>() {
            @Override
            public void onResponse(Call<Puppy> call, Response<Puppy> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Error code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String imgPath = url.imagePath +  response.body().getImage();
//                Picasso.get().load(imgPath).into(card1);
                StrictModeClass.StrictMode();
                try{


                    URL url = new URL(imgPath);
                    pup_image.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));

                }  catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Puppy> call, Throwable t) {
                Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //Training

        trainings = new ArrayList<>();
        DogBreedapi dogBreedapi1 = url.getInstance().create(DogBreedapi.class);
        final Call<List<Training>> train = dogBreedapi1.gettrain();
        Call<Training> trainingCall = dogBreedapi1.gettrainimage(url.token);

        train.enqueue(new Callback<List<Training>>() {
            @Override
            public void onResponse(Call<List<Training>> call, Response<List<Training>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(), "Error code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Training> trainings = response.body();
                trainingAdaptar = new TrainingAdaptar(getContext(),trainings);
                recycler4.setHasFixedSize(true);
                recycler4.setAdapter(trainingAdaptar);
                recycler4.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<Training>> call, Throwable t) {
                Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        trainingCall.enqueue(new Callback<Training>() {
        @Override
        public void onResponse(Call<Training> call, Response<Training> response) {
        if(!response.isSuccessful())
        {
            Toast.makeText(getContext(), "Error code" + response.code(), Toast.LENGTH_SHORT).show();
            return;
        }
        String imgPath = url.imagePath +  response.body().getImage();
//                Picasso.get().load(imgPath).into(card1);
        StrictModeClass.StrictMode();
        try{


            URL url = new URL(imgPath);
            trimage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));

        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<Training> call, Throwable t) {

    }
});
        return root;
    }

}