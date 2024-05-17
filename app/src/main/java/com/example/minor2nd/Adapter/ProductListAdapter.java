package com.example.minor2nd.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.minor2nd.Activity.ProductDetailsActivity;
import com.example.minor2nd.Domain.FProducts;
import com.example.minor2nd.R;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.viewholder> {
    ArrayList<FProducts> items;
    Context context;

    public ProductListAdapter(ArrayList<FProducts> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ProductListAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate= LayoutInflater.from(context).inflate(R.layout.viewholder_product_list,parent,false);
        return new viewholder(inflate);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.viewholder holder, int position) {
        holder.TitleTxt.setText(items.get(position).getTitle());
        holder.timeTxt.setText(items.get(position).getTimeValue());
        holder.price.setText("₹"+items.get(position).getPrice());
        holder.rateTxt.setText(""+items.get(position).getStarRate());
        Glide.with(context)
                .load(items.get(position).getImagePath()).transform(new CenterCrop(),new RoundedCorners(30))
                .into(holder.picIm);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,ProductDetailsActivity.class);
                intent.putExtra("object",items.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        TextView TitleTxt,price, rateTxt,timeTxt;
        ImageView picIm;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            TitleTxt=itemView.findViewById(R.id.titleTxtPl);
            price=itemView.findViewById(R.id.priceTxtPl);
            timeTxt=itemView.findViewById(R.id.TimeTxtPL);
            rateTxt =itemView.findViewById(R.id.rateTxtPl);
            picIm=itemView.findViewById(R.id.ImgPL);

        }
    }
}
