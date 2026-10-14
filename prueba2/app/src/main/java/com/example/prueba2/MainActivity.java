package com.example.prueba2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnAbrir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Log.e("ESTADOS", "1 - Estoy en onCreate");

        btnAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        SecondActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("ESTADOS", "6 - Estoy en onDestroy");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.e("ESTADOS", "5 - Estoy en onStop");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.e("ESTADOS", "4 - Estoy en onPause");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("ESTADOS","3 - Estoy en onResume");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e("ESTADOS", "2 - Estoy en onStart");
    }

    private void inicializaVistas() {
        btnAbrir = findViewById(R.id.btnAbrirMain);
    }

}