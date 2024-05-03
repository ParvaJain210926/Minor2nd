package com.example.minor2nd.Activity;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.minor2nd.Adapter.ProductListAdapter;
import com.example.minor2nd.Domain.FProducts;

import com.example.minor2nd.databinding.ActivityListProductBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListProductActivity extends BaseActivity {
    ActivityListProductBinding binding;
    private RecyclerView.Adapter adapterListProduct;
    private int categoryId;
    private String categoryName;
    private String searchText;
    private boolean inSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityListProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        initList();
        setVariable();
    }

    private void setVariable() {
    }

    private void initList() {
        DatabaseReference myRef=database.getReference("Products");
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<FProducts> list= new ArrayList<>();

        Query query;

        if(inSearch){
            query=myRef.orderByChild("Title").startAt(searchText).endAt(searchText+'\uf8ff');
        }
        else {
            query = myRef.orderByChild("CategoryId").equalTo(categoryId);
        }
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(FProducts.class));
                    }
                    if(list.size()>0){
                        binding.productlistView.setLayoutManager(new GridLayoutManager(ListProductActivity.this,2));
                        adapterListProduct = new ProductListAdapter(list);
                        binding.productlistView.setAdapter(adapterListProduct);

                    }
                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIntentExtra() {
        categoryId=getIntent().getIntExtra("CategoryId",0);
        categoryName=getIntent().getStringExtra("Category");
        searchText=getIntent().getStringExtra("text");
        inSearch=getIntent().getBooleanExtra("isSearch",false);

        binding.titleProductTxt.setText(categoryName);
        binding.bckButton.setOnClickListener(v -> {
            startActivity(new Intent(ListProductActivity.this,MainActivity.class));
            finish();
        });



    }
}