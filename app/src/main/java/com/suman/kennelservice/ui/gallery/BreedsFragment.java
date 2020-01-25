package com.suman.kennelservice.ui.gallery;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.adaptar.Dogbreedadaptar;
import com.suman.kennelservice.api.DogBreedapi;
import com.suman.kennelservice.model.Dogbreeds;
import com.suman.kennelservice.strictmode.StrictModeClass;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreedsFragment extends Fragment {

    RecyclerView recycledogbreed;
   private ImageView card1;
   private TextView tvdogname,tvdogdescription;

    //Adaptar
    Dogbreedadaptar dogbreedadaptar;

    //List of array
    List<Dogbreeds> dogbreedsList;

    private BreedsViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(BreedsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
//        final TextView textView = root.findViewById(R.id.text_gallery);
//        galleryViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        recycledogbreed = root.findViewById(R.id.recycledogbreed);
        card1 = root.findViewById(R.id.card1);
        tvdogname = root.findViewById(R.id.tvdogname);
        tvdogdescription = root.findViewById(R.id.tvdogdescription);

//
//        card1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                tvdogname.getText().toString();
////                tvdogdescription.getText().toString();
//                startActivity(new Intent(getActivity(), DogbreeddetailsFragment.class));
//            }
//        });
        dogbreedsList = new ArrayList<>();

        DogBreedapi dogBreedapi = url.getInstance().create(DogBreedapi.class);
        Call<List<Dogbreeds>> listCall = dogBreedapi.getdogbreeds();
        Call<Dogbreeds> listCall1 = dogBreedapi.getdogbreedimage(url.token);

        listCall.enqueue(new Callback<List<Dogbreeds>>() {
            @Override
            public void onResponse(Call<List<Dogbreeds>> call, Response<List<Dogbreeds>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code Error" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                List<Dogbreeds> dogbreedsList = response.body();
                dogbreedadaptar = new Dogbreedadaptar(getContext(),dogbreedsList);
                recycledogbreed.setHasFixedSize(true);
                recycledogbreed.setAdapter(dogbreedadaptar);
                recycledogbreed.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<Dogbreeds>> call, Throwable t) {
                    Log.d("Mero Msg", "onFailure:" + t.getLocalizedMessage());
                    Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });


        listCall1.enqueue(new Callback<Dogbreeds>() {
            @Override
            public void onResponse(Call<Dogbreeds> call, Response<Dogbreeds> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String imgPath = url.imagePath +  response.body().getImage();
//                Picasso.get().load(imgPath).into(card1);
                StrictModeClass.StrictMode();
                try{


                    URL url = new URL(imgPath);
                    card1.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));

                }  catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Dogbreeds> call, Throwable t) {
                Log.d("the error in api is ",t.getLocalizedMessage());
                Toast.makeText(getContext(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        return root;
    }
}