package com.example.minor2nd.Domain;


public class User {

    public  String username;
    public  String password;
    public  String email;

    public User(){}

    public User (String username,String password,String email)
    {
        this.username = username;
        this.password = password;
        this.email = email;

    }
    public String getUser(){
        return username;
    }
}

