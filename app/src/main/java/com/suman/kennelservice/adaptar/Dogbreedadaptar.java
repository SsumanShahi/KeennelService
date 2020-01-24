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

import com.suman.kennelservice.Main2Activity;
import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.model.Dogbreeds;
import com.suman.kennelservice.strictmode.StrictModeClass;
import com.suman.kennelservice.ui.gallery.DogbreeddetailsFragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Dogbreedadaptar extends RecyclerView.Adapter<Dogbreedadaptar.dogbreedViewHolder>{
    Context mcontext;
    List<Dogbreeds> dogbreeds;

    public Dogbreedadaptar(Context mcontext, List<Dogbreeds> dogbreeds) {
        this.mcontext = mcontext;
        this.dogbreeds = dogbreeds;
    }

    @NonNull
    @Override
    public dogbreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.dogbree_layout,parent,false);

        return new dogbreedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dogbreedViewHolder holder, int position) {

        final Dogbreeds dogbreeds1 = dogbreeds.get(position);
        final String imgPath = url.imagePath + dogbreeds1.getImage();
        holder.tvdogname.setText(dogbreeds1.getName());
        holder.tvdogdescription.setText(dogbreeds1.getDescription());
        StrictModeClass.StrictMode();
        try{
            URL url = new URL(imgPath);
            holder.card1.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
            holder.card1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext, Main2Activity.class);
                    intent.putExtra("ImageUrl",imgPath);
                    intent.putExtra("DogName",dogbreeds1.getName());
                    intent.putExtra("DogDescription",dogbreeds1.getDescription());
                    mcontext.startActivity(intent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dogbreeds.size();
    }

    public class dogbreedViewHolder extends RecyclerView.ViewHolder {

        private ImageView card1;
        private TextView tvdogname, tvdogdescription;


        public dogbreedViewHolder(@NonNull View itemView) {
            super(itemView);

            card1 = itemView.findViewById(R.id.card1);
            tvdogname = itemView.findViewById(R.id.tvdogname);
            tvdogdescription = itemView.findViewById(R.id.tvdogdescription);




        }
    }


}
