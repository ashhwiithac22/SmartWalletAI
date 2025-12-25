package com.example.smartwallet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Simple splash screen with delay
        new Handler().postDelayed(() -> {
            // For now, directly go to LoginActivity
            // In real app, check Firebase auth here
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }, 1500);
    }
}