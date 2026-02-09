package urjc.lsmu.ej2activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btn : Button
    private lateinit var textView : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn = findViewById(R.id.button)
        textView = findViewById(R.id.url)
        btn.setOnClickListener({
            var intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(textView.text.toString()))
            // ATENCION: si no declaramos queries en el manifest, resolveActivity no puede saber si hay
            // alguna app que pueda manejar la intención, por lo que siempre devuelve null
            if (intent.resolveActivity(packageManager)!= null){
                startActivity(intent)
            }
        })

    }
}