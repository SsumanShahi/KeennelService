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
import com.suman.kennelservice.model.ProductClass;
import com.suman.kennelservice.strictmode.StrictModeClass;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class ProductAdaptar extends RecyclerView.Adapter<ProductAdaptar.ProductViewHolder> {

    Context mcontext;
    List<ProductClass> productClasses;

    public ProductAdaptar(Context mcontext, List<ProductClass> productClasses) {
        this.mcontext = mcontext;
        this.productClasses = productClasses;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_layout,parent,false);

        return  new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final ProductClass productClass = productClasses.get(position);
        final String imgPath = url.imagePath + productClass.getImage();
        holder.tvname.setText(productClass.getName());
        holder.tvprice.setText(productClass.getPrice());
        StrictModeClass.StrictMode();
        try {
            URL url = new URL(imgPath);
            holder.product_image.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return productClasses.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView product_image;
        private TextView tvname, tvprice;

        public ProductViewHolder(@NonNull View itemView) {

            super(itemView);

            product_image = itemView.findViewById(R.id.product_image);
            tvname = itemView.findViewById(R.id.tvname);
            tvprice = itemView.findViewById(R.id.tvprice);
        }
    }
}
