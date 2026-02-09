package urjc.lsmu.bibliotecaddbbvm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import urjc.lsmu.bibliotecaddbbvm.data.LibrosViewModelFactory
import urjc.lsmu.bibliotecaddbbvm.data.source.local.LibroEntity
import urjc.lsmu.bibliotecaddbbvm.databinding.ActivityMainBinding
import urjc.lsmu.bibliotecaddbbvm.data.LibrosViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: LibrosViewModel // Declaramos el viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Instanciamos el ViewModel con el ViewModelProvider a través del Factory creado para poder pasarle parámetros
        viewModel = ViewModelProvider(this, LibrosViewModelFactory(applicationContext))[LibrosViewModel::class.java]

        // Lista de libros (generados con IA) para añadir aleatoriamente al pulsar el botón (evitamos crear una actividad de creación de libro para simplificar el ejemplo)
        val librosIniciales = listOf(
            LibroEntity(null, "La madre", 1906, "lamadre.png", "Maxim Gorki", "La madre es una novela ..."),
            LibroEntity(null, "Cien años de soledad", 1967, "cien_anos.png", "Gabriel García Márquez", "Crónica fabulosa de la familia Buendía...") ,
            LibroEntity(null, "Don Quijote de la Mancha", 1605, "quijote.png", "Miguel de Cervantes", "La aventura del ingenioso hidalgo Don Quijote...") ,
            LibroEntity(null, "El Aleph", 1949, "aleph.png", "Jorge Luis Borges", "Relatos que muestran el infinito en lo cotidiano...") ,
            LibroEntity(null, "La casa de los espíritus", 1982, "casaspiritus.png", "Isabel Allende", "Saga familiar que mezcla lo mágico y lo político...") ,
            LibroEntity(null, "Ficciones", 1944, "ficciones.png", "Jorge Luis Borges", "Colección de relatos cortos que juegan con la realidad...") ,
            LibroEntity(null, "Rayuela", 1963, "rayuela.png", "Julio Cortázar", "Novela experimental que invita a distintas lecturas...") ,
            LibroEntity(null, "El túnel", 1948, "tunel.png", "Ernesto Sabato", "Novela sobre la obsesión y la soledad humana...") ,
            LibroEntity(null, "La sombra del viento", 2001, "sombra.png", "Carlos Ruiz Zafón", "Misterio en el Cementerio de los Libros Olvidados...") ,
            LibroEntity(null, "El amor en los tiempos del cólera", 1985, "amoreneltiempo.png", "Gabriel García Márquez", "Historia de amor que atraviesa décadas...")
        )

        // Añadimos el comportamiento al botón: insertar un libro aleatorio de la lista inicial
        binding.btnAddLibro.setOnClickListener {
            // Elegimos al azar uno de los librosIniciales y lo añadimos
            val random = kotlin.random.Random
            val elegido = librosIniciales[random.nextInt(librosIniciales.size)]
            val libroAleatorio = LibroEntity(null, elegido.titulo, elegido.anyo, elegido.portada, elegido.autor, elegido.resumen)
            viewModel.addLibro(libroAleatorio)
        }

        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        val miAdaptador = AdaptadorLibros(object : RVClickEvent {
            override fun itemClick(v: View?, data: LibroEntity) {
                // Creamos un intent para abrir la actividad de detalle, pasando el libro pulsado como extra
                val intent = Intent(this@MainActivity, DetalleLibroActivity::class.java)
                intent.putExtra("libro", data)
                startActivity(intent)
            }
        }, object : RVLongClickEvent{
            override fun itemLongClick(v: View?, data: LibroEntity) {
                viewModel.delLibro(data) // Eliminamos el libro usando la función del viewmodel
            }
        })
        binding.recyclerView.adapter = miAdaptador

        // Observador principal para actualizar el RecyclerView
        val librosObserver = Observer<List<LibroEntity>> {
            Log.d("Observer", "Actualizado: ${it.size} libros")
            miAdaptador.setLibros(it)
        }

        // Observamos la lista de libros del viewmodel y actualizamos el adapter
        viewModel.libros.observe(this, librosObserver)
    }
}