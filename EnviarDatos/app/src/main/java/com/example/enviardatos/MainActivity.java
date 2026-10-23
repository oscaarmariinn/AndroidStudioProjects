package com.example.enviardatos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText escribirFraseText;
    private Button caracterButton;
    private Button palabrasButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVariables();

        caracterButton.setOnClickListener(v -> abrirResultado("CARACTERES"));
        palabrasButton.setOnClickListener(v -> abrirResultado("PALABRAS"));
    }

    private void abrirResultado(String modo) {
        String frase = escribirFraseText.getText().toString();

        Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("FRASE", frase);
        bundle.putString("MODO", modo);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void inicializarVariables() {
        escribirFraseText = findViewById(R.id.escribirFraseText);
        caracterButton = findViewById(R.id.caracterButton);
        palabrasButton = findViewById(R.id.palabrasButton);
    }
}