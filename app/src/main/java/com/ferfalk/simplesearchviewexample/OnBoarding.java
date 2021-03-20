package com.ferfalk.simplesearchviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OnBoarding extends AppCompatActivity {
    Button mRegisterBtn;
    Button mLoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        mLoginBtn = findViewById(R.id.btn_login);
        mRegisterBtn = findViewById(R.id.btn_signup);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoarding.this,Login.class));
                //Toast.makeText(OnBoarding.this, "Login", Toast.LENGTH_SHORT).show();
            }
        });
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoarding.this,SignUp.class));
                //Toast.makeText(OnBoarding.this, "Register", Toast.LENGTH_SHORT).show();
            }
        });
    }
}