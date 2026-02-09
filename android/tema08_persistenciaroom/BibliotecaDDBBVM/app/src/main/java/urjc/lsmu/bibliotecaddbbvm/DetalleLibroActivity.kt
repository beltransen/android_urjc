package urjc.lsmu.bibliotecaddbbvm

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import urjc.lsmu.bibliotecaddbbvm.data.source.local.LibroEntity

class DetalleLibroActivity : AppCompatActivity() {
    private lateinit var tvTitulo: TextView
    private lateinit var tvAutor: TextView
    private lateinit var tvAnyo: TextView
    private lateinit var tvResumen: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_libro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

            val libro = intent.getSerializableExtra("libro") as LibroEntity
            tvTitulo = findViewById(R.id.detalleTitulo)
            tvAutor = findViewById(R.id.detalleAutor)
            tvAnyo = findViewById(R.id.detalleAnyo)
            tvResumen = findViewById(R.id.detalleResumen)

            tvTitulo.text = libro.titulo
            tvAutor.text = libro.autor
            tvAnyo.text = libro.anyo.toString()
            tvResumen.text = libro.resumen
    }
}