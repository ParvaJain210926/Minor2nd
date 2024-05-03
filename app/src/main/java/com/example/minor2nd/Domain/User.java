package com.example.minor2nd.Domain;

import com.example.minor2nd.Activity.MainActivity;

public class User  extends MainActivity {
    public String userName;
    public String userPassword;

    public String email;


    public User(String userName, String userPassword,String email){
        this.userName=userName;
        this.email=email;
        this.userPassword=userPassword;
    }

    public String getUserName() {
        return userName;
    }
}
