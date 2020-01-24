package com.suman.kennelservice.ui.gallery;

import androidx.lifecycle.ViewModelProviders;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class DogbreeddetailsFragment extends Fragment {

    private ImageView card1;
    private TextView tvdogname,tvdogdescription;

    //Adaptar
    Dogbreedadaptar dogbreedadaptar;

    //List of array
    List<Dogbreeds> dogbreedsList;

    private DogbreeddetailsViewModel mViewModel;

    public static DogbreeddetailsFragment newInstance() {
        return new DogbreeddetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.dogbreeddetails_fragment, container, false);

        card1 = view.findViewById(R.id.card1);
        tvdogname = view.findViewById(R.id.tvdogname);
        tvdogdescription = view.findViewById(R.id.tvdogdescription);

        Bundle bundle = getActivity().getIntent().getExtras();

        if(bundle != null)
        {
            card1.setImageResource(bundle.getInt("image"));
            tvdogname.setText(bundle.getString("name"));
            tvdogdescription.setText(bundle.getString("Description"));
        }

//        dogbreedsList = new ArrayList<>();
//        DogBreedapi dogBreedapi = url.getInstance().create(DogBreedapi.class);
////        Call<List<Dogbreeds>> listCall = dogBreedapi.getdogbreeds();
//        Call<Dogbreeds> listCall3 = dogBreedapi.getdogdetail(url.token);
//        Call<Dogbreeds> listCall1 = dogBreedapi.getdogbreedimage(url.token);

//        listCall.enqueue(new Callback<List<Dogbreeds>>() {
//            @Override
//            public void onResponse(Call<List<Dogbreeds>> call, Response<List<Dogbreeds>> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(getContext(), "Code Error" + response.code(), Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                List<Dogbreeds> dogbreedsList = response.body();
//                dogbreedadaptar = new Dogbreedadaptar(getContext(),dogbreedsList);
//                recycledogbreed.setHasFixedSize(true);
//                recycledogbreed.setAdapter(dogbreedadaptar);
//                recycledogbreed.setLayoutManager(new LinearLayoutManager(getContext()));
//            }
//
//            @Override
//            public void onFailure(Call<List<Dogbreeds>> call, Throwable t) {
//                Log.d("Mero Msg", "onFailure:" + t.getLocalizedMessage());
//                Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//            }
//        });

//        listCall3.enqueue(new Callback<Dogbreeds>() {
//            @Override
//            public void onResponse(Call<Dogbreeds> call, Response<Dogbreeds> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(getContext(), "Code Error" + response.code(), Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                List<Dogbreeds> dogbreedsList = response.body();
//                dogbreedadaptar = new Dogbreedadaptar(getContext(),dogbreedsList);
//
//
//                }
//
//
//            @Override
//            public void onFailure(Call<Dogbreeds> call, Throwable t) {
//
//            }
//        });
//
//
//        listCall1.enqueue(new Callback<Dogbreeds>() {
//            @Override
//            public void onResponse(Call<Dogbreeds> call, Response<Dogbreeds> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(getContext(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                String imgPath = url.imagePath +  response.body().getImage();
////                Picasso.get().load(imgPath).into(card1);
//                StrictModeClass.StrictMode();
//                try{
//
//
//                    URL url = new URL(imgPath);
//                    dcard1.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
//
//                }  catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Dogbreeds> call, Throwable t) {
//                Log.d("the error in api is ",t.getLocalizedMessage());
//                Toast.makeText(getContext(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DogbreeddetailsViewModel.class);
        // TODO: Use the ViewModel
    }

}
