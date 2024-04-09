package urjc.telematica.eventosrecyclerview

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetallesContactoActivity : AppCompatActivity() {
    private lateinit var tvNombre: TextView
    private lateinit var tvTelefono: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvNacimiento: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_contacto)
        val contacto = intent.getSerializableExtra("contacto") as Contacto
        tvNombre = findViewById(R.id.detalleNombre)
        tvTelefono = findViewById(R.id.detalleTelefono)
        tvEmail = findViewById(R.id.detalleEmail)
        tvNacimiento = findViewById(R.id.detalleNacimiento)

        tvNombre.text = contacto.nombre
        tvTelefono.text = contacto.telefono
        tvEmail.text = contacto.email
        tvNacimiento.text = contacto.nacimiento
    }
}