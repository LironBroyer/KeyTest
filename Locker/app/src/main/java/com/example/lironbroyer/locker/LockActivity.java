package com.example.lironbroyer.locker;

import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        Intent startHomeScreen=new Intent(Intent.ACTION_MAIN);
        startHomeScreen.addCategory(Intent.CATEGORY_HOME);
        startHomeScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(startHomeScreen);


    }
}
