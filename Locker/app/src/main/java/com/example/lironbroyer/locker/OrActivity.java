package com.example.lironbroyer.locker;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OrActivity extends AppCompatActivity {
    private Intent mServiceIntent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_or);
    }

    public void onBobClick(View view) {
        mServiceIntent = new Intent(this, LockerService.class);
        mServiceIntent.setData(Uri.parse("http://localhost:123/Liron"));
        this.startService(mServiceIntent);
    }
}
