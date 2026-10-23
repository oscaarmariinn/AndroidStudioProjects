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

import com.example.enviardatos2.modelos.Moto;

public class CreateMotoActivity extends AppCompatActivity {

    private EditText marcaMotoText, modeloMotoText, cilindradaMotoText;
    private Button cancelarMotoButton, crearMotoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_moto);

        inicializaVistas();

        cancelarMotoButton.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        crearMotoButton.setOnClickListener(v -> {
            String marca = marcaMotoText.getText().toString();
            String modelo = modeloMotoText.getText().toString();
            String cilindrada = cilindradaMotoText.getText().toString();

            if (marca.isEmpty() || modelo.isEmpty() || cilindrada.isEmpty()) {
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show();
                return;
            }

            Moto moto = new Moto(marca, modelo, Integer.parseInt(cilindrada));

            Bundle bundle = new Bundle();
            bundle.putSerializable("MOTO", moto);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    private void inicializaVistas() {
        marcaMotoText = findViewById(R.id.marcaMotoText);
        modeloMotoText = findViewById(R.id.modeloMotoText);
        cilindradaMotoText = findViewById(R.id.cilindradaMotoText);
        cancelarMotoButton = findViewById(R.id.cancelarMotoButton);
        crearMotoButton = findViewById(R.id.crearMotoButton);
    }
}