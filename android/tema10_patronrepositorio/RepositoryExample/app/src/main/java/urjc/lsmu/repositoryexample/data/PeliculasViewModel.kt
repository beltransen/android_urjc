package urjc.lsmu.repositoryexample.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import urjc.lsmu.repositoryexample.data.repository.PeliculasRepository
import urjc.lsmu.repositoryexample.data.source.local.Pelicula
import urjc.lsmu.repositoryexample.data.source.local.PeliculaDAO
import urjc.lsmu.repositoryexample.data.source.local.PeliculasRoomDatabase

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
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPelicula(pelicula)
        }
    }

    fun delPelicula(pelicula: Pelicula){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delPelicula(pelicula)
        }
    }
}