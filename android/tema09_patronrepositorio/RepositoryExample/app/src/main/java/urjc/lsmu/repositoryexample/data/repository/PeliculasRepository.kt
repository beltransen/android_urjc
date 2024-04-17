package urjc.lsmu.repositoryexample.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import urjc.lsmu.repositoryexample.data.Pelicula
import urjc.lsmu.repositoryexample.data.local.PeliculaEntity
import urjc.lsmu.repositoryexample.data.local.PeliculaDAO
import urjc.lsmu.repositoryexample.data.toDomain
import urjc.lsmu.repositoryexample.data.toEntity

class PeliculasRepository(private val dao: PeliculaDAO) {
    fun getAllPeliculas(): LiveData<List<Pelicula>> = dao.getAll().map { listaPeliculas ->
        listaPeliculas.map { it.toDomain() } }

    fun addPelicula(pelicula: Pelicula){
        dao.insert(pelicula.toEntity())
    }
    fun delPelicula(pelicula: Pelicula){
        dao.delete(pelicula.toEntity())
    }
}