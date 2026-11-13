package oscar.marin.elementoscodigo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import oscar.marin.elementoscodigo.databinding.ActivityMainBinding;
import oscar.marin.elementoscodigo.models.AlumnoModel;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> addAlumnoLauncher;

    private ArrayList<AlumnoModel> alumnoModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        alumnoModelArrayList = new ArrayList<>();
        inicializaLauncher();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAlumnoLauncher.launch(new Intent(MainActivity.this,
                        CrearAlumnoActivity.class));
            }
        });
    }

    private void inicializaLauncher() {
        addAlumnoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null &&
                                    result.getData().getExtras() != null) {
                                AlumnoModel alumnoModel =
                                        (AlumnoModel) result.getData().getExtras().getSerializable("ALUMNO");
//FALTA MOSTRAR LA INFORMACIÓN
                                if (alumnoModel != null) {
                                    alumnoModelArrayList.add(alumnoModel);
                                    mostrarAlumnos();
                                }
                            }
                        }
                    }
                }
        );
    }
    private void mostrarAlumnos(){
        binding.contentMain.contenedorMain.removeAllViews();
        for (AlumnoModel alumno:alumnoModelArrayList) {
            LayoutInflater layoutInflater =
                    LayoutInflater.from(MainActivity.this);
// getLayoutInflater también se podría usar
            View alumnoView =
                    layoutInflater.inflate(R.layout.alumno_model_view, null);
            TextView txtNombre =
                    alumnoView.findViewById(R.id.lbNombreAlumnoView);
            TextView txtApellidos =
                    alumnoView.findViewById(R.id.lbApellidosAlumnoView);
            TextView txtCiclo =
                    alumnoView.findViewById(R.id.lbCicloAlumnoView);
            TextView txtGrupo =
                    alumnoView.findViewById(R.id.lbGrupoAlumnoView);
            txtNombre.setText(alumno.getNombre());
            txtApellidos.setText(alumno.getApellidos());
            txtCiclo.setText(alumno.getCiclo());
            txtGrupo.setText(String.valueOf(alumno.getGrupo()));
            binding.contentMain.contenedorMain.addView(alumnoView);
        }
    }
}