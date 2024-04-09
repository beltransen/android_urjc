package urjc.telematica.ciclovida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AboutActivity : AppCompatActivity() {
    private lateinit var textoNombre : TextView
    private lateinit var textoRol : TextView
    private lateinit var btnAtras : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val nombre = intent.getStringExtra("nombre")
        val rol = intent.getStringExtra("rol")

        textoNombre = findViewById(R.id.textoNombre)
        textoRol = findViewById(R.id.textoRol)
        btnAtras = findViewById(R.id.btnAtras)

        textoNombre.text = nombre
        textoRol.text = rol

        btnAtras.setOnClickListener {
            finish()
        }
    }
}