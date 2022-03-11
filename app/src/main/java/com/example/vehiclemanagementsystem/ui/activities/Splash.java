package com.example.vehiclemanagementsystem.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.vehiclemanagementsystem.R;
import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        auth = FirebaseAuth.getInstance();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUsers();
            }
        }, 3000);

    }

    private void checkUsers() {
        if (auth.getCurrentUser() != null) {

            Intent i = new Intent(Splash.this, MainActivity.class);
            startActivity(i);
            finish();

        } else {
            Intent i = new Intent(Splash.this, LoginActivity.class);
            startActivity(i);
            finish();

        }
    }

}