package com.example.minor2nd.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.minor2nd.Domain.FProducts;
import com.example.minor2nd.Helper.ManagementCart;
import com.example.minor2nd.R;
import com.example.minor2nd.databinding.ActivityProductDetailsBinding;

public class ProductDetailsActivity extends BaseActivity {
    ActivityProductDetailsBinding binding;
    private FProducts object;
    private int num=0 ;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));

        getIntentExtra();
        setVariable();

    }

    private void setVariable() {
        managementCart=new ManagementCart(this);
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
        Glide.with(ProductDetailsActivity.this).load(object.getImagePath()).into(binding.detailImageView);

        binding.priceTxtView.setText("Rs."+object.getPrice());
        binding.titleTextView.setText(object.getTitle());
        binding.descriptionTextView.setText(object.getDescription());
        binding.ratingTxt.setText(object.getStarRate()+"Rating");
        binding.ratingBar.setRating((float)object.getStarRate());
        binding.totalPriceTextView.setText(num*object.getPrice()+"Rs");

        binding.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num=num+1;
                binding.numQuantitytxt.setText(num +" ");
                binding.totalPriceTextView.setText("Rs."+(num* object.getPrice())+"/-");
            }
        });
        binding.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num>=1){
                num=num-1;
                binding.numQuantitytxt.setText(num+"");
                binding.totalPriceTextView.setText("Rs."+(num* object.getPrice())+"/-");
            }}
        });
        binding.addtoCartButton.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                    if(num==0) {
                        Toast.makeText(ProductDetailsActivity.this, "Nothing to Add", Toast.LENGTH_SHORT).show();

                    }
                    else{
                object.setNumberInCart(num);
                managementCart.insertProductCart(object);
            }}
        });

    }

    private void getIntentExtra() {
        object= (FProducts) getIntent().getSerializableExtra("object");

    }
}