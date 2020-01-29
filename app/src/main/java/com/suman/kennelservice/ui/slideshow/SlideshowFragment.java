package com.suman.kennelservice.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.adaptar.ProductAdaptar;
import com.suman.kennelservice.api.DogBreedapi;
import com.suman.kennelservice.model.ProductClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideshowFragment extends Fragment {

    RecyclerView rct1;

    ProductAdaptar productAdaptar;

    List<ProductClass> productClasses;
    private ImageView product_image;


    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        slideshowViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        product_image = root.findViewById(R.id.product_image);

        rct1 = root.findViewById(R.id.rct1);

        productClasses = new ArrayList<>();

        DogBreedapi dogBreedapi = url.getInstance().create(DogBreedapi.class);
        Call<List<ProductClass>> prd = dogBreedapi.getproducts();

        prd.enqueue(new Callback<List<ProductClass>>() {
            @Override
            public void onResponse(Call<List<ProductClass>> call, Response<List<ProductClass>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "error code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<ProductClass> productClasses = response.body();
                ProductAdaptar productAdaptar = new ProductAdaptar(getContext(),productClasses);
                rct1.setAdapter(productAdaptar);
                rct1.setHasFixedSize(true);
                rct1.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
            }

            @Override
            public void onFailure(Call<List<ProductClass>> call, Throwable t) {
                Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }
}