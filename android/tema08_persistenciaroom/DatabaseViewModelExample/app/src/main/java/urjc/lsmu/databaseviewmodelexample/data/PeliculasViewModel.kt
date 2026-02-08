package urjc.lsmu.databaseviewmodelexample.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import urjc.lsmu.databaseviewmodelexample.data.source.local.PeliculaEntity
import urjc.lsmu.databaseviewmodelexample.data.source.local.PeliculaDAO
import urjc.lsmu.databaseviewmodelexample.data.source.local.PeliculasRoomDatabase

// Este ViewModel recibe un parámetro Context para poder instanciar la RoomDatabase
class PeliculasViewModel(context: Context): ViewModel() {
    private val myDAO: PeliculaDAO // DAO para interactuar con la BBDD
    val peliculas: LiveData<List<PeliculaEntity>> // Lista observable pública expuesta a la Actividad
    init {
        myDAO = PeliculasRoomDatabase.getInstance(context).peliculaDAO() // Inicializamos el DAO
        peliculas = myDAO.getAll() // Asociamos la lista con la tabla de Peliculas de la BBDD
    }

    fun addPelicula(peliculaEntity: PeliculaEntity){
        myDAO.insert(peliculaEntity)
    }

    fun delPelicula(peliculaEntity: PeliculaEntity){
        myDAO.delete(peliculaEntity)
    }
}