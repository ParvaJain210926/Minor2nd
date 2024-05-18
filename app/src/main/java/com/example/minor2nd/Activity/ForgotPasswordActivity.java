package com.example.minor2nd.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.minor2nd.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends BaseActivity {

        EditText editTextEmail;
        Button forgetButton;

        FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_forgot_password);
            editTextEmail=(EditText) findViewById(R.id.editTextTextForgetEmail);
            forgetButton=findViewById(R.id.buttonForgotPasswordButton);
            mAuth = FirebaseAuth.getInstance();

            forgetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetPassword();
                }
            });
        }

        private void resetPassword()
        {
            String email = editTextEmail.getText().toString().trim();
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                editTextEmail.setError("Please enter a valid Email");
                editTextEmail.requestFocus();
            }

            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(ForgotPasswordActivity.this,"Please check your Mail Box to reset Password ",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                    else
                    {

                        Toast.makeText(ForgotPasswordActivity.this,"Failed to reset Password,Try entering Registered email",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }