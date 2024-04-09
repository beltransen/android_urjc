package urjc.lsmu.viewmodellivedata.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PeliculasViewModel: ViewModel() {
    private val _peliculas = mutableListOf<Pelicula>() // Guarda los datos
    private val _listaPeliculas = MutableLiveData<List<Pelicula>>() // Permite observar los datos
    val listaPeliculas: LiveData<List<Pelicula>> // Expone el observable sin permitir editarlo
        get() = _listaPeliculas

    fun addPelicula(pelicula: Pelicula){
        _peliculas.add(pelicula) // Añade el nuevo elemento
        _listaPeliculas.value = _peliculas // Actualiza el observable para notificar a los observadores
    }

    fun delPelicula(index: Int){
        _peliculas.removeAt(index)
        _listaPeliculas.value = _peliculas
    }
}