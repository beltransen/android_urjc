package urjc.lsmu.databaseexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import urjc.lsmu.databaseexample.data.source.local.Pelicula
import urjc.lsmu.databaseexample.data.source.local.PeliculasRoomDatabase
import urjc.lsmu.databaseexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var peliculas: LiveData<List<Pelicula>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val peliculasDAO = PeliculasRoomDatabase.getInstance(application).peliculaDAO()
        peliculas = peliculasDAO.getAll()

        binding.btnAddPelicula.setOnClickListener {
            var peli = Pelicula(null, "Todo a la vez en todas partes",
                2023, "Dan Kwan, Daniel Scheinert")
            peliculasDAO.insert(peli)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        var miAdaptador = AdaptadorPeliculas()
        binding.recyclerView.adapter = miAdaptador

        var peliculas_observer = Observer<List<Pelicula>> {
            Log.d("Observer", "Insertado")
            miAdaptador.setPeliculas(it)
        }
        peliculas.observe(this, peliculas_observer)
    }
}