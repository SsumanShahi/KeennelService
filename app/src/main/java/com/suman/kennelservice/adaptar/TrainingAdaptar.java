package com.suman.kennelservice.adaptar;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.activity.DogBreedDetailActivity;
import com.suman.kennelservice.model.Dogbreeds;
import com.suman.kennelservice.model.Training;
import com.suman.kennelservice.strictmode.StrictModeClass;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class TrainingAdaptar extends RecyclerView.Adapter<TrainingAdaptar.TrainingViewHolder> {

    Context mcontext;
    List<Training> trainingList;

    public TrainingAdaptar(Context mcontext, List<Training> trainingList) {
        this.mcontext = mcontext;
        this.trainingList = trainingList;
    }

    @NonNull
    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.training_layout,parent,false);

        return new TrainingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingViewHolder holder, int position) {

        final Training training = trainingList.get(position);
        final String imgPath = url.imagePath + training.getImage();
        holder.trainname.setText(training.getTrainingName());
        holder.trainsteps.setText(training.getSteps());
        StrictModeClass.StrictMode();
        try {
            URL url = new URL(imgPath);
            holder.trimage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public int getItemCount() {
        return trainingList.size();
    }

    public class TrainingViewHolder extends RecyclerView.ViewHolder {

        private ImageView trimage;
        private TextView trainname,trainsteps;
        public TrainingViewHolder(@NonNull View itemView) {
            super(itemView);

            trainname = itemView.findViewById(R.id.trainname);
            trimage = itemView.findViewById(R.id.trimage);
            trainsteps = itemView.findViewById(R.id.trainsteps);
        }
    }
}
