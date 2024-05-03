package com.example.minor2nd.Adapter;

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
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.minor2nd.Activity.ProductDetailsActivity;
import com.example.minor2nd.Domain.FProducts;
import com.example.minor2nd.R;

import java.util.ArrayList;

public class ProductFAdapter extends RecyclerView.Adapter<ProductFAdapter.viewholder>{
    ArrayList<FProducts> items;
    Context context;

    public ProductFAdapter(ArrayList<FProducts> list) {
        this.items =list;
    }

    @NonNull
    @Override
    public ProductFAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_fproduct,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductFAdapter.viewholder holder, int position) {
        holder.TitleTxt.setText(items.get(position).getTitle());
        holder.priceTxt.setText("₹"+ items.get(position).getPrice()+"/-");
        holder.timeTxt.setText(items.get(position).getTimeValue()+"Min");
        holder.starTxt.setText(""+items.get(position).getStarRate());

        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new FitCenter(),new RoundedCorners(30))
                .into(holder.picImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("object",items.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView TitleTxt,priceTxt,starTxt,timeTxt;
        ImageView picImg;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            TitleTxt=itemView.findViewById(R.id.titleText);
            priceTxt=itemView.findViewById(R.id.pricetxt);
            starTxt=itemView.findViewById(R.id.starText1);
            timeTxt=itemView.findViewById(R.id.timeTxt);
            picImg=itemView.findViewById(R.id.picImg);
        }
    }
}
