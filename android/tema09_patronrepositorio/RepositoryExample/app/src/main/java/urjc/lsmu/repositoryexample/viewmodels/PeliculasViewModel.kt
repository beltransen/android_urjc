package urjc.lsmu.repositoryexample.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import urjc.lsmu.repositoryexample.data.Pelicula
import urjc.lsmu.repositoryexample.data.repository.PeliculasRepository
import urjc.lsmu.repositoryexample.data.local.PeliculasRoomDatabase

// Este ViewModel recibe un parámetro Context para poder instanciar la RoomDatabase
class PeliculasViewModel(context: Context): ViewModel() {
    private val repository: PeliculasRepository
    val peliculas: LiveData<List<Pelicula>> // Lista observable pública expuesta a la Actividad
    init {
        val peliculasDAO = PeliculasRoomDatabase.getInstance(context).peliculaDAO() // Inicializamos el DAO
        repository = PeliculasRepository(peliculasDAO)
        peliculas = repository.getAllPeliculas() // Asociamos la lista con la tabla de Peliculas de la BBDD
    }

    fun addPelicula(pelicula: Pelicula){
        repository.addPelicula(pelicula)
    }

    fun delPelicula(pelicula: Pelicula){
        repository.delPelicula(pelicula)
    }
}