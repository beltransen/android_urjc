package urjc.lsmu.ej3a5activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var lastBackPressed = 0L
    lateinit var boton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        boton = findViewById(R.id.button)
        boton.setOnClickListener({
            val texto = findViewById<EditText>(R.id.campoTexto).text.toString()
            val intent = Intent(this, SecondaryActivity::class.java)
            intent.putExtra("texto", texto)
            startActivity(intent)
        })

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val now = System.currentTimeMillis()
                if (now - lastBackPressed <= 2000) {
                    finish()
                } else {
                    lastBackPressed = now
                    Toast.makeText(applicationContext, "Pulsa una vez más para cerrar la aplicación", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}