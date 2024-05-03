package com.example.minor2nd.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.minor2nd.Adapter.CartAdapter;
import com.example.minor2nd.Helper.ChangeNumberItemsListener;
import com.example.minor2nd.Helper.ManagementCart;
import com.example.minor2nd.R;
import com.example.minor2nd.databinding.ActivityCartBinding;

public class CartActivity extends BaseActivity {
    private ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagementCart managementCart;
    private double tax;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        managementCart=new ManagementCart(this);

        setVariable();
        calculateCart();
        initList();
    }

    private void initList() {
        if(managementCart.getListCart().isEmpty()){
            binding.emptyCartTxt.setVisibility(View.VISIBLE);
            binding.scrollViewCart.setVisibility(View.GONE);
        }
        else{
            binding.emptyCartTxt.setVisibility(View.GONE);
            binding.scrollViewCart.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        binding.cardView.setLayoutManager(linearLayoutManager);//check Here
        adapter=new CartAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCart();
            }
        });
        binding.cardView.setAdapter(adapter);
    }

    private void calculateCart() {
        double percentTax=0.02; //2% tax
        double delivery=5; //rupees

        tax=Math.round(managementCart.getTotalFee()*(percentTax*100.0))/100;
        double total=Math.round((managementCart.getTotalFee()+tax+delivery)*100)/100;
        double itemTotal=Math.round(managementCart.getTotalFee()*100)/100;

        binding.totalFeesTxt.setText("Rs."+itemTotal+"-/");
        binding.totalTaxTxt.setText("Rs."+tax+"-/");
        binding.delivertFeeTxt.setText("Rs."+delivery+"-/");
        binding.totalTxt.setText("Rs."+total+"-/");

    }

    private void setVariable() {
        binding.backButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}