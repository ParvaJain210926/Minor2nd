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
import com.example.minor2nd.Domain.FProducts;
import com.example.minor2nd.Helper.ChangeNumberItemsListener;
import com.example.minor2nd.Helper.ManagementCart;
import com.example.minor2nd.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewholder> {
    ArrayList<FProducts> list;
    private ManagementCart managementCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdapter(ArrayList<FProducts> list, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.list = list;
        managementCart=new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.vieholder_cart,parent,false);

        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.viewholder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.feeEachItem.setText("Rs."+(list.get(position).getNumberInCart()*list.get(position).getPrice())+"-/");
        holder.total.setText("Quantity" + list.get(position).getNumberInCart()+" X "+ list.get(position).getPrice());
        holder.num.setText(list.get(position).getNumberInCart()+"");

        Glide.with(holder.itemView.getContext()).load(list.get(position).getImagePath()).transform(new CenterCrop(),new RoundedCorners(30))
                .into(holder.picCart);


        holder.plusItem.setOnClickListener(v -> managementCart.plusNumberItem(list, position, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                notifyDataSetChanged();
                changeNumberItemsListener.change();

            }
        }));


        holder.minusItem.setOnClickListener(v -> managementCart.minusNumberItem(list, position, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                notifyDataSetChanged();
                changeNumberItemsListener.change();
            }
        }));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView title,feeEachItem,plusItem,minusItem;
        ImageView picCart;
        TextView total,num;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleProductTxt);
            feeEachItem=itemView.findViewById(R.id.feeEchItem);
            picCart=itemView.findViewById(R.id.picview);
            minusItem=itemView.findViewById(R.id.minusCart);
            plusItem=itemView.findViewById(R.id.plusCart);
            total=itemView.findViewById(R.id.totalEachItem);
            num=itemView.findViewById(R.id.numberItemTxt);
            
        }
    }
}
