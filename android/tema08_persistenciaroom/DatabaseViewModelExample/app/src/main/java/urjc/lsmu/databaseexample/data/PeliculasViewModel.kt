package urjc.lsmu.databaseexample.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import urjc.lsmu.databaseexample.data.source.local.Pelicula
import urjc.lsmu.databaseexample.data.source.local.PeliculaDAO
import urjc.lsmu.databaseexample.data.source.local.PeliculasRoomDatabase

// Este ViewModel recibe un parámetro Context para poder instanciar la RoomDatabase
class PeliculasViewModel(context: Context): ViewModel() {
    private val myDAO: PeliculaDAO // DAO para interactuar con la BBDD
    val peliculas: LiveData<List<Pelicula>> // Lista observable pública expuesta a la Actividad
    init {
        myDAO = PeliculasRoomDatabase.getInstance(context).peliculaDAO() // Inicializamos el DAO
        peliculas = myDAO.getAll() // Asociamos la lista con la tabla de Peliculas de la BBDD
    }

    fun addPelicula(pelicula: Pelicula){
        myDAO.insert(pelicula)
    }

    fun delPelicula(pelicula: Pelicula){
        myDAO.delete(pelicula)
    }
}