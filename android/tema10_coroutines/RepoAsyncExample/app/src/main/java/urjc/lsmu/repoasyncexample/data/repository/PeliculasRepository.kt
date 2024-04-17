package urjc.lsmu.repoasyncexample.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import urjc.lsmu.repoasyncexample.data.Pelicula
import urjc.lsmu.repoasyncexample.data.source.local.PeliculaDAO
import urjc.lsmu.repoasyncexample.data.toDomain
import urjc.lsmu.repoasyncexample.data.toEntity

class PeliculasRepository(private val dao: PeliculaDAO) {
    fun getAllPeliculas(): LiveData<List<Pelicula>> = dao.getAll().map { listaPeliculas ->
        listaPeliculas.map { it.toDomain() } }

    suspend fun addPelicula(pelicula: Pelicula){
        dao.insert(pelicula.toEntity())
    }
    suspend fun delPelicula(pelicula: Pelicula){
        dao.delete(pelicula.toEntity())
    }
}