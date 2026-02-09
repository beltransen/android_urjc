package urjc.telematica.eventosrecyclerview

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var miLista: RecyclerView
    private lateinit var btnAdd: Button
    private lateinit var etNombre: EditText
    private lateinit var etTelefono: EditText
    private lateinit var etEmail: EditText
    private lateinit var etNacimiento: EditText

    private val listaContactos = mutableListOf<Contacto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.nombre)
        etTelefono = findViewById(R.id.telefono)
        etEmail = findViewById(R.id.email)
        etNacimiento = findViewById(R.id.nacimiento)
        btnAdd = findViewById(R.id.btnAdd)

        miLista = findViewById(R.id.listaContactos)
        miLista.layoutManager = LinearLayoutManager(this) // Lista vertical

        /* En este ejemplo, el adaptador puede ofrecer dos eventos: click simple y largo (mediante dos interfaces).
         * Los dos listeners son opcionales, por lo que podríamos crear un adaptador con dos, uno o ninguno.
         * Para el ejercicio de clase solo hay que hacer el primer listener (click simple). **/

        var miAdaptador = AdaptadorContactosEventos(listaContactos, clickListener = object : RVClickEvent {
            // Callback que se va a lanzar en un click simple (Actividad secundaria)
            override fun itemClick(v: View?, data: Contacto) { // Recibe la vista y el Contacto, aunque la vista no la usa
                var intentSecundario = Intent(applicationContext, DetallesContactoActivity::class.java)
                intentSecundario.putExtra("contacto", data)
                startActivity(intentSecundario)
            }
        }, longClickListener = object : RVLongClickEvent {
            // Callback que se va a lanzar en un click largo (Borrar el elemento de listaContactos y notificar al adaptador)
            override fun itemLongClick(position: Int) {
                listaContactos.removeAt(position)
                miLista.adapter?.notifyItemRemoved(position) // Notificar al adaptador para que actualice el RecyclerView
                Toast.makeText(applicationContext, "Contacto borrado", Toast.LENGTH_SHORT).show()
            }
        })
        miLista.adapter = miAdaptador

        // EXTRA: Añade un separador entre elementos del RecyclerView
        var divDecorator = DividerItemDecoration(this, RecyclerView.VERTICAL)
        divDecorator.setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.contact_divider, null)!!)
        miLista.addItemDecoration(divDecorator)

        // Listener del botón para añadir contacto
        btnAdd.setOnClickListener(){
            // Instanciar objeto Contacto
            val contacto = Contacto(etNombre.text.toString(), etTelefono.text.toString(),
                etEmail.text.toString(), etNacimiento.text.toString())
            listaContactos.add(contacto) // Añadirlo a los datos de la actividad
            miAdaptador.notifyItemInserted(listaContactos.size) // Notificar al adaptador para que actualice el RecyclerView
            clearFields() // Función auxiliar para limpiar los campos
        }
    }

    fun clearFields(){
        etNombre.text.clear()
        etTelefono.text.clear()
        etEmail.text.clear()
        etNacimiento.text.clear()
        etNombre.requestFocus()
    }
}