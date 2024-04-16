package urjc.lsmu.repoasyncexample.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import urjc.lsmu.repoasyncexample.data.Pelicula
import urjc.lsmu.repoasyncexample.data.source.local.PeliculaEntity
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


// Codigo para diapos
class PeliculasAPI

class PeliculasRepository2(
    context: Context
) {
    var peliculas: LiveData<List<PeliculaEntity>> = dao.getAll()
    fun getAllPeliculas(): LiveData<List<PeliculaEntity>>{
      if (peliculas.value?.isEmpty() == true) {
          var remotePeliculas = getPeliculasRemote()
          // Insertar películas remotas en bbdd local
          println(remotePeliculas)
      }
        return peliculas
    }

    // Función para los datos de películas de un servidor remoto con la API Rest
    fun getPeliculasRemote(): List<PeliculaEntity> = TODO()

    suspend fun addPelicula(pelicula: PeliculaEntity){
        dao.insert(pelicula)
    }
    suspend fun delPelicula(pelicula: PeliculaEntity){
        dao.delete(pelicula)
    }

}