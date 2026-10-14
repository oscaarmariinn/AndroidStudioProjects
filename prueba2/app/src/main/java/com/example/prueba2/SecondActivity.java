package com.example.prueba2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("ESTADOS", "6 - Estoy en onDestroy- SECOND");
        startActivity(new Intent(SecondActivity.this,
                SecondActivity.class));
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.e("ESTADOS 2", "5 - Estoy en onStop");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.e("ESTADOS 2", "4 - Estoy en onPause");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("ESTADOS 2","3 - Estoy en onResume");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e("ESTADOS 2", "2 - Estoy en onStart");
    }
}