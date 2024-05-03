package com.example.minor2nd.Activity;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;


import com.example.minor2nd.Adapter.CategoryAdapter;
import com.example.minor2nd.Adapter.ProductFAdapter;
import com.example.minor2nd.Domain.Category;
import com.example.minor2nd.Domain.FProducts;
import com.example.minor2nd.Domain.Location;
import com.example.minor2nd.Domain.Price;
import com.example.minor2nd.Domain.Time;

import com.example.minor2nd.R;
import com.example.minor2nd.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));

        initLocation();
        initTime();
        initPrice();
        initCategory();
        initPopular();
        setVariable();



        // My experiment Location

    }



    private void setVariable() {
        binding.LogoutImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
        binding.searchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=binding.searchEditText.getText().toString();
                if(text.isEmpty()){
                    Intent intent=new Intent(MainActivity.this, ListProductActivity.class);
                    intent.putExtra("text",text);
                    intent.putExtra("isSearch",true);
                    startActivity(intent);
                }
            }
        });
        //binding.fullNameTxt.setText();
        binding.cartBtnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
    }

    private void initPopular() {
        DatabaseReference myRef= database.getReference("Products");
        binding.progressBarBB.setVisibility(View.GONE);
        ArrayList <FProducts> list = new ArrayList<>();
        Query query= myRef.orderByChild("BestFood").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(FProducts.class));

                    }
                    if(list.size()>0){
                        binding.recyclerViewBB.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                                LinearLayoutManager.HORIZONTAL,false));
                        RecyclerView.Adapter adapter = new ProductFAdapter(list);
                        binding.recyclerViewBB.setAdapter(adapter);

                    }
                    binding.progressBarBB.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void initCategory() {
        DatabaseReference myRef= database.getReference("Category");
        binding.progressBarBB.setVisibility(View.GONE);
        ArrayList <Category> list = new ArrayList<>();
      myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Category.class));

                    }
                    if(list.size()>0){
                        binding.recyclerView2.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
                        RecyclerView.Adapter adapter = new CategoryAdapter(list);
                        binding.recyclerView2.setAdapter(adapter);

                    }
                    binding.progressBarcategory.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void initLocation() {
        DatabaseReference myRef=database.getReference("Location");
        ArrayList<Location> list=new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Location.class));
                    }
                    ArrayAdapter<Location> arrayAdapter=new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.locationSpin.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initTime() {
        DatabaseReference myRef=database.getReference("Time");
        ArrayList<Time> list=new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Time.class));
                    }
                    ArrayAdapter<Time> arrayAdapter=new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinTime.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initPrice() {
        DatabaseReference myRef=database.getReference("Price");
        ArrayList<Price> list=new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Price.class));
                    }
                    ArrayAdapter<Price> arrayAdapter=new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinPrice.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}