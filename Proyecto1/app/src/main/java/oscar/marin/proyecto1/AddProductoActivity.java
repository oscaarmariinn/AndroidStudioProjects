package oscar.marin.proyecto1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import oscar.marin.proyecto1.modelos.LineaPedido;


public class AddProductoActivity extends AppCompatActivity {

    private EditText txtProducto;
    private EditText txtCantidad;
    private Spinner spTipo;
    private Button btnCancelar;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_producto);
        inicializarVariables();

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LineaPedido linea = crearLinea();
                if (linea != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LINEA", linea);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(AddProductoActivity.this,
                            getString(R.string.faltan_datos), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inicializarVariables() {
        txtProducto = findViewById(R.id.txtProductoAdd);
        txtCantidad = findViewById(R.id.txtCantidadAdd);
        spTipo = findViewById(R.id.spTipoAdd);
        btnCancelar = findViewById(R.id.btnCancelarAdd);
        btnGuardar = findViewById(R.id.btnGuardarAdd);
    }

    private LineaPedido crearLinea() {
        String producto = txtProducto.getText().toString();
        String cantidadTexto = txtCantidad.getText().toString();

        if (producto.isEmpty() || cantidadTexto.isEmpty()) {
            return null;
        }
        if (spTipo.getSelectedItemPosition() == 0) {
            return null;
        }

        int cantidad = Integer.parseInt(cantidadTexto);
        if (cantidad <= 0) {
            return null;
        }

        String tipo = (String) spTipo.getSelectedItem();
        double precio = precioSegunTipo(spTipo.getSelectedItemPosition());

        return new LineaPedido(producto, tipo, cantidad, precio);
    }

    private double precioSegunTipo(int posicion) {
        switch (posicion) {
            case 1: return 2.00;   // Bebida
            case 2: return 3.50;   // Tapa
            case 3: return 8.00;   // Ración
            case 4: return 4.00;   // Postre
            default: return 0;
        }
    }
}