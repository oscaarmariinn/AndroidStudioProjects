package com.example.prueba1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.prueba1.ui.theme.Prueba1Theme

class MainActivity : AppCompatActivity() {

    private lateinit var txtContador: TextView
    private lateinit var btnClick: Button
    private var contador: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contador = 0
        inicializaVistas()

        btnClick.setOnClickListener {
            contador++
            txtContador.text = "$contador veces"
            Toast.makeText(this, "Has pulsado el botón", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inicializaVistas() {
        txtContador = findViewById(R.id.txtContadorMain)
        btnClick = findViewById(R.id.btnClickMain)
    }
}
