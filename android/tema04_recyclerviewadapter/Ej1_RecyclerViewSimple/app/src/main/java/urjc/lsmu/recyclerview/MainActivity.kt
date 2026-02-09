package urjc.lsmu.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var miLista: RecyclerView
    private lateinit var btnAdd: Button

    private val listaStrings = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        miLista = findViewById(R.id.milista)
        miLista.layoutManager = LinearLayoutManager(this)
        var miAdaptador = MyAdapter(listaStrings)
        miLista.adapter = miAdaptador

        btnAdd = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener(){
            listaStrings.add("Hola")
            miAdaptador.notifyItemInserted(listaStrings.size)
        }
    }
}