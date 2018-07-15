package com.example.android.meetingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;

public class splash_screen extends AppCompatActivity{
    @Override
   protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent i= new Intent(splash_screen.this, MainActivity.class);
                startActivity(i);

                finish();
            }
        },2000);
    }


}
