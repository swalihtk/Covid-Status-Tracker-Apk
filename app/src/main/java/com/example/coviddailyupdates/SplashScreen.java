package com.example.coviddailyupdates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textView=findViewById(R.id.tw_dailyUpdates_string);

        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_screen_string_animation);
        textView.startAnimation(hyperspaceJumpAnimation);


        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }
}