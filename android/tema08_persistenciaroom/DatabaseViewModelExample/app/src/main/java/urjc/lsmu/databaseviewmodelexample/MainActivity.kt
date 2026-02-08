package urjc.lsmu.databaseviewmodelexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import urjc.lsmu.databaseviewmodelexample.data.PeliculasViewModelFactory
import urjc.lsmu.databaseviewmodelexample.data.source.local.PeliculaEntity
import urjc.lsmu.databaseviewmodelexample.databinding.ActivityMainBinding
import urjc.lsmu.databaseviewmodelexample.data.PeliculasViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: PeliculasViewModel // Declaramos el viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Instanciamos el ViewModel con el ViewModelProvider a través del Factory creado para poder pasarle parámetros
        viewModel = ViewModelProvider(this, PeliculasViewModelFactory(applicationContext))[PeliculasViewModel::class.java]

        binding.btnAddPelicula.setOnClickListener {
            var peli = PeliculaEntity(null, "Todo a la vez en todas partes",
                2023, "Dan Kwan, Daniel Scheinert")
            viewModel.addPelicula(peli) // Añadimos la película usando la función del viewmodel
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        var miAdaptador = AdaptadorPeliculas()
        binding.recyclerView.adapter = miAdaptador

        var peliculas_observer = Observer<List<PeliculaEntity>> {
            Log.d("Observer", "Insertado")
            miAdaptador.setPeliculas(it)
        }

        viewModel.peliculas.observe(this, peliculas_observer) // Observamos la lista de películas del viewmodel
    }
}