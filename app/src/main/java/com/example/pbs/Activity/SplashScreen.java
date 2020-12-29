package com.example.pbs.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.example.pbs.LoginActivity.Login;
import com.example.pbs.R;

public class SplashScreen extends AppCompatActivity {
     public static final String TAG="SplashScreen";
    Handler handler;
    Runnable runnable;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Log.i(TAG,"Activity");

        img = findViewById(R.id.img);
        img.animate().alpha(4000).setDuration(1);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent dsp = new Intent(SplashScreen.this, Login.class);
                startActivity(dsp);
                finish();
            }
        },4000);
    }

}
