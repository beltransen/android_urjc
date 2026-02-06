package urjc.lsmu.holamundo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var miBoton: Button
    private lateinit var btnRestar: Button
    private lateinit var miTexto: TextView

    private var contador = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        miTexto = findViewById(R.id.mitexto)
        miBoton = findViewById(R.id.btn_sumar)
        miBoton.setOnClickListener({
            Log.d("MAIN", "He clickado")
            contador++
            miTexto.setText("Contador $contador")
        })

        btnRestar = findViewById(R.id.btn_restar)
        btnRestar.setOnClickListener({
            contador--
            miTexto.setText("Contador $contador")
        })
    }
}