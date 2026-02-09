package urjc.lsmu.ej6activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AnadirActivity : AppCompatActivity() {
    lateinit var btnConfirmar : Button
    lateinit var txtNombre : TextView
    lateinit var txtPrecio : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_anadir)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtNombre = findViewById(R.id.nombreProducto)
        txtPrecio = findViewById(R.id.valorProducto)
        btnConfirmar = findViewById(R.id.btnConfirmar)

        btnConfirmar.setOnClickListener({
            val intent = intent
            intent.putExtra("nombre", txtNombre.text.toString())
            intent.putExtra("precio", txtPrecio.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        })

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                setResult(RESULT_CANCELED)
                finish()
            }
        })
    }
}