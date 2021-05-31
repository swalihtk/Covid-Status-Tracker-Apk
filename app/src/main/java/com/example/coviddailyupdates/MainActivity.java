package com.example.coviddailyupdates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.UiModeManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void changeToWorldCorona(View view){
        Intent intent=new Intent(MainActivity.this,WorldCorona.class);
        startActivity(intent);
    }
    public void changeToIndiaCorona(View view){
        Intent intent=new Intent(MainActivity.this,IndiaCorona.class);
        startActivity(intent);
    }
    public void changeToAboutUs(View view){
        Intent intent=new Intent(MainActivity.this, AboutUs.class);
        startActivity(intent);
    }
}