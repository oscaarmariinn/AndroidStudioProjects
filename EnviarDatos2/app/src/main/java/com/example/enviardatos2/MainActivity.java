package com.example.enviardatos2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.enviardatos2.modelos.Bici;
import com.example.enviardatos2.modelos.Coche;
import com.example.enviardatos2.modelos.Moto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView cochesText, motosText, bicisText;
    private Button crearCocheButton, crearMotoButton, crearBiciButton;

    private ArrayList<Coche> listaCoches;
    private ArrayList<Moto> listaMotos;
    private ArrayList<Bici> listaBicis;

    private ActivityResultLauncher<Intent> launcherCoches;
    private ActivityResultLauncher<Intent> launcherMotos;
    private ActivityResultLauncher<Intent> launcherBicis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVariables();
        inicializarLaunchers();

        crearCocheButton.setOnClickListener(v ->
                launcherCoches.launch(new Intent(MainActivity.this, CreateCocheActivity.class)));

        crearMotoButton.setOnClickListener(v ->
                launcherMotos.launch(new Intent(MainActivity.this, CreateMotoActivity.class)));

        crearBiciButton.setOnClickListener(v ->
                launcherBicis.launch(new Intent(MainActivity.this, CreateBiciActivity.class)));
    }

    private void inicializarLaunchers() {
        launcherCoches = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null && result.getData().getExtras() != null) {
                                Coche coche = (Coche) result.getData().getExtras().getSerializable("COCHE");
                                listaCoches.add(coche);
                                cochesText.setText("Coches: " + listaCoches.size());
                            } else {
                                Toast.makeText(MainActivity.this, "No hay datos", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Ventana cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        launcherMotos = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null && result.getData().getExtras() != null) {
                                Moto moto = (Moto) result.getData().getExtras().getSerializable("MOTO");
                                listaMotos.add(moto);
                                motosText.setText("Motos: " + listaMotos.size());
                            } else {
                                Toast.makeText(MainActivity.this, "No hay datos", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Ventana cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        launcherBicis = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null && result.getData().getExtras() != null) {
                                Bici bici = (Bici) result.getData().getExtras().getSerializable("BICI");
                                listaBicis.add(bici);
                                bicisText.setText("Bicis: " + listaBicis.size());
                            } else {
                                Toast.makeText(MainActivity.this, "No hay datos", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Ventana cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    private void inicializarVariables() {
        cochesText = findViewById(R.id.cochesText);
        motosText = findViewById(R.id.motosText);
        bicisText = findViewById(R.id.bicisText);

        crearCocheButton = findViewById(R.id.crearCocheButton);
        crearMotoButton = findViewById(R.id.crearMotoButton);
        crearBiciButton = findViewById(R.id.crearBiciButton);

        listaCoches = new ArrayList<>();
        listaMotos = new ArrayList<>();
        listaBicis = new ArrayList<>();
    }
}