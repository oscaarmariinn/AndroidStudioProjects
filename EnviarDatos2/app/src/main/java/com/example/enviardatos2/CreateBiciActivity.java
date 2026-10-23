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

import com.example.enviardatos2.modelos.Bici;

public class CreateBiciActivity extends AppCompatActivity {

    private EditText marcaBiciText, pulgadasBiciText;
    private Button cancelarBiciButton, crearBiciButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bici);

        inicializaVistas();

        cancelarBiciButton.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        crearBiciButton.setOnClickListener(v -> {
            String marca = marcaBiciText.getText().toString();
            String pulgadas = pulgadasBiciText.getText().toString();

            if (marca.isEmpty() || pulgadas.isEmpty()) {
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show();
                return;
            }

            Bici bici = new Bici(marca, Integer.parseInt(pulgadas));

            Bundle bundle = new Bundle();
            bundle.putSerializable("BICI", bici);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    private void inicializaVistas() {
        marcaBiciText = findViewById(R.id.marcaBiciText);
        pulgadasBiciText = findViewById(R.id.pulgadasBiciText);
        cancelarBiciButton = findViewById(R.id.cancelarBiciButton);
        crearBiciButton = findViewById(R.id.crearBiciButton);
    }
}