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

import com.squareup.picasso.Picasso;
import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.activity.DogBreedDetailActivity;
import com.suman.kennelservice.model.Appointment;
import com.suman.kennelservice.model.Puppy;
import com.suman.kennelservice.strictmode.StrictModeClass;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class PuppyAdapater extends RecyclerView.Adapter<PuppyAdapater.PuppyViewHolder>{

    Context mcontext;
    List<Puppy> puppies;

    public PuppyAdapater(Context mcontext, List<Puppy> puppies) {
        this.mcontext = mcontext;
        this.puppies = puppies;
    }

    @NonNull
    @Override
    public PuppyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.puppy_l,parent,false);

        return new PuppyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PuppyViewHolder holder, int position) {

        final Puppy puppy = puppies.get(position);
        holder.tvpupname.setText(puppy.getName());
        holder.tvpupdetail.setText(puppy.getDetail());
        final String imgPath = url.imagePath + puppy.getImage();
        StrictModeClass.StrictMode();
        try {
            URL url = new URL(imgPath);
            holder.pup_image.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return puppies.size();
    }

    public class PuppyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvpupname,tvpupdetail;
        private ImageView pup_image;
        public PuppyViewHolder(@NonNull View itemView) {
            super(itemView);

            pup_image = itemView.findViewById(R.id.pup_image);
            tvpupname = itemView.findViewById(R.id.tvpupname);
            tvpupdetail = itemView.findViewById(R.id.tvpupdetail);
        }
    }
}
