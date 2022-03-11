package com.example.vehiclemanagementsystem.model;

public class UserModel {
     String name;
     String email;
     String phone;
     String id;
     String password;

    public UserModel() {
    }

    public UserModel(String name, String email, String phone, String id, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

