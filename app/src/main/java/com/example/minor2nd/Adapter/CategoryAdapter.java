package com.example.minor2nd.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

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
import com.example.minor2nd.Activity.ListProductActivity;
import com.example.minor2nd.Domain.Category;
import com.example.minor2nd.Domain.FProducts;
import com.example.minor2nd.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder>{
    ArrayList<Category> items;
    Context context;
    private viewholder holder;
    private int position;

    public CategoryAdapter(ArrayList<Category> list) {
        this.items =list;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.viewholder holder,int position) {


        holder.TitleTxt2.setText(items.get(position).getName());

    switch (position){
         case 0:{
         holder.picImg2.setBackgroundResource(R.drawable.btn_2);
         break;}

         case 1:{
             holder.picImg2.setBackgroundResource(R.drawable.btn_2);
             break;}
         case 2:{
             holder.picImg2.setBackgroundResource(R.drawable.btn_2);
             break;
         }
     }

     int drawableResourceId=context.getResources().getIdentifier(items.get(position).getImagePath(),
            "drawable",holder.itemView.getContext().getPackageName());

     Glide.with(context).load(drawableResourceId).into(holder.picImg2);

     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent =new Intent(context, ListProductActivity.class);
             intent.putExtra("CategoryId",items.get(position).getId());
             intent.putExtra("CategoryName",items.get(position).getName());
             context.startActivity(intent);

         }
     });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView TitleTxt2;
        ImageView picImg2;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            TitleTxt2=itemView.findViewById(R.id.catNameTx);
            picImg2=itemView.findViewById(R.id.imgCat);
        }
    }
}
