package urjc.lsmu.repositoryexample.data.repository

import androidx.lifecycle.LiveData
import urjc.lsmu.repositoryexample.data.source.local.Pelicula
import urjc.lsmu.repositoryexample.data.source.local.PeliculaDAO

class PeliculasRepository(private val dao: PeliculaDAO) {
    fun getAllPeliculas(): LiveData<List<Pelicula>> = dao.getAll()

    suspend fun addPelicula(pelicula: Pelicula){
        dao.insert(pelicula)
    }

    suspend fun delPelicula(pelicula: Pelicula){
        dao.delete(pelicula)
    }
}