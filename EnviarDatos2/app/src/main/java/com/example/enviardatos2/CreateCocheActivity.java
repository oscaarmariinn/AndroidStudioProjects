package com.example.enviardatos2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.enviardatos2.modelos.Coche;

public class CreateCocheActivity extends AppCompatActivity {

    private EditText marcaCocheText, modeloCocheText, colorCocheText;
    private Button cancelarCocheButton, crearCocheButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_coche);

        inicializaVistas();

        cancelarCocheButton.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        crearCocheButton.setOnClickListener(v -> {
            String marca = marcaCocheText.getText().toString();
            String modelo = modeloCocheText.getText().toString();
            String color = colorCocheText.getText().toString();

            if (marca.isEmpty() || modelo.isEmpty() || color.isEmpty()) {
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show();
                return;
            }

            Coche coche = new Coche(marca, modelo, color);

            Bundle bundle = new Bundle();
            bundle.putSerializable("COCHE", coche);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    private void inicializaVistas() {
        marcaCocheText = findViewById(R.id.marcaCocheText);
        modeloCocheText = findViewById(R.id.modeloCocheText);
        colorCocheText = findViewById(R.id.colorCocheText);
        cancelarCocheButton = findViewById(R.id.cancelarCocheButton);
        crearCocheButton = findViewById(R.id.crearCocheButton);
    }
}