package oscar.marin.proyecto1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import oscar.marin.proyecto1.modelos.LineaPedido;


public class DetalleActivity extends AppCompatActivity {

    private TextView lbProducto, lbTipo, lbCantidad, lbPrecio, lbSubtotal;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        inicializarVariables();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            LineaPedido linea = (LineaPedido) bundle.getSerializable("LINEA");
            if (linea != null) {
                lbProducto.setText(getString(R.string.formato_det_producto, linea.getProducto()));
                lbTipo.setText(getString(R.string.formato_det_tipo, linea.getTipo()));
                lbCantidad.setText(getString(R.string.formato_det_cantidad, linea.getCantidad()));
                lbPrecio.setText(getString(R.string.formato_det_precio, linea.getPrecioUnitario()));
                lbSubtotal.setText(getString(R.string.formato_det_subtotal, linea.getSubtotal()));
            }
        }

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void inicializarVariables() {
        lbProducto = findViewById(R.id.lbProductoDet);
        lbTipo = findViewById(R.id.lbTipoDet);
        lbCantidad = findViewById(R.id.lbCantidadDet);
        lbPrecio = findViewById(R.id.lbPrecioDet);
        lbSubtotal = findViewById(R.id.lbSubtotalDet);
        btnVolver = findViewById(R.id.btnVolverDet);
    }
}