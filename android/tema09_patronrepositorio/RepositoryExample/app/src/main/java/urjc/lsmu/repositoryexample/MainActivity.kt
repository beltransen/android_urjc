package urjc.lsmu.repositoryexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import urjc.lsmu.repositoryexample.data.Pelicula
import urjc.lsmu.repositoryexample.viewmodels.PeliculasViewModelFactory
import urjc.lsmu.repositoryexample.databinding.ActivityMainBinding
import urjc.lsmu.repositoryexample.viewmodels.PeliculasViewModel
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: PeliculasViewModel // Declaramos el viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Instanciamos el ViewModel con el ViewModelProvider a través del Factory creado para poder pasarle parámetros
        viewModel = ViewModelProvider(this, PeliculasViewModelFactory(applicationContext))[PeliculasViewModel::class.java]

        binding.btnAddPelicula.setOnClickListener {
            val peli = Pelicula(null, "Todo a la vez en todas partes",
                LocalDate.parse("2022-03-11"), "Dan Kwan, Daniel Scheinert")
            viewModel.addPelicula(peli) // Añadimos la película usando la función del viewmodel
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val miAdaptador = AdaptadorPeliculas()
        binding.recyclerView.adapter = miAdaptador

        val peliculas_observer = Observer<List<Pelicula>> {
            miAdaptador.setPeliculas(it)
        }

        viewModel.peliculas.observe(this, peliculas_observer) // Observamos la lista de películas del viewmodel
    }
}