package urjc.telematica.ej3contactosfabnavdrawer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class DetallesContactoActivity : AppCompatActivity() {
    private lateinit var tvNombre: EditText
    private lateinit var tvTelefono: EditText
    private lateinit var tvEmail: EditText
    private lateinit var tvNacimiento: EditText
    lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_contacto)
        tvNombre = findViewById(R.id.detalleNombre)
        tvTelefono = findViewById(R.id.detalleTelefono)
        tvEmail = findViewById(R.id.detalleEmail)
        tvNacimiento = findViewById(R.id.detalleNacimiento)
        btnGuardar = findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener({
            val intent = Intent()
            val contacto = Contacto(tvNombre.text.toString(), tvTelefono.text.toString(), tvEmail.text.toString(), tvNacimiento.text.toString())
            intent.putExtra("contacto", contacto)
            setResult(RESULT_OK, intent)
            finish()
        })

    }
}