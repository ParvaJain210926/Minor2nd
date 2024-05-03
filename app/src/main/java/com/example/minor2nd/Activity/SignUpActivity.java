package com.example.minor2nd.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.minor2nd.Domain.User;
import com.example.minor2nd.R;
import com.example.minor2nd.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends BaseActivity {
    ActivitySignUpBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        setVariable();
    }
    private void setVariable(){
        binding.signIntext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });

        binding.buttonSignUpScreen.setOnClickListener(v -> {
            String email = binding.editTextEmail.getText().toString();
            String password = binding.editTextPassword.getText().toString();
            String Name = binding.editTextFullName.getText().toString();

            if (email.isEmpty()) {
                binding.editTextEmail.setError("Please Enter Email");
                binding.editTextEmail.requestFocus();
            } else if (password.isEmpty() || password.length() < 8) {
                binding.editTextPassword.setError("Please enter a valid 8 character Password");
                binding.editTextPassword.requestFocus();
            } else if (Name.isEmpty()) {
                binding.editTextFullName.setError("Please Enter your Name");
                binding.editTextFullName.requestFocus();
            } else {


                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    public void onComplete(Task <AuthResult> task){
                        if(task.isSuccessful()){
                            Log.i(TAG, "Complete");
                            Toast.makeText(SignUpActivity.this, "User registered Successfully", Toast.LENGTH_SHORT).show();
                            SignUpActivity.this.startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                        } else {
                            Log.i(TAG, "Failure" + task.getException());
                            Toast.makeText(SignUpActivity.this, "Registration Fails", Toast.LENGTH_SHORT).show();

                        }
                    }

               /*public void onComplete(@NonNull Task<AuthResult> task) {

                   if (task.isSuccessful()) {
                       User user = new User(Name, password, email);
                       FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {

                               if (task.isSuccessful()) {
                                   Log.i(TAG, "Complete");
                                   Toast.makeText(SignUpActivity.this, "User registered Successfully", Toast.LENGTH_SHORT).show();
                                   SignUpActivity.this.startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                               } else {
                                   Log.i(TAG, "Failure" + task.getException());
                                   Toast.makeText(SignUpActivity.this, "Registration Fails", Toast.LENGTH_SHORT).show();

                               }
                           }
                       });
                   }
               }
           });
           }*/

                });
            }
        });
    }

}