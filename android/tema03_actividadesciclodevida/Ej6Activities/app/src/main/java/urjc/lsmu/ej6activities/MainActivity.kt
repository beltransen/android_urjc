package urjc.lsmu.ej6activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    lateinit var btnLimpiar : Button
    lateinit var btnAnadir : Button
    lateinit var txtCompra : TextView


    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            Log.i(TAG, "Recibido ${it.data?.getStringExtra("nombre")}")
            txtCompra.text = "${txtCompra.text}\n${it.data?.getStringExtra("nombre")} - ${it.data?.getStringExtra("precio")}"
        }else{
            Log.i(TAG, "Operación cancelada")
            Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtCompra = findViewById(R.id.txtListaCompra)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnAnadir = findViewById(R.id.btnAnadir)

        btnLimpiar.setOnClickListener({
            txtCompra.text = ""
        })

        btnAnadir.setOnClickListener({
            val intent = Intent(this, AnadirActivity::class.java)
            getResult.launch(intent)
        })
    }
}