package com.example.enviardatos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultadoActivity extends AppCompatActivity {

    private TextView fraseResultadoText;
    private TextView resultadoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        fraseResultadoText = findViewById(R.id.fraseResultadoText);
        resultadoText = findViewById(R.id.resultadoText);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            String frase = bundle.getString("FRASE");
            String modo = bundle.getString("MODO");

            fraseResultadoText.setText("Frase: " + frase);

            if (modo.equals("CARACTERES")) {
                resultadoText.setText("Caracteres: " + frase.length());
            } else {
                resultadoText.setText("Palabras: " + contarPalabras(frase));
            }
        }
    }

    private int contarPalabras(String frase) {
        String limpia = frase.trim();
        if (limpia.isEmpty()) {
            return 0;
        }
        // \\s+ = uno o más espacios seguidos, así "hola   mundo" cuenta 2
        return limpia.split("\\s+").length;
    }
}