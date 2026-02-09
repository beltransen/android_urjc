package urjc.telematica.ej3contactosfabnavdrawer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var fab: FloatingActionButton

    private lateinit var miLista: RecyclerView
//    private lateinit var btnAdd: Button
//    private lateinit var etNombre: EditText
//    private lateinit var etTelefono: EditText
//    private lateinit var etEmail: EditText
//    private lateinit var etNacimiento: EditText

    private val listaContactos = mutableListOf<Contacto>()

    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            val contacto = it.data!!.getSerializableExtra("contacto") as Contacto
            listaContactos.add(contacto) // Añadirlo a los datos de la actividad
            miLista.adapter?.notifyItemInserted(listaContactos.size) // Notificar al adaptador
        }else{
            Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_drawer)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
//        supportActionBar?.setHomeAsUpIndicator(android.R.drawable.ic_menu_compass)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Elementos de navegación"

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener {
            it.isChecked = true
            drawerLayout.close()
            when(it.itemId){
                R.id.nav_dummy1 -> Log.d("Drawer","Acción 1 del menú lateral")
                R.id.nav_dummy2 ->  Log.d("Drawer","Acción 2 del menú lateral")
                R.id.nav_dummy3 ->  Log.d("Drawer","Acción 3 del menú lateral")
                R.id.nav_dummy4 ->  Log.d("Drawer","Acción 4 del menú lateral")
                R.id.nav_dummy5 ->  Log.d("Drawer","Acción 5 del menú lateral")
            }
            true
        }

        fab = findViewById(R.id.fab)
        fab.setOnClickListener(){
            // Lanzar la actividad para añadir contacto (con el launcher)
            val intent = Intent(this, DetallesContactoActivity::class.java)
            getResult.launch(intent)
        }

        miLista = findViewById(R.id.listaContactos)
        miLista.layoutManager = LinearLayoutManager(this) // Lista vertical

        var miAdaptador = AdaptadorContactosEventos(listaContactos, longClickListener = object : RVLongClickEvent {
            // Callback que se va a lanzar en un click largo (Borrar el elemento de listaContactos y notificar al adaptador)
            override fun itemLongClick(position: Int) {
                listaContactos.removeAt(position)
                miLista.adapter?.notifyItemRemoved(position) // Notificar al adaptador para que actualice el RecyclerView
                Toast.makeText(applicationContext, "Contacto borrado", Toast.LENGTH_SHORT).show()
            }
        })
        miLista.adapter = miAdaptador

        var divDecorator = DividerItemDecoration(this, RecyclerView.VERTICAL)
        divDecorator.setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.contact_divider, null)!!)
        miLista.addItemDecoration(divDecorator)

        // Ejercicio 2
//        etNombre = findViewById(R.id.nombre)
//        etTelefono = findViewById(R.id.telefono)
//        etEmail = findViewById(R.id.email)
//        etNacimiento = findViewById(R.id.nacimiento)
//        btnAdd = findViewById(R.id.btnAdd)
//        // Listener del botón para añadir contacto
//        btnAdd.setOnClickListener(){
//            // Instanciar objeto Contacto
//            val contacto = Contacto(etNombre.text.toString(), etTelefono.text.toString(),
//                etEmail.text.toString(), etNacimiento.text.toString())
//            listaContactos.add(contacto) // Añadirlo a los datos de la actividad
//            miAdaptador.notifyItemInserted(listaContactos.size) // Notificar al adaptador para que actualice el RecyclerView
//            clearFields() // Función auxiliar para limpiar los campos
//        }
    }

    // Ejercicio 2
//    fun clearFields(){
//        etNombre.text.clear()
//        etTelefono.text.clear()
//        etEmail.text.clear()
//        etNacimiento.text.clear()
//        etNombre.requestFocus()
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> drawerLayout.open()
            R.id.action_delete -> {
                val tamano = listaContactos.size
                listaContactos.clear()
                miLista.adapter?.notifyItemRangeRemoved(0, tamano)
            }
            R.id.action_acerca_de -> Toast.makeText(this, "App desarrollada por Jorge Beltrán", Toast.LENGTH_SHORT).show()
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
}