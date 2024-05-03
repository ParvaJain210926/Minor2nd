package com.example.minor2nd.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.minor2nd.R;
import com.example.minor2nd.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity{
    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setVariable();
        getWindow().setStatusBarColor(Color.parseColor("#569DE6"));

    }
    private void setVariable(){
        binding.buttonSignIn.setOnClickListener(v -> {
            if(mAuth.getCurrentUser()!=null) {
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            }
            else{
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
            }


        });
        binding.buttonSignUp.setOnClickListener(v -> startActivity(new Intent(IntroActivity.this, SignUpActivity.class)));

    }
}