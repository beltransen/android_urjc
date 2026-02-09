package urjc.lsmu.bibliotecaddbbvm.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import urjc.lsmu.bibliotecaddbbvm.data.source.local.LibroEntity
import urjc.lsmu.bibliotecaddbbvm.data.source.local.LibroDAO
import urjc.lsmu.bibliotecaddbbvm.data.source.local.LibrosRoomDatabase

// Este ViewModel recibe un parámetro Context para poder instanciar la RoomDatabase
class LibrosViewModel(context: Context): ViewModel() {
    private val myDAO: LibroDAO // DAO para interactuar con la BBDD
    val libros: LiveData<List<LibroEntity>> // Lista observable pública expuesta a la Actividad
    init {
        myDAO = LibrosRoomDatabase.getInstance(context).libroDAO() // Inicializamos el DAO
        libros = myDAO.getAll() // Asociamos la lista con la tabla de Libros de la BBDD
    }

    fun addLibro(libroEntity: LibroEntity){
        myDAO.insert(libroEntity)
    }

    fun delLibro(libroEntity: LibroEntity){
        myDAO.delete(libroEntity)
    }
}