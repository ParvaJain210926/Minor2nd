package com.example.minor2nd.Activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.minor2nd.Adapter.ProductListAdapter;
import com.example.minor2nd.Domain.FProducts;
import com.example.minor2nd.databinding.ActivityAllProductsBinding;
import com.example.minor2nd.databinding.ActivityListProductBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllProductsActivity extends BaseActivity {
    ActivityAllProductsBinding binding;
    private RecyclerView.Adapter adapterListProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityAllProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initList();
        binding.bckButtonAllProducts.setOnClickListener(v -> {
            startActivity(new Intent(AllProductsActivity.this, MainActivity.class));
            finish();
        });

    }
    private void initList() {
        DatabaseReference myRef=database.getReference("Products");
        binding.progressBarAll.setVisibility(View.VISIBLE);
        ArrayList<FProducts> list= new ArrayList<>();

        Query query = myRef.getDatabase().getReference("Products");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(FProducts.class));
                    }
                    if(list.size()>0){
                        binding.productListView.setLayoutManager(new GridLayoutManager(AllProductsActivity.this,2));
                        adapterListProduct = new ProductListAdapter(list);
                        binding.productListView.setAdapter(adapterListProduct);

                    }
                    binding.progressBarAll.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AllProductsActivity.this,"Failed",Toast.LENGTH_SHORT).show();

            }
        });
    }
}