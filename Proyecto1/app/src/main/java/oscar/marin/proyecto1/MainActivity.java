package oscar.marin.proyecto1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

import oscar.marin.proyecto1.modelos.LineaPedido;

public class MainActivity extends AppCompatActivity {

    private TextView lbTotal;
    private TextView lbNumLineas;
    private Button btnAdd;
    private LinearLayout contenedor;

    private ArrayList<LineaPedido> lineas;
    private ActivityResultLauncher<Intent> launcherAddProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarVariables();

        lineas = new ArrayList<>();
        inicializaLauncher();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddProductoActivity.class);
                launcherAddProducto.launch(intent);
            }
        });

        mostrarLineas();   // estado inicial: comanda vacía y total 0
    }

    private void inicializarVariables() {
        lbTotal = findViewById(R.id.lbTotalMain);
        lbNumLineas = findViewById(R.id.lbNumLineasMain);
        btnAdd = findViewById(R.id.btnAddMain);
        contenedor = findViewById(R.id.contenedorMain);
    }

    private void inicializaLauncher() {
        launcherAddProducto = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null && result.getData().getExtras() != null) {
                                LineaPedido linea = (LineaPedido)
                                        result.getData().getExtras().getSerializable("LINEA");
                                if (linea != null) {
                                    lineas.add(linea);
                                    mostrarLineas();
                                    Toast.makeText(MainActivity.this,
                                            getString(R.string.producto_anadido), Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(MainActivity.this,
                                    getString(R.string.operacion_cancelada), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    private void mostrarLineas() {
        contenedor.removeAllViews();   // evita duplicados al repintar

        if (lineas.isEmpty()) {
            TextView vacio = new TextView(MainActivity.this);
            vacio.setText(getString(R.string.comanda_vacia));
            contenedor.addView(vacio);
        }

        double total = 0;
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);

        for (LineaPedido linea : lineas) {
            View vista = layoutInflater.inflate(R.layout.item_linea_pedido, null);

            TextView lbProducto = vista.findViewById(R.id.lbProductoItem);
            TextView lbTipo = vista.findViewById(R.id.lbTipoItem);
            TextView lbCantidad = vista.findViewById(R.id.lbCantidadItem);
            TextView lbSubtotal = vista.findViewById(R.id.lbSubtotalItem);

            lbProducto.setText(linea.getProducto());
            lbTipo.setText(linea.getTipo());
            lbCantidad.setText(getString(R.string.formato_cantidad,
                    linea.getCantidad(), linea.getPrecioUnitario()));
            lbSubtotal.setText(getString(R.string.formato_subtotal, linea.getSubtotal()));

            vista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LINEA", linea);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

            contenedor.addView(vista);
            total += linea.getSubtotal();
        }

        lbTotal.setText(getString(R.string.formato_total, total));
        lbNumLineas.setText(getString(R.string.formato_num_lineas, lineas.size()));
    }
}