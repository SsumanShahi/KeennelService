package com.suman.kennelservice.adaptar;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.activity.DogBreedDetailActivity;
import com.suman.kennelservice.activity.DogDetailActivity;
import com.suman.kennelservice.model.MyDog;
import com.suman.kennelservice.model.MyDogCRUD;
import com.suman.kennelservice.strictmode.StrictModeClass;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class DogListAdaptar extends RecyclerView.Adapter<DogListAdaptar.DogListViewHolder> {
    Context mcontext;
    List<MyDogCRUD> myDogs;

    public DogListAdaptar(Context mcontext, List<MyDogCRUD> myDogs) {
        this.mcontext = mcontext;
        this.myDogs = myDogs;
    }

    @NonNull
    @Override
    public DogListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_list, parent, false);

        return new DogListAdaptar.DogListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogListViewHolder holder, int position) {
        final MyDogCRUD myDog = myDogs.get(position);
        final String imgPath = url.imagePath + myDog.getImage();
        holder.tvDogName.setText(myDog.getPetName());
        holder.tvDogBreed.setText(myDog.getBreed());
        Log.d("EditDogDetailsActivity", "response other than 200"+myDog.getId());

        StrictModeClass.StrictMode();
        try {
            URL url = new URL(imgPath);
            holder.ivDog.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DogListAdapter","on click button"+myDog.getId());
                Intent intent = new Intent(mcontext, DogDetailActivity.class);
                intent.putExtra("DogDetail", myDog);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myDogs.size();
    }

    public class DogListViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivDog;
        private TextView tvDogName, tvDogBreed;
        private CardView cvDogItem;
        private ConstraintLayout constraintLayout;


        public DogListViewHolder(@NonNull View itemView) {
            super(itemView);
            ivDog = itemView.findViewById(R.id.iv_dog);
            tvDogName = itemView.findViewById(R.id.tv_dog_name);
            tvDogBreed = itemView.findViewById(R.id.tv_dog_breed);
            cvDogItem = itemView.findViewById(R.id.cv_dog_list);
            constraintLayout = itemView.findViewById(R.id.cv);



        }
    }
}
