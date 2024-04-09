package urjc.lsmu.viewmodellivedata

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import urjc.lsmu.viewmodellivedata.data.Pelicula
import urjc.lsmu.viewmodellivedata.data.PeliculasViewModel
import urjc.lsmu.viewmodellivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val viewModel: PeliculasViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        // Configuración del RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        var miAdaptador = AdaptadorPeliculas()
        binding.recyclerView.adapter = miAdaptador

        // Evento de UI llama a metodo de ViewModel
        binding.btnAddPelicula.setOnClickListener {
            var peli = Pelicula( "Todo a la vez en todas partes",
                2023, "Dan Kwan, Daniel Scheinert")
            viewModel.addPelicula(peli)
        }

        // Patron Observer para actualizar el RecyclerView con los cambios en el ViewModel
        var peliculas_observer = Observer<List<Pelicula>> {
            Log.d("Observer", "Insertado")
            miAdaptador.setPeliculas(it)
        }
        viewModel.listaPeliculas.observe(this, peliculas_observer)
    }
}